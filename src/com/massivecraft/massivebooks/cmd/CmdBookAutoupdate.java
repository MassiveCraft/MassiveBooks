package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MConf;
import com.massivecraft.massivecore.cmd.MassiveCommandException;
import com.massivecraft.massivecore.cmd.arg.ARBoolean;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;

public class CmdBookAutoupdate extends MassiveBooksCommand
{
	public CmdBookAutoupdate()
	{
		this.addAliases("autoupdate");
		
		this.addOptionalArg("true/false", "toggle");
		
		this.addRequirements(ReqHasPerm.get(Perm.AUTOUPDATE.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveCommandException
	{
		boolean currentState = mme.isUsingAutoUpdate();
		Boolean targetState = this.arg(0, ARBoolean.get(), !currentState);
		
		if (!MConf.get().autoupdatingServerbooks)
		{
			sendMessage(Lang.AUTOUPDATE_DERPWARN);
		}
		
		mme.setUsingAutoUpdate(targetState, true, true);
	}
}
