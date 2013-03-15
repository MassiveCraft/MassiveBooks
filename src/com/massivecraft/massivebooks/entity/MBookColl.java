package com.massivecraft.massivebooks.entity;

import org.bukkit.ChatColor;

import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Const;
import com.massivecraft.massivebooks.MassiveBooks;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;

public class MBookColl extends Coll<MBook, String>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MBookColl i = new MBookColl();
	public static MBookColl get() { return i; }
	private MBookColl()
	{
		super(MStore.getDb(ConfServer.dburi), MassiveBooks.get(), "ai", Const.COLLECTION_BASENAME_BOOK, MBook.class, String.class, false);
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
