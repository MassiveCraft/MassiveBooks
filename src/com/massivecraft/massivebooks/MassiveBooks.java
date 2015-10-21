package com.massivecraft.massivebooks;

import com.massivecraft.massivebooks.cmd.CmdBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivebooks.entity.MConfColl;
import com.massivecraft.massivecore.MassivePlugin;

public class MassiveBooks extends MassivePlugin 
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
	private CmdBook cmdBook;
	public CmdBook getCmdBook() { return this.cmdBook; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Version Synchronized
		this.setVersionSynchronized(true);
		
		// Initialize Collections
		MConfColl.get().init();
		MBookColl.get().init();
		
		// Commands
		this.cmdBook = new CmdBook();
		this.cmdBook.register(this);
		
		// Setup Listeners
		EngineMain.get().activate();
		EnginePowertool.get().activate();
		
		postEnable();
	}
	
}
