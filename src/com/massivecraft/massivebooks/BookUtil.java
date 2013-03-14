package com.massivecraft.massivebooks;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.mcore.util.SenderUtil;

public class BookUtil
{
	/**
	 * @param item The item to create an unsigned copy of
	 * @return a copy of the item without author and title
	 * @throws NullPointerException if the item parameter is null
	 * @throws IllegalStateException if the item doesn't have {@link org.bukkit.inventory.meta.BookMeta}.
	 */
	public static ItemStack createUnsigned(ItemStack item) throws NullPointerException, IllegalStateException
	{
		if (item == null) throw new NullPointerException("item was null");
		ItemStack ret = item.clone();
		ItemMeta meta = ret.getItemMeta();
		if (!(meta instanceof BookMeta)) throw new IllegalStateException("item doesn't have BookMeta");
		BookMeta bookMeta = (BookMeta)meta;
		bookMeta.setAuthor(null);
		bookMeta.setTitle(null);
		ret.setItemMeta(bookMeta);
		return ret;
	}
	
	/**
	 * @param authorable The object to extract author id from
	 * @return The author field from the book meta in case there is one
	 */
	public static String getAuthorId(Object authorable)
	{
		if (!(authorable instanceof ItemStack)) return null;
		ItemStack item = (ItemStack)authorable;
		
		ItemMeta meta = item.getItemMeta();
		if (!(meta instanceof BookMeta)) return null;
		BookMeta bookMeta = (BookMeta)meta;
		
		if (!bookMeta.hasAuthor()) return null;
		return bookMeta.getAuthor();
	}
	
	public static boolean isAuthorOf(CommandSender sender, Object authorable) throws NullPointerException
	{
		if (sender == null) throw new NullPointerException("sender was null");
		if (authorable == null) throw new NullPointerException("authorable was null");
		
		String senderId = SenderUtil.getSenderId(sender);
		String authorId = getAuthorId(authorable);
		
		return senderId.equalsIgnoreCase(authorId);
	}
	
}
