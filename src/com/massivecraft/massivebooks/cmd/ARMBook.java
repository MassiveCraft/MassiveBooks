package com.massivecraft.massivebooks.cmd;

import java.util.Collection;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivecore.cmd.arg.ARAbstractSelect;

public class ARMBook extends ARAbstractSelect<MBook>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static ARMBook i = new ARMBook();
	public static ARMBook get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String typename()
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

}
