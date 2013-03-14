package com.massivecraft.massivebooks;

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
		
		// Commands
		
		// Listeners
		MainListener.get().setup();
		
		postEnable();
	}
	
}
