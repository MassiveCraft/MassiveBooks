package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.entity.MPlayer;
import com.massivecraft.mcore.cmd.MCommand;

public abstract class MassiveBooksCommand extends MCommand
{
	public MPlayer mme;
	
	@Override
	public void fixSenderVars()
	{
		this.mme = MPlayer.get(this.sender);
	}
}