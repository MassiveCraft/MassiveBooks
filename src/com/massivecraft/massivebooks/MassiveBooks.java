package com.massivecraft.massivebooks;

import com.massivecraft.massivebooks.cmd.CmdBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivebooks.entity.MConfColl;
import com.massivecraft.mcore.MPlugin;

public class MassiveBooks extends MPlugin 
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MassiveBooks i;
	public static MassiveBooks get() { return i; }
	public MassiveBooks() { MassiveBooks.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Commands
	private CmdBook cmdBook = new CmdBook();
	public CmdBook getCmdBook() { return this.cmdBook; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Load server config
		ConfServer.i.load();
		
		// Initialize Collections
		MConfColl.get().init();
		MBookColl.get().init();
		
		// Commands
		this.getCmdBook().register(this, true);
		
		// Listeners
		MainListener.get().setup();
		
		postEnable();
	}
	
}
