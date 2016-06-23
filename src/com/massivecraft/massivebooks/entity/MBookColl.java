package com.massivecraft.massivebooks.entity;

import org.bukkit.ChatColor;

import com.massivecraft.massivecore.store.Coll;

public class MBookColl extends Coll<MBook>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MBookColl i = new MBookColl();
	public static MBookColl get() { return i; }

	// -------------------------------------------- //
	// STACK TRACEABILITY
	// -------------------------------------------- //
	
	@Override
	public void onTick()
	{
		super.onTick();
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String fixId(Object oid)
	{
		String ret = super.fixId(oid);
		if (ret == null) return ret;
		return ChatColor.stripColor(ret.trim().toLowerCase());
	}
	
}
