package com.massivecraft.massivebooks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
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
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

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
	// ITEM FRAME LOADING
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void itemFrameLoading(PlayerInteractEntityEvent event)
	{
		// If a player is interacting with an item frame ...
		Entity entity = event.getRightClicked();
		if (!(entity instanceof ItemFrame)) return;
		ItemFrame itemFrame = (ItemFrame)entity;
		
		// ... and the player isn't sneaking ...
		final Player player = event.getPlayer();
		if (player.isSneaking()) return;
		
		// ... and there is something with BookMeta in the item frame ...
		ItemStack itemFrameItem = itemFrame.getItem();
		if (!BookUtil.hasBookMeta(itemFrameItem)) return;
		
		// ... cancel to stop rotation ...
		event.setCancelled(true);
		
		// ... then if the player is holding something with BookMeta ...
		ItemStack itemInHand = player.getItemInHand();
		if (BookUtil.hasBookMeta(itemInHand))
		{
			// ... do load the content into the hand of the player ...
			ItemStack target = new ItemStack(itemFrameItem);
			target.setAmount(itemInHand.getAmount());
			BookUtil.updateBook(target);
			player.setItemInHand(target);
			
			// ... and inform of the successful frameload.
			player.sendMessage(Lang.getSuccessFrameload(target));
		}
		else
		{
			// ... otherwise inform the player that this feature exist.
			player.sendMessage(Lang.getFrameContains(itemFrameItem));
		}
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
	@EventHandler(priority = EventPriority.LOWEST)
	public void updateBookDisplayNames(PlayerItemHeldEvent event)
	{
		BookUtil.updateBooks(event.getPlayer());
	}
	
	// Can be cancelled but we don't care :P
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void updateBookDisplayNames(PlayerPickupItemEvent event)
	{
		BookUtil.updateBook(event.getItem());
	}
	
	// Can be cancelled but we don't care :P
	@EventHandler(priority = EventPriority.LOWEST)
	public void updateBookDisplayNames(InventoryClickEvent event)
	{
		BookUtil.updateBooks(event.getInventory());
		BookUtil.updateBooks(event.getWhoClicked());
	}
	
	// Can be cancelled but we don't care :P
	@EventHandler(priority = EventPriority.LOWEST)
	public void updateBookDisplayNames(InventoryOpenEvent event)
	{
		BookUtil.updateBooks(event.getInventory());
		BookUtil.updateBooks(event.getPlayer());
	}
	
}
