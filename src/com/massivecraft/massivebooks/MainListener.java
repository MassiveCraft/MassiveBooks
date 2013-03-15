package com.massivecraft.massivebooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

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
	// POWERTOOL
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void powertool(PlayerInteractEvent event)
	{
		// If a player is left-clicking something ...
		Action action = event.getAction();
		if (action != Action.LEFT_CLICK_AIR && action != Action.LEFT_CLICK_BLOCK) return;
		
		// ... attempt a powertool use.
		BookUtil.powertoolUse(event, event.getPlayer(), null);
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void powertool(PlayerInteractEntityEvent event)
	{
		// If a player is interacting with another player ...
		Entity entity = event.getRightClicked();
		if (!(entity instanceof Player)) return;
		Player you = (Player)entity;
		
		// ... attempt a powertool use.
		BookUtil.powertoolUse(event, event.getPlayer(), you);
	}
	
	// -------------------------------------------- //
	// UPDATE BOOK DISPLAY NAMES
	// -------------------------------------------- //
	// The custom book naming system makes use of display names and other volatile information.
	// Thus we attempt to update the display names kinda often!
	
	// Can't be cancelled
	@EventHandler(priority = EventPriority.LOW)
	public void updateBookDisplayNames(PlayerItemHeldEvent event)
	{
		BookUtil.updateDisplayNames(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void updateBookDisplayNames(InventoryOpenEvent event)
	{
		BookUtil.updateDisplayNames(event.getInventory());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void updateBookDisplayNames(InventoryClickEvent event)
	{
		BookUtil.updateDisplayNames(event.getInventory());
	}
	
}
