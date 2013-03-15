package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBookTitle extends MassiveBooksCommand
{
	public CmdBookTitle()
	{
		super();
		this.addAliases(ConfServer.aliasesBookTitle);
		this.addRequiredArg("title");
		this.setErrorOnToManyArgs(false);
		this.addRequirements(ReqHasPerm.get(Perm.TITLE.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		ItemStack item = this.arg(ARBookInHand.getWritten());
		if (item == null) return;
		
		String target = this.argConcatFrom(0);
		String old = BookUtil.getTitle(item);
		
		if (BookUtil.isTitleEquals(item, target))
		{
			sendMessage(String.format(Lang.TITLE_ALREADY_X, BookUtil.describeTitle(old)));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.TITLE_OTHER.has(sender, true)) return;
		
		BookUtil.setTitle(item, target);
		me.setItemInHand(item);
		
		sendMessage(String.format(Lang.TITLE_CHANGED_FROM_X_TO_Y, BookUtil.describeTitle(old), BookUtil.describeTitle(target)));
	}
}
