package com.massivecraft.massivebooks.entity;

import com.massivecraft.mcore.store.SenderEntity;

public class MPlayer extends SenderEntity<MPlayer>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static MPlayer get(Object oid)
	{
		return MPlayerColl.get().get(oid);
	}
	
	//----------------------------------------------//
	// OVERRIDE
	//----------------------------------------------//
	
	@Override
	public MPlayer load(MPlayer that)
	{
		this.usingAutoUpdate = that.usingAutoUpdate;
		
		return this;
	}
	
	@Override
	public boolean isDefault()
	{
		if (this.usingAutoUpdate != true) return false;
		return true;
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private boolean usingAutoUpdate = true;
	public boolean isUsingAutoUpdate() { return this.usingAutoUpdate; }
	public void setUsingAutoUpdate(boolean usingAutoUpdate) { this.usingAutoUpdate = usingAutoUpdate; }

}