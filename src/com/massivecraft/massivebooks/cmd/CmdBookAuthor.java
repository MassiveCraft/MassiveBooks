package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore.mixin.Mixin;

public class CmdBookAuthor extends MassiveBooksCommand
{
	public CmdBookAuthor()
	{
		this.addAliases("author");
		
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
		
		String target = this.argConcatFrom(0);
		target = Mixin.tryFix(target);
		String old = BookUtil.getAuthor(item);

		if (BookUtil.isAuthorEqualsId(item, target))
		{
			sendMessage(Lang.getSameAuthor(old));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.AUTHOR_OTHER.has(sender, true)) return;
		
		BookUtil.setAuthor(item, target);
		me.setItemInHand(item);
		
		sendMessage(Lang.getAlterAuthor(old, target));
	}
}
