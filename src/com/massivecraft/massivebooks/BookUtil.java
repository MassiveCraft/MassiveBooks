package com.massivecraft.massivebooks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.mcore.util.SenderUtil;

public class BookUtil
{
	// -------------------------------------------- //
	// BOOK META
	// -------------------------------------------- //
	
	public static BookMeta getBookMeta(ItemStack item)
	{
		ItemMeta meta = item.getItemMeta();
		if (!(meta instanceof BookMeta)) return null;
		return (BookMeta)meta;
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
		return item.setItemMeta(meta);
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
		return item.setItemMeta(meta);
	}
	
	public static boolean isAuthorEqualsId(ItemStack item, String author)
	{
		String actualAuthor = getAuthor(item);
		if (actualAuthor == null) return author == null;
		return actualAuthor.equalsIgnoreCase(author);
	}
	
	public static boolean isAuthorEquals(ItemStack item, CommandSender author)
	{
		return isAuthorEqualsId(item, SenderUtil.getSenderId(author));
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
		return item.setItemMeta(meta);
	}
	
	public static boolean isPagesEquals(ItemStack item, List<String> pages)
	{
		List<String> actualPages = getPages(item);
		if (actualPages == null) return pages == null;
		return actualPages.equals(pages);
	}
	
	// -------------------------------------------- //
	// UNSIGN
	// -------------------------------------------- //
	
	public static boolean unsign(ItemStack item)
	{
		setAuthor(item, null);
		setTitle(item, null);
		item.setType(Material.BOOK_AND_QUILL);
		return true;
	}
	
	public static boolean isUnsigned(ItemStack item)
	{
		return isAuthorEqualsId(item, null) && isTitleEquals(item, null) && item.getType() == Material.BOOK_AND_QUILL;
	}
	
	// -------------------------------------------- //
	// CLEAR
	// -------------------------------------------- //
	
	public static boolean clear(ItemStack item)
	{
		item.setDurability((short) 0);
		item.setType(Material.BOOK_AND_QUILL);
		item.setItemMeta(Bukkit.getItemFactory().getItemMeta(Material.BOOK_AND_QUILL));
		return true;
	}
	
	public static boolean isCleared(ItemStack item)
	{
		return item.getDurability() == 0 && item.getType() == Material.BOOK_AND_QUILL && !item.hasItemMeta();
	}
	
	// -------------------------------------------- //
	// LORE-FLAGS
	// -------------------------------------------- //
	
	public static boolean containsFlag(ItemStack item, String flag)
	{
		if (flag == null) return false;
		return item.getItemMeta().getLore().contains(flag);
	}
	
	public static boolean addFlag(ItemStack item, String flag)
	{
		if (flag == null) return false;
		if (containsFlag(item, flag)) return false;
		return item.getItemMeta().getLore().add(flag);
	}
	
	public static boolean removeFlag(ItemStack item, String flag)
	{
		if (flag == null) return false;
		if (!containsFlag(item, flag)) return false;
		return item.getItemMeta().getLore().remove(flag);
	}
	
	// -------------------------------------------- //
	// POWERTOOL
	// -------------------------------------------- //
	
	public static List<String> powertoolGetRawlines(ItemStack item) throws IllegalArgumentException
	{
		List<String> ret = new ArrayList<String>();
		for (String page : getPages(item))
		{
			if (page == null) break;
			if (page.trim().length() <= 2) break;
			for (String line : page.split("\\r?\\n"))
			{
				if (line == null) continue;
				line = line.trim();
				if (line.length() == 0) continue;
				ret.add(line);
			}
		}
		return ret;
	}
	
	public static boolean powertoolLinesContains(List<String> lines, String target)
	{
		for (String line : lines)
		{
			if (line.contains(target)) return true;
		}
		return false;
	}
	
	public static String powertoolProccessRawline(String rawline, String meId, String youId, String colorBase, String colorMe, String colorYou)
	{
		String ret = rawline;
		
		if (meId != null)
		{
			ret = ret.replace(Const.POWERTOOL_ME, colorMe+meId+colorBase);
		}
		
		if (youId != null)
		{
			ret = ret.replace(Const.POWERTOOL_ME, colorYou+youId+colorBase);
		}
		
		return colorBase+ret;
	}
	
	public static String powertoolProccessRawline(String rawline, String meId, String youId, boolean color)
	{
		String colorMe = "";
		String colorYou = "";
		String colorBase = "";
		
		if (color)
		{
			colorMe = Lang.POWERTOOL_COLOR_ME;
			colorYou = Lang.POWERTOOL_COLOR_YOU;
			boolean command = rawline.startsWith("/");
			if (command)
			{
				colorBase = Lang.POWERTOOL_COLOR_COMMAND;
			}
			else
			{
				colorBase = Lang.POWERTOOL_COLOR_CHAT;
			}
		}
		
		return powertoolProccessRawline(rawline, meId, youId, colorBase, colorMe, colorYou);
	}
	
	public static String powertoolProccessRawline(String rawline, Player me, Player you, boolean color)
	{
		return powertoolProccessRawline(rawline, SenderUtil.getSenderId(me), SenderUtil.getSenderId(you), color);
	}
	
	public static void powertoolUse(Cancellable cancellable, Player me, Player you)
	{
		// If the player is holding a powertool ...
		ItemStack item = me.getItemInHand();
		try
		{
			if (!containsFlag(item, Const.POWERTOOL)) return;
		}
		catch (Exception e)
		{
			return;
		}
		
		// ... cancel the event ...
		cancellable.setCancelled(true);
		
		// ... extract the rawlines from the powertool ...
		List<String> rawlines = powertoolGetRawlines(item);
		
		// ... ensure the powertool is/isnt used on other player ...
		boolean isYouPowertool = powertoolLinesContains(rawlines, Const.POWERTOOL_YOU);
		if (isYouPowertool && you == null)
		{
			me.sendMessage(Lang.POWERTOOL_YOU_SHOULD);
			return;
		}
		else if (!isYouPowertool && you != null)
		{
			me.sendMessage(Lang.POWERTOOL_YOU_SHOULDNT);
			return;
		}
		
		// ... try to run each of them.
		for (String rawline : rawlines)
		{
			String prolineFlat = powertoolProccessRawline(rawline, me, you, false);
			String prolineColored = powertoolProccessRawline(rawline, me, you, true);
			
			try
			{
				me.chat(prolineFlat);
				me.sendMessage(String.format(Lang.POWERTOOL_RAN, prolineColored));
			}
			catch (Exception e)
			{
				me.sendMessage(String.format(Lang.POWERTOOL_FAILED, prolineColored, e.getMessage()));
			}
		}
	}
	
	
}
