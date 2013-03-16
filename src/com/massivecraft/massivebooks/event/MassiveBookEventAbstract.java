package com.massivecraft.massivebooks.event;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public abstract class MassiveBookEventAbstract extends Event implements Runnable, Serializable
{
	// -------------------------------------------- //
	// SERIAL
	// -------------------------------------------- //
	
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public void run()
	{
		Bukkit.getPluginManager().callEvent(this);
	}
	
}