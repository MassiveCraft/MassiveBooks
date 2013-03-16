package com.massivecraft.massivebooks.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class MassiveBooksPowertoolReplaceEvent extends MassiveBooksEventAbstractCancellable
{
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	
	private static final HandlerList handlers = new HandlerList();
	@Override public HandlerList getHandlers() { return handlers; }
	public static HandlerList getHandlerList() { return handlers; }
	
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	
	private final PlayerInteractEvent interactEvent;
	public PlayerInteractEvent getInteractEvent() { return this.interactEvent; }
	
	private final PlayerInteractEntityEvent interactEntityEvent;
	public PlayerInteractEntityEvent getInteractEntityEvent() { return this.interactEntityEvent; }
	
	private final String replacee;
	public String getReplacee() { return this.replacee; }
	
	private String replacement = null;
	public String getReplacement() { return this.replacement; }
	public void setReplacement(String replacement) { this.replacement = replacement; }
	
	private String error = null;
	public String getError() { return this.error; }
	public void setError(String error) { this.error = error; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public MassiveBooksPowertoolReplaceEvent(PlayerInteractEvent interactEvent, PlayerInteractEntityEvent interactEntityEvent, String replacee)
	{
		this.interactEvent = interactEvent;
		this.interactEntityEvent = interactEntityEvent;
		this.replacee = replacee;
	}

}
