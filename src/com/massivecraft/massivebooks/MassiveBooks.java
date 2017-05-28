package com.massivecraft.massivebooks;

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
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnableInner()
	{
		// Activate
		this.activateAuto();
	}
	
}
