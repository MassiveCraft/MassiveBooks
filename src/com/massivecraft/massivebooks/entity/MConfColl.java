package com.massivecraft.massivebooks.entity;

import java.util.Map.Entry;

import com.massivecraft.massivebooks.Const;
import com.massivecraft.massivebooks.MassiveBooks;
import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;
import com.massivecraft.massivecore.store.MStore;
import com.massivecraft.massivecore.xlib.gson.JsonElement;

public class MConfColl extends Coll<MConf>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MConfColl i = new MConfColl();
	public static MConfColl get() { return i; }
	private MConfColl()
	{
		super(Const.COLLECTION_MCONF, MConf.class, MStore.getDb(), MassiveBooks.get());
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void init()
	{
		super.init();
		MConf.i = this.get(MassiveCore.INSTANCE, true);
		this.createUpdatePermissionNodes();
	}
	
	@Override
	public synchronized void loadFromRemoteFixed(String id, Entry<JsonElement, Long> entry)
	{
		super.loadFromRemoteFixed(id, entry);
		if ( ! this.inited()) return;
		this.createUpdatePermissionNodes();
	}
	
	// -------------------------------------------- //
	// EXTRAS
	// -------------------------------------------- //
	
	public void createUpdatePermissionNodes()
	{
		for (MConf mconf : this.getAll())
		{
			mconf.createUpdatePermissionNodes();
		}
	}
	
}
