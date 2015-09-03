package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Const;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.ARBookInHand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARBoolean;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;

public class CmdBookCopyrighted extends MassiveBooksCommand
{
	public CmdBookCopyrighted()
	{
		this.addAliases("cr", "copyrighted");
		
		this.addArg(ARBoolean.get(), "true/false", "toggle");
		
		this.addRequirements(ReqHasPerm.get(Perm.COPYRIGHTED.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		ItemStack item = this.readArgFrom(ARBookInHand.getWritten());
		
		boolean currentState = BookUtil.containsFlag(item, Const.COPYRIGHTED);
		boolean targetState = this.readArg(!currentState);
		
		// Detect NoChange
		if (targetState == currentState)
		{
			if (targetState)
			{
				message(Lang.COPYRIGHTED_ALREADY_TRUE);
			}
			else
			{
				message(Lang.COPYRIGHTED_ALREADY_FALSE);
			}
			return;
		}
		
		// Check other-perm if another author
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.COPYRIGHTED_OTHER.has(sender, true)) return;
		
		// Apply & Inform
		if (targetState)
		{
			BookUtil.addFlag(item, Const.COPYRIGHTED);
			message(Lang.COPYRIGHTED_CHANGED_TO_TRUE);
		}
		else
		{
			BookUtil.removeFlag(item, Const.COPYRIGHTED);
			message(Lang.COPYRIGHTED_CHANGED_TO_FALSE);
		}
	}

}
