package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdBookDelete extends MassiveBooksCommand
{
	public CmdBookDelete()
	{
		super();
		this.addAliases(ConfServer.aliasesBookDelete);
		this.addRequiredArg("title");
		this.setErrorOnToManyArgs(false);
		this.addRequirements(ReqHasPerm.get(Perm.DELETE.node));
	}
	
	@Override
	public void perform()
	{
		MBook mbook = this.argConcatFrom(0, ARMBook.get());
		if (mbook == null) return;
		
		ItemStack target = mbook.getItem();
		mbook.detach();
		
		sendMessage(Lang.getSuccessDelete(target));
	}
}
