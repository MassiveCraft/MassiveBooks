package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.entity.MPlayer;
import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.VisibilityMode;

public abstract class MassiveBooksCommand extends MassiveCommand
{
	public MPlayer mme;
	
	@Override
	public void fixSenderVars()
	{
		this.mme = MPlayer.get(this.sender);
	}
	
	@Override
	public void unsetSenderVars()
	{
		this.mme = null;
	}
	
	public MassiveBooksCommand()
	{
		this.setVisibilityMode(VisibilityMode.SECRET);
	}
}