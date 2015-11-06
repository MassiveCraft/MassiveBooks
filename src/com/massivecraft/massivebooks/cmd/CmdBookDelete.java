package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.type.TypeMBook;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

public class CmdBookDelete extends MassiveBooksCommand
{
	public CmdBookDelete()
	{
		this.addAliases("delete");
		
		this.addParameter(TypeMBook.get(), "title", true);
		
		this.addRequirements(RequirementHasPerm.get(Perm.DELETE.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		MBook mbook = this.readArg();
		
		ItemStack target = mbook.getItem();
		mbook.detach();
		
		message(Lang.getSuccessDelete(target));
	}

}
