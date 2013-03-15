package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.entity.MPlayer;
import com.massivecraft.mcore.cmd.MCommand;
import com.massivecraft.mcore.cmd.VisibilityMode;

public abstract class MassiveBooksCommand extends MCommand
{
	public MPlayer mme;
	
	@Override
	public void fixSenderVars()
	{
		this.mme = MPlayer.get(this.sender);
	}
	
	public MassiveBooksCommand()
	{
		super();
		this.setVisibilityMode(VisibilityMode.SECRET);
	}
}