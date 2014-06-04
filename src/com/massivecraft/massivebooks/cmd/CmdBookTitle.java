package com.massivecraft.massivebooks.cmd;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.util.Txt;

public class CmdBookTitle extends MassiveBooksCommand
{
	public CmdBookTitle()
	{
		this.addAliases("title");
		
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
		String targetParsed = Txt.parse(target);
		boolean usingColor = (!ChatColor.stripColor(targetParsed).equals(targetParsed));
		
		if (usingColor)
		{
			if (!Perm.TITLE_COLOR.has(sender, true)) return;
			target = targetParsed;
		}
		
		String old = BookUtil.getTitle(item);
		
		if (BookUtil.isTitleEquals(item, target))
		{
			sendMessage(Lang.getSameTitle(old));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.TITLE_OTHER.has(sender, true)) return;
		
		BookUtil.setTitle(item, target);
		me.setItemInHand(item);
		
		sendMessage(Lang.getAlterTitle(old, target));
	}
}
