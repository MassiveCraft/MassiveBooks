package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.TypeBookInHand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;

public class CmdBookLock extends MassiveBooksCommand
{
	public CmdBookLock()
	{
		// Aliases
		this.addAliases("lock");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.LOCK.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		ItemStack item = TypeBookInHand.getEither().read(sender);
		
		if (BookUtil.isLocked(item))
		{
			message(Lang.getSameLock(item));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.LOCK_OTHER.has(sender, true)) return;
		
		ItemStack before = item.clone();
		BookUtil.lock(item);
		me.setItemInHand(item);
		
		message(Lang.getAlterLock(before));
	}

}
