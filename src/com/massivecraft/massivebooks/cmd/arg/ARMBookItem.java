package com.massivecraft.massivebooks.cmd.arg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARAbstract;
import com.massivecraft.massivecore.cmd.arg.ARAllAble;

public class ARMBookItem extends ARAbstract<ItemStack> implements ARAllAble<ItemStack>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ARMBookItem i = new ARMBookItem();
	public static ARMBookItem get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public ItemStack read(String arg, CommandSender sender) throws MassiveException
	{
		MBook mbook = ARMBook.get().read(arg, sender);
		return mbook.getItem();
	}
	
	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return ARMBook.get().getTabList(sender, arg);
	}

	@Override
	public Collection<ItemStack> getAll(CommandSender sender)
	{
		List<ItemStack> ret = new ArrayList<ItemStack>();
		
		for (MBook mbook : MBookColl.get().getAll())
		{
			ret.add(mbook.getItem());
		}
		
		return ret;
	}
	
}
