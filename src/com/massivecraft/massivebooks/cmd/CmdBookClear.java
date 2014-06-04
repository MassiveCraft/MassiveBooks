package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;

public class CmdBookClear extends MassiveBooksCommand
{
	public CmdBookClear()
	{
		this.addAliases("clear");
		
		this.addRequirements(ReqHasPerm.get(Perm.CLEAR.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		ItemStack item = this.arg(ARBookInHand.getEither());
		if (item == null) return;
		
		if (BookUtil.isCleared(item))
		{
			sendMessage(Lang.getSameClear(item));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.CLEAR_OTHER.has(sender, true)) return;
		
		ItemStack before = item.clone();
		BookUtil.clear(item);
		me.setItemInHand(item);
		
		sendMessage(Lang.getAlterClear(before));
	}
}
