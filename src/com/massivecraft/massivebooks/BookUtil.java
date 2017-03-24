package com.massivecraft.massivebooks;

import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MConf;
import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.InventoryUtil;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookUtil
{	
	// -------------------------------------------- //
	// BOOK META
	// -------------------------------------------- //
	
	public static boolean hasBookMeta(ItemStack item)
	{
		if (item == null) return false;
		Material type = item.getType();
		if (type == Material.WRITTEN_BOOK) return true;
		if (type == Material.BOOK_AND_QUILL) return true;
		return false;
	}
	
	public static BookMeta getBookMeta(ItemStack item)
	{
		if (item == null) return null;
		ItemMeta meta = item.getItemMeta();
		if (!(meta instanceof BookMeta)) return null;
		return (BookMeta)meta;
	}
	
	public static boolean isBookMetaEmpty(ItemStack item)
	{
		if (item == null) return true;
		BookMeta meta = getBookMeta(item);
		return isBookMetaEmpty(meta);
	}
	
	public static boolean isBookMetaEmpty(BookMeta meta)
	{
		if (meta == null) return true;
		if (meta.hasTitle()) return false;
		if (meta.hasAuthor()) return false;
		if (meta.hasPages()) return false;
		return true;
	}
	
	// -------------------------------------------- //
	// UPDATE BOOKS
	// -------------------------------------------- //
	
	// Many books
	
	public static int updateBooks(HumanEntity player)
	{
		if (player == null) return 0;
		return updateBooks(player.getInventory());
	}
	
	public static int updateBooks(Inventory inventory)
	{
		if (inventory == null) return 0;
		int ret = 0;
		for (ItemStack item : inventory.getContents())
		{
			if (updateBook(item)) ret++;
		}
		
		//System.out.println("Updated "+ret);
		
		if (ret > 0)
		{
			//System.out.println("queuing view inv-update");
			sendInventoryContentToViewersSoon(inventory);
		}
		return ret;
	}
	
	// One Book
	
	public static boolean updateBook(ItemFrame itemFrame)
	{
		ItemStack item = itemFrame.getItem();
		if (updateBook(item))
		{
			itemFrame.setItem(item);
			return true;
		}
		return false;
	}
	
	public static boolean updateBook(Item item)
	{
		return updateBook(item.getItemStack());
	}
	
	public static boolean updateBook(ItemStack item)
	{
		if (item == null) return false;
		if (!hasBookMeta(item)) return false;
		if (updateServerbook(item)) return true;
		return updateDisplayName(item);
	}
	
	// Saved
	
	public static boolean updateServerbook(ItemStack item)
	{
		if (!MConf.get().autoupdatingServerbooks) return false;
		
		if (item == null) return false;
		String title = getTitle(item);
		
		MBook mbook = MBook.get(title);
		if (mbook == null) return false;
		
		ItemStack blueprint = mbook.getItem();
		if (blueprint == null) return false;
		
		if (item.isSimilar(blueprint)) return false;
		
		item.setDurability(blueprint.getDurability());
		item.setType(blueprint.getType());
		item.setItemMeta(blueprint.getItemMeta());
		
		return true;
	}
	
	// DisplayName
	
	public static boolean updateDisplayName(ItemStack item)
	{
		if (!MConf.get().autoupdatingDisplayNames) return false;
		if (item == null) return false;
		String targetDisplayname = Lang.descDisplayName(item);
		return setDisplayName(item, targetDisplayname);
	}
	
	public static boolean setDisplayName(ItemStack item, String targetDisplayName)
	{
		if (item == null) return false;
		ItemMeta meta = item.getItemMeta();
		String currentDisplayName = meta.getDisplayName();
		if (MUtil.equals(currentDisplayName, targetDisplayName)) return false;
		meta.setDisplayName(targetDisplayName);
		return item.setItemMeta(meta);
	}
	
	// The awesomest trick to force-update-clients :O
	
	public static void sendInventoryContentToViewersSoon(Inventory inventory)
	{
		final Set<Player> players = new HashSet<Player>();
		for (HumanEntity viewer : inventory.getViewers())
		{
			if (viewer instanceof Player)
			{
				players.add((Player)viewer);
			}
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(MassiveBooks.get(), new Runnable()
		{
			@Override
			public void run()
			{
				for (Player player : players)
				{
					InventoryUtil.update(player);
				}
			}
		});
	}
	
	// -------------------------------------------- //
	// TITLE
	// -------------------------------------------- //
	
	public static String getTitle(ItemStack item)
	{
		BookMeta meta = getBookMeta(item);
		if (meta == null) return null;
		if (!meta.hasTitle()) return null;
		return meta.getTitle();
	}
	
	public static boolean setTitle(ItemStack item, String title)
	{
		BookMeta meta = getBookMeta(item);
		if (meta == null) return false;
		meta.setTitle(title);
		if (!item.setItemMeta(meta)) return false;
		updateBook(item);
		return true;
	}
	
	public static boolean isTitleEquals(ItemStack item, String title)
	{
		String actualTitle = getTitle(item);
		if (actualTitle == null) return title == null;
		return actualTitle.equals(title);
	}
	
	// -------------------------------------------- //
	// AUTHOR
	// -------------------------------------------- //
	
	public static String getAuthor(ItemStack item)
	{
		BookMeta meta = getBookMeta(item);
		if (meta == null) return null;
		if (!meta.hasAuthor()) return null;
		return meta.getAuthor();
	}
	
	public static boolean setAuthor(ItemStack item, String author)
	{
		BookMeta meta = getBookMeta(item);
		if (meta == null) return false;
		meta.setAuthor(author);
		if (!item.setItemMeta(meta)) return false;
		updateDisplayName(item);
		return true;
	}
	
	public static boolean isAuthorEqualsId(ItemStack item, String author)
	{
		String actualAuthor = getAuthor(item);
		if (actualAuthor == null) return author == null;
		return actualAuthor.equalsIgnoreCase(author);
	}
	
	public static boolean isAuthorEquals(ItemStack item, CommandSender author)
	{
		return isAuthorEqualsId(item, IdUtil.getName(author));
	}
	
	// -------------------------------------------- //
	// PAGES
	// -------------------------------------------- //
	
	public static List<String> getPages(ItemStack item)
	{
		BookMeta meta = getBookMeta(item);
		if (meta == null) return null;
		if (!meta.hasPages()) return null;
		return meta.getPages();
	}
	
	public static boolean setPages(ItemStack item, List<String> pages)
	{
		BookMeta meta = getBookMeta(item);
		if (meta == null) return false;
		meta.setPages(pages);
		if (!item.setItemMeta(meta)) return false;
		updateDisplayName(item);
		return true;
	}
	
	public static boolean isPagesEquals(ItemStack item, List<String> pages)
	{
		List<String> actualPages = getPages(item);
		if (actualPages == null) return pages == null;
		return actualPages.equals(pages);
	}
	
	// -------------------------------------------- //
	// UNLOCK & LOCK
	// -------------------------------------------- //

	public static boolean unlock(ItemStack item)
	{
		if (item == null) return false;
		if (item.getType() == Material.BOOK_AND_QUILL) return true;
		item.setType(Material.BOOK_AND_QUILL);
		updateDisplayName(item);
		return true;
	}
	
	public static boolean lock(ItemStack item)
	{
		if (item == null) return false;
		if (item.getType() == Material.WRITTEN_BOOK) return true;
		item.setType(Material.WRITTEN_BOOK);
		updateDisplayName(item);
		return true;
	}
	
	public static boolean isLocked(ItemStack item)
	{
		if (item == null) return false;
		return item.getType() == Material.WRITTEN_BOOK;
	}
	
	public static boolean isUnlocked(ItemStack item)
	{
		if (item == null) return false;
		return item.getType() == Material.BOOK_AND_QUILL;
	}
	
	// -------------------------------------------- //
	// CLEAR
	// -------------------------------------------- //
	
	public static boolean clear(ItemStack item)
	{
		item.setDurability((short) 0);
		item.setType(Material.BOOK_AND_QUILL);
		item.setItemMeta(null);
		return true;
	}
	
	public static boolean isCleared(ItemStack item)
	{
		return item != null && item.getDurability() == 0 && item.getType() == Material.BOOK_AND_QUILL && !item.hasItemMeta();
	}
	
	// -------------------------------------------- //
	// LORE-FLAGS
	// -------------------------------------------- //
	
	public static boolean containsFlag(ItemStack item, String flag)
	{
		if (flag == null) return false;
		if (!item.hasItemMeta()) return false;
		ItemMeta meta = item.getItemMeta();
		if (!meta.hasLore()) return false;
		List<String> lore = meta.getLore();
		return lore.contains(flag);
	}
	
	public static boolean addFlag(ItemStack item, String flag)
	{
		if (flag == null) return false;
		if (containsFlag(item, flag)) return false;
		ItemMeta meta = item.getItemMeta();
		if (meta.hasLore())
		{
			List<String> lore = meta.getLore();
			lore.add(flag);
			meta.setLore(lore);
		}
		else
		{
			meta.setLore(MUtil.list(flag));
		}
		if (!item.setItemMeta(meta)) return false;
		updateDisplayName(item);
		return true;
	}
	
	public static boolean removeFlag(ItemStack item, String flag)
	{
		if (flag == null) return false;
		if (!containsFlag(item, flag)) return false;
		ItemMeta meta = item.getItemMeta();
		if (!meta.hasLore()) return false;
		List<String> lore = meta.getLore();
		lore.remove(flag);
		if (lore.size() == 0)
		{
			meta.setLore(null);
		}
		else
		{
			meta.setLore(lore);
		}
		if (!item.setItemMeta(meta)) return false;
		updateBook(item);
		return true;
	}
	
	// -------------------------------------------- //
	// COPY PERMS
	// -------------------------------------------- //
	
	public static boolean hasCopyPerm(ItemStack item, CommandSender sender, boolean verboose)
	{
		if (BookUtil.isAuthorEquals(item, sender)) return true;
		if (!Perm.COPY_OTHER.has(sender, true)) return false;
		if (!BookUtil.containsFlag(item, Const.COPYRIGHTED)) return true;
		if (!Perm.COPY_COPYRIGHTED.has(sender, true)) return false;
		return true;
	}
	
}
