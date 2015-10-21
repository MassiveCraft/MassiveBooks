package com.massivecraft.massivebooks.cmd.arg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.type.TypeAbstract;
import com.massivecraft.massivecore.cmd.type.TypeAllAble;

public class TypeMBookItem extends TypeAbstract<ItemStack> implements TypeAllAble<ItemStack>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeMBookItem i = new TypeMBookItem();
	public static TypeMBookItem get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public ItemStack read(String arg, CommandSender sender) throws MassiveException
	{
		MBook mbook = TypeMBook.get().read(arg, sender);
		return mbook.getItem();
	}
	
	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return TypeMBook.get().getTabList(sender, arg);
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
