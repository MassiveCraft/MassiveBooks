package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Const;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.arg.ARBoolean;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBookPowertool extends MassiveBooksCommand
{
	public CmdBookPowertool()
	{
		super();
		this.addAliases(ConfServer.aliasesBookPowertool);
		this.addOptionalArg("true/false", "toggle");
		this.addRequirements(ReqHasPerm.get(Perm.POWERTOOL.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		// Args
		ItemStack item = this.arg(ARBookInHand.getWritten());
		if (item == null) return;
		
		boolean currentState = BookUtil.containsFlag(item, Const.POWERTOOL);
		Boolean targetState = this.arg(0, ARBoolean.get(), !currentState);
		if (targetState == null) return;
		
		// Detect NoChange
		if (targetState == currentState)
		{
			if (targetState)
			{
				sendMessage(Lang.POWERTOOL_ALREADY_TRUE);
			}
			else
			{
				sendMessage(Lang.POWERTOOL_ALREADY_FALSE);
			}
			return;
		}
		
		// Check other-perm if another author
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.POWERTOOL_OTHER.has(sender, true)) return;
		
		// Apply & Inform
		if (targetState)
		{
			BookUtil.addFlag(item, Const.POWERTOOL);
			sendMessage(Lang.POWERTOOL_CHANGED_TO_TRUE);
		}
		else
		{
			BookUtil.removeFlag(item, Const.POWERTOOL);
			sendMessage(Lang.POWERTOOL_CHANGED_TO_FALSE);
		}
	}
}
