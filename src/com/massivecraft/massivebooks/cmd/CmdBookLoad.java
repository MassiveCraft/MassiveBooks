package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.TypeBookInHand;
import com.massivecraft.massivebooks.cmd.arg.TypeMBook;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;

public class CmdBookLoad extends MassiveBooksCommand
{
	public CmdBookLoad()
	{
		// Aliases
		this.addAliases("load");
		
		// Parameters
		this.addParameter(TypeMBook.get(), "title", true);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.LOAD.node));
		this.addRequirements(ReqIsPlayer.get());
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
