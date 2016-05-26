package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.type.TypeBookInHand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.util.InventoryUtil;

public class CmdBookLock extends MassiveBooksCommand
{
	public CmdBookLock()
	{
		// Aliases
		this.addAliases("lock");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.LOCK));
		this.addRequirements(RequirementIsPlayer.get());
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
		InventoryUtil.setWeapon(me, item);
		
		message(Lang.getAlterLock(before));
	}

}
