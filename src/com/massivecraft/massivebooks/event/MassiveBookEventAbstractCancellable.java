package com.massivecraft.massivebooks.event;

import org.bukkit.event.Cancellable;

public abstract class MassiveBookEventAbstractCancellable extends MassiveBookEventAbstract implements Cancellable
{
	// -------------------------------------------- //
	// SERIAL
	// -------------------------------------------- //
	
	private static final long serialVersionUID = 1L;

	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private boolean cancelled = false;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean isCancelled()
	{
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;		
	}
	
}
