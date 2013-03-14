package com.massivecraft.massivebooks;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class MainListener implements Listener
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MainListener i = new MainListener();
	public static MainListener get() { return i; }
	public MainListener() {}
	
	// -------------------------------------------- //
	// SETUP
	// -------------------------------------------- //
	
	public void setup()
	{
		Bukkit.getPluginManager().registerEvents(this, MassiveBooks.get());
	}
	
	// -------------------------------------------- //
	// DERP ÜNT FROOD
	// -------------------------------------------- //
	
	
}
