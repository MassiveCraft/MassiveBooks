package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.ARBookInHand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARString;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.util.IdUtil;

public class CmdBookAuthor extends MassiveBooksCommand
{
	public CmdBookAuthor()
	{
		// Aliases
		this.addAliases("author");
		
		// Args
		this.addArg(ARString.get(), "author", true);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.AUTHOR.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		ItemStack item = ARBookInHand.getWritten().read(sender);
		
		String target = this.readArg();
		target = IdUtil.getName(target);
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
