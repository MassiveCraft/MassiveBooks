package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.TypeBookInHand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.cmd.type.TypeString;
import com.massivecraft.massivecore.util.IdUtil;

public class CmdBookAuthor extends MassiveBooksCommand
{
	public CmdBookAuthor()
	{
		// Aliases
		this.addAliases("author");
		
		// Parameters
		this.addParameter(TypeString.get(), "author", true);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.AUTHOR.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		ItemStack item = TypeBookInHand.getWritten().read(sender);
		
		String target = this.readArg();
		target = IdUtil.getName(target);
		String old = BookUtil.getAuthor(item);

		if (BookUtil.isAuthorEqualsId(item, target))
		{
			message(Lang.getSameAuthor(old));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.AUTHOR_OTHER.has(sender, true)) return;
		
		BookUtil.setAuthor(item, target);
		me.setItemInHand(item);
		
		message(Lang.getAlterAuthor(old, target));
	}
	
}
