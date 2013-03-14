package com.massivecraft.massivebooks.entity;

import com.massivecraft.mcore.store.Entity;

public class MBook extends Entity<MBook, String>
{	
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static MBook get(Object oid)
	{
		return MBookColl.get().get(oid);
	}
	
	//----------------------------------------------//
	// OVERRIDE
	//----------------------------------------------//
	
	@Override
	public MBook load(MBook that)
	{
		// TODO
		
		return this;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	
	
}
