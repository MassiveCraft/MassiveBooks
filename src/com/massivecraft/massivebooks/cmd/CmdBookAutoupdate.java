package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MConf;
import com.massivecraft.mcore.cmd.arg.ARBoolean;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBookAutoupdate extends MassiveBooksCommand
{
	public CmdBookAutoupdate()
	{
		this.addAliases(ConfServer.aliasesBookAutoupdate);
		
		this.addOptionalArg("true/false", "toggle");
		
		this.addRequirements(ReqHasPerm.get(Perm.AUTOUPDATE.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		boolean currentState = mme.isUsingAutoUpdate();
		Boolean targetState = this.arg(0, ARBoolean.get(), !currentState);
		if (targetState == null) return;
		
		if (!MConf.get().isAutoupdatingServerbooks())
		{
			sendMessage(Lang.AUTOUPDATE_DERPWARN);
		}
		
		mme.setUsingAutoUpdate(targetState, true, true);
	}
}
