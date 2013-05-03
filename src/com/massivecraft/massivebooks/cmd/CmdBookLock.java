package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBookLock extends MassiveBooksCommand
{
	public CmdBookLock()
	{
		this.addAliases(ConfServer.aliasesBookLock);
		
		this.addRequirements(ReqHasPerm.get(Perm.LOCK.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		ItemStack item = this.arg(ARBookInHand.getEither());
		if (item == null) return;
		
		if (BookUtil.isLocked(item))
		{
			sendMessage(Lang.getSameLock(item));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.LOCK_OTHER.has(sender, true)) return;
		
		ItemStack before = item.clone();
		BookUtil.lock(item);
		me.setItemInHand(item);
		
		sendMessage(Lang.getAlterLock(before));
	}
}
