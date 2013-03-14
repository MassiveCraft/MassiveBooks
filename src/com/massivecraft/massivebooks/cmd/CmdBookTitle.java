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
		
		String title = this.argConcatFrom(0);
		
		if (BookUtil.isTitleEquals(item, title))
		{
			sendMessage(Lang.ALREADY_TITLE);
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.TITLE_OTHER.has(sender, true)) return;
		
		BookUtil.setTitle(item, title);
		me.setItemInHand(item);
		
		sendMessage(Lang.SUCCESS_TITLE);
	}
}
