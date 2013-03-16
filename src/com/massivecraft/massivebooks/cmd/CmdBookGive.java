package com.massivecraft.massivebooks.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.mcore.cmd.arg.ARInteger;
import com.massivecraft.mcore.cmd.arg.ARPlayer;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.mixin.Mixin;
import com.massivecraft.mcore.util.InventoryUtil;

public class CmdBookGive extends MassiveBooksCommand
{
	public CmdBookGive()
	{
		super();
		this.addAliases(ConfServer.aliasesBookGive);
		this.addRequiredArg("player");
		this.addRequiredArg("amount");
		this.addRequiredArg("title");
		this.setErrorOnToManyArgs(false);
		this.addRequirements(ReqHasPerm.get(Perm.GIVE.node));
	}
	
	@Override
	public void perform()
	{
		// What player should we give to?
		Player player = this.arg(0, ARPlayer.getStart());
		if (player == null) return;
		
		// How many? or perhaps ensure the player has at least one?
		Integer amount = 1;
		boolean ensure = false;
		if (this.arg(1).toLowerCase().startsWith("e"))
		{
			ensure = true;
		}
		else
		{
			amount = this.arg(1, ARInteger.get());
			if (amount == null) return;
			if (amount <= 0)
			{
				sendMessage(Lang.AMOUNT_MUST_BE_POSITIVE);
				return;
			}
		}
		
		// What books should we give?
		List<MBook> mbooks = new ArrayList<MBook>();
		if (this.argConcatFrom(2).toLowerCase().equals("all"))
		{
			mbooks.addAll(MBookColl.get().getAll());
		}
		else
		{
			MBook singlembook = this.argConcatFrom(2, ARMBook.get());
			if (singlembook == null) return;
			mbooks.add(singlembook);
		}
		
		// Now for each book ...
		for (MBook mbook : mbooks)
		{
			ItemStack item = mbook.getItem();
			PlayerInventory inventory = player.getInventory();
			if (ensure && inventory.containsAtLeast(item, 1))
			{
				sender.sendMessage(Lang.getAlreadyHave(Mixin.getDisplayName(player), item));
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
			
			sender.sendMessage(Lang.getGave("You", Mixin.getDisplayName(player), amount, item));
			player.sendMessage(Lang.getGave(Mixin.getDisplayName(sender), "you", amount, item));
		}
	}
	
	public void give(Player player, int amount, MBook mbook)
	{
		
	}
}
