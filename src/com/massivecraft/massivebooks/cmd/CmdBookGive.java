package com.massivecraft.massivebooks.cmd;

import java.util.Collection;
import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.ARBookAmount;
import com.massivecraft.massivebooks.cmd.arg.ARMBookItem;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARAll;
import com.massivecraft.massivecore.cmd.arg.ARPlayer;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.util.InventoryUtil;

public class CmdBookGive extends MassiveBooksCommand
{
	public CmdBookGive()
	{
		// Aliases
		this.addAliases("give");
		
		// Args
		this.addArg(ARPlayer.get(), true, "player", "you");
		this.addArg(ARBookAmount.get(), "amount", "1");
		this.addArg(ARAll.get(ARMBookItem.get()), "title", "*bookandquill*", true);
		
		this.addRequirements(ReqHasPerm.get(Perm.GIVE.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		Player player = this.readArg(me);
		
		// How many? or perhaps ensure the player has at least one?
		Integer amount = this.readArg(1);
		boolean ensure = amount == ARBookAmount.ENSURE;
		if (ensure) amount = 1;
		
		// What items should we give?
		Collection<ItemStack> items = this.readArg(Collections.singleton(new ItemStack(Material.BOOK_AND_QUILL)));
		
		// Now for each item ...
		for (ItemStack item : items)
		{
			PlayerInventory inventory = player.getInventory();
			if (ensure && inventory.containsAtLeast(item, 1))
			{
				sender.sendMessage(Lang.getAlreadyHave(Mixin.getDisplayName(player, sender), item));
				player.sendMessage(Lang.getAlreadyHave("You", item));
				continue;
			}
			
			if (InventoryUtil.roomLeft(inventory, item, amount) < amount)
			{
				sender.sendMessage(Lang.getNotEnoughRoomFor(amount, item));
				player.sendMessage(Lang.getNotEnoughRoomFor(amount, item));
				continue;
			}
			
			InventoryUtil.addItemTimes(inventory, item, amount);
			
			sender.sendMessage(Lang.getGave("You", Mixin.getDisplayName(player, sender), amount, item));
			player.sendMessage(Lang.getGave(Mixin.getDisplayName(sender, player), "you", amount, item));
		}
	}

}
