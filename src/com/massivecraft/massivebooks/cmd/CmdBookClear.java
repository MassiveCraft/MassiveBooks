package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.type.TypeBookInHand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;

public class CmdBookClear extends MassiveBooksCommand
{
	public CmdBookClear()
	{
		// Aliases
		this.addAliases("clear");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.CLEAR.node));
		this.addRequirements(RequirementIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		ItemStack item = TypeBookInHand.getEither().read(sender);
		
		if (BookUtil.isCleared(item))
		{
			message(Lang.getSameClear(item));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.CLEAR_OTHER.has(sender, true)) return;
		
		ItemStack before = item.clone();
		BookUtil.clear(item);
		me.setItemInHand(item);
		
		message(Lang.getAlterClear(before));
	}
}
