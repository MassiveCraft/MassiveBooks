package com.massivecraft.massivebooks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.mcore.util.MUtil;
import com.massivecraft.mcore.util.SenderUtil;

public class BookUtil
{
	// -------------------------------------------- //
	// ITEM STACK
	// -------------------------------------------- //
	
	public static ItemStack getItemStack(Object object) throws IllegalArgumentException
	{
		if (!(object instanceof ItemStack)) throw new IllegalArgumentException("Object wasn't ItemStack");
		return (ItemStack)object;
	}
	
	// -------------------------------------------- //
	// ITEM TYPE
	// -------------------------------------------- //
	
	public static Material getItemType(Object object) throws IllegalArgumentException
	{
		return getItemStack(object).getType();
	}
	
	public static void setItemType(Object object, Material itemType) throws IllegalArgumentException
	{
		getItemStack(object).setType(itemType);
	}
	
	public static boolean isItemTypeEquals(Object object, Material itemType) throws IllegalArgumentException
	{
		return getItemType(object) == itemType;
	}
	
	// -------------------------------------------- //
	// BOOK META
	// -------------------------------------------- //
	
	public static BookMeta getBookMeta(Object object) throws IllegalArgumentException
	{
		ItemStack item = getItemStack(object);
		
		ItemMeta meta = item.getItemMeta();
		if (!(meta instanceof BookMeta)) throw new IllegalArgumentException("ItemStack didn't have BookMeta");
		return (BookMeta)meta;
	}
	
	public static void setBookMeta(Object object, BookMeta bookMeta) throws IllegalArgumentException
	{
		ItemStack item = getItemStack(object);
		item.setItemMeta(bookMeta);
	}
	
	// -------------------------------------------- //
	// TITLE
	// -------------------------------------------- //
	
	public static String getTitle(Object object) throws IllegalArgumentException
	{
		BookMeta bookMeta = getBookMeta(object);
		if (!bookMeta.hasTitle()) return null;
		return bookMeta.getTitle();
	}
	
	public static void setTitle(Object object, String title) throws IllegalArgumentException
	{
		BookMeta bookMeta = getBookMeta(object);
		bookMeta.setTitle(title);
		setBookMeta(object, bookMeta);
	}
	
	public static boolean isTitleEquals(Object object, String title) throws IllegalArgumentException
	{
		String actualTitle = getTitle(object);
		if (actualTitle == null) return title == null;
		return actualTitle.equals(title);
	}
	
	// -------------------------------------------- //
	// AUTHOR
	// -------------------------------------------- //
	
	public static String getAuthor(Object object) throws IllegalArgumentException
	{
		BookMeta bookMeta = getBookMeta(object);
		if (!bookMeta.hasAuthor()) return null;
		return bookMeta.getAuthor();
	}
	
	public static void setAuthor(Object object, String author) throws IllegalArgumentException
	{
		BookMeta bookMeta = getBookMeta(object);
		bookMeta.setAuthor(author);
		setBookMeta(object, bookMeta);
	}
	
	public static boolean isAuthorEqualsId(Object object, String author) throws IllegalArgumentException
	{
		String actualAuthor = getAuthor(object);
		if (actualAuthor == null) return author == null;
		return actualAuthor.equalsIgnoreCase(author);
	}
	
	public static boolean isAuthorEquals(Object object, CommandSender author) throws IllegalArgumentException
	{
		return isAuthorEqualsId(object, SenderUtil.getSenderId(author));
	}
	
	// -------------------------------------------- //
	// PAGES
	// -------------------------------------------- //
	
	public static List<String> getPages(Object object) throws IllegalArgumentException
	{
		BookMeta bookMeta = getBookMeta(object);
		if (!bookMeta.hasPages()) return null;
		return bookMeta.getPages();
	}
	
	public static void setPages(Object object, List<String> pages) throws IllegalArgumentException
	{
		BookMeta bookMeta = getBookMeta(object);
		bookMeta.setPages(pages);
		setBookMeta(object, bookMeta);
	}
	
	public static boolean isPagesEquals(Object object, List<String> pages) throws IllegalArgumentException
	{
		String actualPages = getAuthor(object);
		if (actualPages == null) return pages == null;
		return actualPages.equals(pages);
	}
	
	// -------------------------------------------- //
	// UNSIGN
	// -------------------------------------------- //
	
	public static void setUnsigned(Object object) throws IllegalArgumentException
	{
		setAuthor(object, null);
		setTitle(object, null);
		setItemType(object, Material.BOOK_AND_QUILL);
	}
	
	public static boolean isUnsigned(Object object) throws IllegalArgumentException
	{
		return isAuthorEqualsId(object, null) && isTitleEquals(object, null) && isItemTypeEquals(object, Material.BOOK_AND_QUILL);
	}
	
	// -------------------------------------------- //
	// CLEAR
	// -------------------------------------------- //
	
	public static void setCleared(Object object) throws IllegalArgumentException
	{
		setUnsigned(object);
		setPages(object, null);
	}
	
	public static boolean isCleared(Object object) throws IllegalArgumentException
	{
		return isUnsigned(object) && isPagesEquals(object, null);
	}
	
	// -------------------------------------------- //
	// POWERTOOL STATE
	// -------------------------------------------- //

	public static boolean isPowertool(Object object) throws IllegalArgumentException
	{
		BookMeta bookMeta = getBookMeta(object);
		if (!bookMeta.hasLore()) return false;
		return bookMeta.getLore().contains(Const.POWERTOOL);
	}
	
	public static void setPowertool(Object object, boolean powertool) throws IllegalArgumentException
	{
		BookMeta bookMeta = getBookMeta(object);
		if (powertool)
		{
			bookMeta.setLore(MUtil.list(Const.POWERTOOL));
		}
		else
		{
			bookMeta.setLore(new ArrayList<String>());
		}
		setBookMeta(object, bookMeta);
	}
	
	public static boolean isPowertoolEquals(Object object, boolean powertool) throws IllegalArgumentException
	{
		return isPowertool(object) == powertool;
	}
	
	// -------------------------------------------- //
	// POWERTOOL
	// -------------------------------------------- //
	
	public static List<String> powertoolGetRawlines(Object object) throws IllegalArgumentException
	{
		List<String> ret = new ArrayList<String>();
		for (String page : getPages(object))
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
			if (!isPowertoolEquals(item, true)) return;
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
