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
	public MassiveBooks()
	{
		MassiveBooks.i = this;
		
		// Version Synchronized
		this.setVersionSynchronized(true);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnableInner()
	{
		// Activate
		this.activate(
			// Coll
			MConfColl.get(),
			MBookColl.get(),
		
			// Command
			CmdBook.get(),
		
			// Engine
			EngineMain.get(),
			EnginePowertool.get()
		);
	}
	
}
