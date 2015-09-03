package com.massivecraft.massivebooks.cmd;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.ARBookInHand;
import com.massivecraft.massivebooks.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.money.Money;
import com.massivecraft.massivecore.util.InventoryUtil;

public class CmdBookCopy extends MassiveBooksCommand
{
	public CmdBookCopy()
	{
		this.addAliases("copy");
		
		this.addArg(1, ARInteger.get(), "times", "1");
		
		this.addRequirements(ReqHasPerm.get(Perm.COPY.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Get item arg
		ItemStack item = ARBookInHand.getWritten().read(sender);
		BookUtil.updateBook(item);
		
		item = item.clone();
		item.setAmount(1);
		
		// Get times arg
		int times = this.readArg();
		if (times <= 0) throw new MassiveException().addMessage(Lang.TIMES_MUST_BE_POSITIVE);

		// Has right to copy?
		if (!BookUtil.hasCopyPerm(item, sender, true)) return;
		
		// What do we require?
		double moneyRequired = times * MConf.get().getCopyCost(me);
		int booksRequired = times;
		int inksacsRequired = times;
		int feathersRequired = times;
		int roomRequired = times;
		
		if (me.getGameMode() == GameMode.CREATIVE)
		{
			moneyRequired = 0;
			booksRequired = 0;
			inksacsRequired = 0;
			feathersRequired = 0;
			// roomRequired = 0; // Nope, since the room always is required. It's not really a resource cost.
		}
		
		// The inventory in question
		Inventory inventory = me.getInventory();
		
		// Check ...
		
		// ... money (this is only a preliminary check)
		if (Money.enabled())
		{
			if (!Money.has(me, moneyRequired))
			{
				double moneyPossesed = Money.get(me);
				double moneyMissing = moneyRequired - moneyPossesed;
				this.sendCheckFailMessage(Lang.RESOURCE_MONEY, moneyRequired, moneyPossesed, moneyMissing);
				return;
			}
		}
		
		// ... books (the actual check)
		int booksPossesed = InventoryUtil.countSimilar(inventory, new ItemStack(Material.BOOK));
		if (!this.checkResource(Lang.RESOURCE_BOOKS, booksRequired, booksPossesed)) return;
		
		// ... inksacs (the actual check)
		int inksacsPossesed = InventoryUtil.countSimilar(inventory, new ItemStack(Material.INK_SACK));
		if (!this.checkResource(Lang.RESOURCE_INKSACS, inksacsRequired, inksacsPossesed)) return;
		
		// ... feathers (the actual check)
		int feathersPossesed = InventoryUtil.countSimilar(inventory, new ItemStack(Material.FEATHER));
		if (!this.checkResource(Lang.RESOURCE_FEATHERS, feathersRequired, feathersPossesed)) return;
		
		// ... room (the actual check)
		int roomPossesed = InventoryUtil.roomLeft(me.getInventory(), item, roomRequired);
		if (!this.checkResource(Lang.RESOURCE_ROOM, roomRequired, roomPossesed)) return;
		
		// Remove ...
		
		// ... money (real check here)
		if (Money.enabled() && moneyRequired != 0)
		{
			if (!Money.despawn(me, me, moneyRequired, "MassiveBooks"))
			{
				message(String.format(Lang.FAILED_TO_REMOVE_X, Lang.RESOURCE_MONEY));
				return;
			}
		}
		
		// ... books (assumed to succeed)
		inventory.removeItem(new ItemStack(Material.BOOK, booksRequired));
		
		// ... inksacs (assumed to succeed)
		inventory.removeItem(new ItemStack(Material.INK_SACK, inksacsRequired));
		
		// ... feathers (assumed to succeed)
		inventory.removeItem(new ItemStack(Material.FEATHER, feathersRequired));
		
		// ... room (assumed to succeed)
		// (add book copies)
		InventoryUtil.addItemTimes(me.getInventory(), item, times);
		
		// Inform		
		message(Lang.getSuccessCopyCopies(times));
		message(Lang.getSuccessCopyResources(moneyRequired, booksRequired, inksacsRequired, feathersRequired));
	}
	
	// -------------------------------------------- //
	// CHECK UTILITIES
	// -------------------------------------------- //
	
	public boolean checkResource(String resourceName, Integer required, Integer possessed)
	{
		int missing = required - possessed;
		if (missing <= 0) return true;
		this.sendCheckFailMessage(resourceName, required, possessed, missing);
		return false;
	}
	
	public void sendCheckFailMessage(String resourceName, Object required, Object possessed, Object missing)
	{
		String notEnoughMessage = String.format(Lang.NOT_ENOUGH_X, resourceName); 
		this.message(notEnoughMessage);
		String reportMessage = String.format(Lang.REQUIRED_X_POSSESSED_Y_MISSING_Z, required, possessed, missing);
		this.message(reportMessage);
	}
	
}
