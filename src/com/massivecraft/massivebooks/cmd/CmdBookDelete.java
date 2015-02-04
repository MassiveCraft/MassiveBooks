package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivecore.cmd.MassiveCommandException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;

public class CmdBookDelete extends MassiveBooksCommand
{
	public CmdBookDelete()
	{
		this.addAliases("delete");
		
		this.addRequiredArg("title");
		this.setErrorOnToManyArgs(false);
		
		this.addRequirements(ReqHasPerm.get(Perm.DELETE.node));
	}
	
	@Override
	public void perform() throws MassiveCommandException
	{
		MBook mbook = this.argConcatFrom(0, ARMBook.get());
		
		ItemStack target = mbook.getItem();
		mbook.detach();
		
		sendMessage(Lang.getSuccessDelete(target));
	}
}
