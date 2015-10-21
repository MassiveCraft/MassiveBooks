package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.entity.MPlayer;
import com.massivecraft.massivecore.cmd.MassiveCommand;
import com.massivecraft.massivecore.cmd.Visibility;

public abstract class MassiveBooksCommand extends MassiveCommand
{
	public MPlayer mme;
	
	@Override
	public void senderFields(boolean set)
	{
		this.mme = set ? MPlayer.get(this.sender) : null;
	}
	
	public MassiveBooksCommand()
	{
		this.setVisibility(Visibility.SECRET);
	}
}