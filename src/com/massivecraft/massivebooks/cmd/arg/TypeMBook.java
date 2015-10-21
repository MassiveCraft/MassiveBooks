package com.massivecraft.massivebooks.cmd.arg;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivecore.cmd.type.TypeAbstractSelect;

public class TypeMBook extends TypeAbstractSelect<MBook>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeMBook i = new TypeMBook();
	public static TypeMBook get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String getTypeName()
	{
		return "saved book";
	}

	@Override
	public MBook select(String str, CommandSender sender)
	{
		return MBook.get(str);
	}

	@Override
	public Collection<String> altNames(CommandSender sender)
	{
		return MBookColl.get().getIds();
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return this.altNames(sender);
	}

}
