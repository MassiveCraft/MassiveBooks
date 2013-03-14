package com.massivecraft.massivebooks;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.mcore.util.SenderUtil;

public class BookUtil
{
	// -------------------------------------------- //
	// BOOK META
	// -------------------------------------------- //
	
	public static BookMeta getBookMeta(Object object) throws IllegalArgumentException
	{
		if (!(object instanceof ItemStack)) throw new IllegalArgumentException("Object wasn't ItemStack");
		ItemStack item = (ItemStack)object;
		
		ItemMeta meta = item.getItemMeta();
		if (!(meta instanceof BookMeta)) throw new IllegalArgumentException("ItemStack didn't have BookMeta");
		BookMeta bookMeta = (BookMeta)meta;
		
		return bookMeta;
	}
	
	public static void setBookMeta(Object object, BookMeta bookMeta) throws IllegalArgumentException
	{
		if (!(object instanceof ItemStack)) throw new IllegalArgumentException("Object wasn't ItemStack");
		ItemStack item = (ItemStack)object;
		
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
		String actualTitle = getAuthor(object);
		if (actualTitle == null) return title == null;
		return actualTitle.equalsIgnoreCase(title);
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
	
	public static boolean isAuthorEquals(Object object, String author) throws IllegalArgumentException
	{
		String actualAuthor = getAuthor(object);
		if (actualAuthor == null) return author == null;
		return actualAuthor.equalsIgnoreCase(author);
	}
	
	public static boolean isAuthorEquals(Object object, CommandSender author) throws IllegalArgumentException
	{
		return isAuthorEquals(object, SenderUtil.getSenderId(author));
	}
	
	// -------------------------------------------- //
	// UNSIGN
	// -------------------------------------------- //
	
	public static void unsign(Object object) throws IllegalArgumentException
	{
		setAuthor(object, null);
		setTitle(object, null);
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
	
	public static void clear(Object object) throws IllegalArgumentException
	{
		setAuthor(object, null);
		setTitle(object, null);
		setPages(object, null);
	}
	
}
