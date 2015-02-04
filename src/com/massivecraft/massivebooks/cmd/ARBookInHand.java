package com.massivecraft.massivebooks.cmd;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivecore.cmd.MassiveCommandException;
import com.massivecraft.massivecore.cmd.arg.ArgReaderAbstract;
import com.massivecraft.massivecore.util.IdUtil;

public class ARBookInHand extends ArgReaderAbstract<ItemStack>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public static final ARBookInHand WRITTEN = new ARBookInHand(true, false);
	public static ARBookInHand getWritten() { return WRITTEN; }
	
	public static final ARBookInHand QUILL = new ARBookInHand(false, true);
	public static ARBookInHand getQuill() { return QUILL; }
	
	public static final ARBookInHand EITHER = new ARBookInHand(true, true);
	public static ARBookInHand getEither() { return EITHER; }
	
	private ARBookInHand(boolean acceptingWrittenBook, boolean acceptingBookAndQuill)
	{
		this.acceptingWrittenBook = acceptingWrittenBook;
		this.acceptingBookAndQuill = acceptingBookAndQuill;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private final boolean acceptingWrittenBook;
	public boolean isAcceptingWrittenBook() { return this.acceptingWrittenBook; }
	
	private final boolean acceptingBookAndQuill;
	public boolean isAcceptingBookAndQuill() { return this.acceptingBookAndQuill; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public ItemStack read(String arg, CommandSender sender) throws MassiveCommandException
	{
		ItemStack ret = this.getItemStack(sender);
		if (ret != null) return ret;
		
		throw new MassiveCommandException().addMessage(this.getError());
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public String getAcceptedItemsDesc()
	{
		if (this.acceptingWrittenBook && this.acceptingBookAndQuill) return Lang.ACCEPTED_ITEMS_EITHER;
		if (this.acceptingWrittenBook) return Lang.ACCEPTED_ITEMS_WRITTEN;
		return Lang.ACCEPTED_ITEMS_QUILL;
	}
	
	public String getError()
	{
		return String.format(Lang.BOOK_IN_HAND_ERROR_TEMPLATE, this.getAcceptedItemsDesc());
	}
	
	public ItemStack getItemStack(CommandSender sender)
	{
		Player player = IdUtil.getAsPlayer(sender);
		if (player == null) return null;
		
		ItemStack ret = player.getItemInHand();
		if (ret == null) return null;
		
		Material type = ret.getType();
		if (type == Material.WRITTEN_BOOK && this.isAcceptingWrittenBook()) return ret;
		if (type == Material.BOOK_AND_QUILL && this.isAcceptingBookAndQuill()) return ret;
		
		return null;
	}

}
