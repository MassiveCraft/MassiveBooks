package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.type.TypeBookInHand;
import com.massivecraft.massivebooks.cmd.type.TypeMBook;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;

public class CmdBookLoad extends MassiveBooksCommand
{
	public CmdBookLoad()
	{
		// Aliases
		this.addAliases("load");
		
		// Parameters
		this.addParameter(TypeMBook.get(), "title", true);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.LOAD.node));
		this.addRequirements(RequirementIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MBook mbook = this.readArg();
		ItemStack old = TypeBookInHand.getEither().read(sender);
		
		ItemStack target = mbook.getItem();
		target.setAmount(old.getAmount());
		
		me.setItemInHand(target);
		
		message(Lang.getSuccessLoad(target));
	}

}
