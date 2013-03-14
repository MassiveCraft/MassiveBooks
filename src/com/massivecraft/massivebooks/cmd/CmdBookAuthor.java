package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBookAuthor extends MassiveBooksCommand
{
	public CmdBookAuthor()
	{
		super();
		this.addAliases(ConfServer.aliasesBookAuthor);
		this.addRequiredArg("author");
		this.setErrorOnToManyArgs(false);
		this.addRequirements(ReqHasPerm.get(Perm.AUTHOR.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		ItemStack item = this.arg(ARBookInHand.getWritten());
		if (item == null) return;
		
		String author = this.argConcatFrom(0);
		
		try
		{
			if (!BookUtil.isAuthorEquals(item, sender) && !Perm.AUTHOR_OTHER.has(sender, true)) return;
			
			BookUtil.setTitle(item, author);
			me.setItemInHand(item);
			
			sendMessage(Lang.SUCCESS_AUTHOR);
		}
		catch (Exception e)
		{
			sendMessage(String.format(Lang.FAIL_AUTHOR, e.getMessage()));
		}
		
	}
}
