package com.massivecraft.massivebooks.entity;

import com.massivecraft.massivebooks.Const;
import com.massivecraft.massivebooks.MassiveBooks;
import com.massivecraft.mcore.MCore;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;

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
		MConf.i = this.get(MCore.INSTANCE, true);
		this.createUpdatePermissionNodes();
	}
	
	@Override
	public synchronized void loadFromRemote(Object oid)
	{
		super.loadFromRemote(oid);
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
