package com.massivecraft.massivebooks.entity;

import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Entity;

public class MConf extends Entity<MConf, String>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static MConf get()
	{
		return MConfColl.get().get(MCore.INSTANCE);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public MConf load(MConf that)
	{
		// TODO
		
		return this;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	

}