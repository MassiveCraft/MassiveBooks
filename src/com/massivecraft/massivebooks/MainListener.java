package com.massivecraft.massivebooks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.massivebooks.entity.MConf;
import com.massivecraft.massivebooks.entity.MPlayer;
import com.massivecraft.mcore.mixin.Mixin;

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
	// ITEM FRAME: LOAD AND DISPLAYNAME
	// -------------------------------------------- //
	
	// Can be cancelled but we don't care :P
	@EventHandler(priority = EventPriority.MONITOR)
	public void itemFrameLoadAndDisplayname(PlayerInteractEntityEvent event)
	{
		// If a player is interacting with an item frame ...
		Entity entity = event.getRightClicked();
		if (!(entity instanceof ItemFrame)) return;
		ItemFrame itemFrame = (ItemFrame)entity;
		
		// ... and that item frame contains something ...
		ItemStack item = itemFrame.getItem();
		if (item == null) return;
		if (item.getType() == Material.AIR) return;
		
		// ... check it the player is sneaking ...
		final Player player = event.getPlayer();
		final boolean sneaking = player.isSneaking();
		
		if (this.itemFrameLoad(event, item, player, sneaking)) return;
		if (this.itemFrameDisplayname(event, item, player, sneaking)) return;
		
		// Add more here if you want to in the future :P
	}
	
	public boolean itemFrameLoad(PlayerInteractEntityEvent event, ItemStack itemInFrame, Player player, boolean sneaking)
	{
		// If loading is allowed ...
		if (sneaking == true && !MConf.get().isItemFrameLoadIfSneakTrue()) return false;
		if (sneaking == false && !MConf.get().isItemFrameLoadIfSneakFalse()) return false;
		
		// ... and there is something with BookMeta in the item frame ...
		if (!BookUtil.hasBookMeta(itemInFrame)) return false;
		
		// ... now do different stuff depending on what item the player is holding ...
		ItemStack itemInHand = player.getItemInHand();
	
		// ... if the player is holding a similar item ...
		if (itemInHand.isSimilar(itemInFrame))
		{
			// ... do unload ...
			ItemStack target = new ItemStack(itemInHand);
			BookUtil.clear(target);
			player.setItemInHand(target);
			
			// ... and inform.
			player.sendMessage(Lang.getFrameUnload(itemInHand));
		}
		// ... else if the player is holding a clear book and quill ...
		else if (BookUtil.isCleared(itemInHand))
		{
			// ... do unload ...
			ItemStack target = new ItemStack(itemInFrame);
			target.setAmount(itemInHand.getAmount());
			player.setItemInHand(target);
			
			// ... and inform.
			player.sendMessage(Lang.getFrameLoad(target));
		}
		// ... else if the player is holding an educated but invalid guess ...
		else if (itemInHand.getType() == Material.WRITTEN_BOOK || itemInHand.getType() == Material.BOOK)
		{
			// ... do help.
			player.sendMessage(Lang.getFrameHelp());
		}
		
		// ... then cancel to stop rotation ...
		event.setCancelled(true);
		
		// ... and return true which means that no displayname info should be sent.
		return true;
	}
	
	public boolean itemFrameDisplayname(PlayerInteractEntityEvent event, ItemStack itemInFrame, Player player, boolean sneaking)
	{
		// If displayname is allowed ...
		if (sneaking == true && !MConf.get().isItemFrameDisplaynameIfSneakTrue()) return false;
		if (sneaking == false && !MConf.get().isItemFrameDisplaynameIfSneakFalse()) return false;
		
		// ... and there is something with displayname in the item frame ...
		if (!itemInFrame.hasItemMeta()) return false;
		ItemMeta meta = itemInFrame.getItemMeta();
		if (!meta.hasDisplayName()) return false;
		String displayname = meta.getDisplayName();
		
		// ... and inform on what the frame contains.
		player.sendMessage(Lang.getFrameContains(displayname));
		return true;
	}
	
	// -------------------------------------------- //
	// ITEM FRAME: ROTATE
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void itemFrameRotate(PlayerInteractEntityEvent event)
	{
		// If a player is interacting with an item frame ...
		Entity entity = event.getRightClicked();
		if (!(entity instanceof ItemFrame)) return;
		ItemFrame itemFrame = (ItemFrame)entity;
		
		// ... and that item frame contains something ...
		ItemStack item = itemFrame.getItem();
		if (item == null) return;
		if (item.getType() == Material.AIR) return;
		
		// ... possibly ...
		final Player player = event.getPlayer();
		final boolean sneaking = player.isSneaking();
		if (sneaking == true && MConf.get().isItemFrameRotateIfSneakTrue()) return;
		if (sneaking == false && MConf.get().isItemFrameRotateIfSneakFalse()) return;
		
		// ... stop rotation.
		event.setCancelled(true);
	}
	
	// -------------------------------------------- //
	// AUTOUPDATE: JOIN WARN OR TOGGLE 
	// -------------------------------------------- //
	
	// Can't be cancelled
	@EventHandler(priority = EventPriority.NORMAL)
	public void autoupdateJoinWarnOrToggle(PlayerJoinEvent event)
	{
		if (!Mixin.isActualJoin(event)) return;
		
		final Player player = event.getPlayer();
		MPlayer mplayer = MPlayer.get(player);
		if (mplayer.isUsingAutoUpdate()) return;
		
		if (Perm.AUTOUPDATE.has(player, false))
		{
			// Warn
			player.sendMessage(Lang.AUTOUPDATE_JOINWARN);
		}
		else
		{
			// Toggle
			mplayer.setUsingAutoUpdate(true, true, false);
		}
		
	}
	
	// -------------------------------------------- //
	// AUTOUPDATE: PERFORM 
	// -------------------------------------------- //
	// MassiveBooks autoupdates books. What does that mean?
	// The item displayname is updated to contain the display name of the author player.
	// Sice the player displayname may change so must the displayname of the book.
	// Additionally books that have the same title as a saved book recieves complete content updates.
	// This way you can always be sure those special books of yours never exists in multiple versions.
	// Saved a new version of the server rule book? All old instances will be updated on the fly.
	
	// Can be cancelled but we don't care :P
	@EventHandler(priority = EventPriority.LOWEST)
	public void updatePerform(PlayerInteractEntityEvent event)
	{
		Entity entity = event.getRightClicked();
		if (!(entity instanceof ItemFrame)) return;
		ItemFrame itemFrame = (ItemFrame)entity;
		
		final Player player = event.getPlayer();
		MPlayer mplayer = MPlayer.get(player);
		if (!mplayer.isUsingAutoUpdate()) return;
		
		BookUtil.updateBook(itemFrame);
	}
	
	// Can't be cancelled
	@EventHandler(priority = EventPriority.LOWEST)
	public void updatePerform(PlayerItemHeldEvent event)
	{
		final Player player = event.getPlayer();
		MPlayer mplayer = MPlayer.get(player);
		if (!mplayer.isUsingAutoUpdate()) return;
		
		BookUtil.updateBooks(player);
	}
	
	// Can be cancelled but we don't care :P
	@EventHandler(priority = EventPriority.LOWEST)
	public void updatePerform(PlayerPickupItemEvent event)
	{
		final Player player = event.getPlayer();
		MPlayer mplayer = MPlayer.get(player);
		if (!mplayer.isUsingAutoUpdate()) return;
		
		BookUtil.updateBook(event.getItem());
	}
	
	// Can be cancelled but we don't care :P
	@EventHandler(priority = EventPriority.LOWEST)
	public void updatePerform(InventoryClickEvent event)
	{
		final HumanEntity human = event.getWhoClicked();
		if (!(human instanceof Player)) return;
		final Player player = (Player)human;
		MPlayer mplayer = MPlayer.get(player);
		if (!mplayer.isUsingAutoUpdate()) return;
		
		BookUtil.updateBooks(event.getInventory());
		BookUtil.updateBooks(event.getWhoClicked());
	}
	
	// Can be cancelled but we don't care :P
	@EventHandler(priority = EventPriority.LOWEST)
	public void updatePerform(InventoryOpenEvent event)
	{
		final HumanEntity human = event.getPlayer();
		if (!(human instanceof Player)) return;
		final Player player = (Player)human;
		MPlayer mplayer = MPlayer.get(player);
		if (!mplayer.isUsingAutoUpdate()) return;
		
		BookUtil.updateBooks(event.getInventory());
		BookUtil.updateBooks(player);
	}
	
}
