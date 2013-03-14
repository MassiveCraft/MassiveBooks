package com.massivecraft.massivebooks.entity;

import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Const;
import com.massivecraft.massivebooks.MassiveBooks;
import com.massivecraft.mcore.store.Coll;
import com.massivecraft.mcore.store.MStore;

public class MConfColl extends Coll<MConf, String>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MConfColl i = new MConfColl();
	public static MConfColl get() { return i; }
	private MConfColl()
	{
		super(MStore.getDb(ConfServer.dburi), MassiveBooks.get(), "ai", Const.COLLECTION_BASENAME_CONF, MConf.class, String.class, true);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void init()
	{
		super.init();
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
