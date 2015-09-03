package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.ARMBook;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;

public class CmdBookDelete extends MassiveBooksCommand
{
	public CmdBookDelete()
	{
		this.addAliases("delete");
		
		this.addArg(ARMBook.get(), "title", true);
		
		this.addRequirements(ReqHasPerm.get(Perm.DELETE.node));
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
