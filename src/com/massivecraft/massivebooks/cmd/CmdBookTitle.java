package com.massivecraft.massivebooks.cmd;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.type.TypeBookInHand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.util.Txt;

public class CmdBookTitle extends MassiveBooksCommand
{
	public CmdBookTitle()
	{
		// Aliases
		this.addAliases("title");
		
		// Parameters
		this.addParameter(TypeString.get(), "title", true);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.TITLE.node));
		this.addRequirements(RequirementIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		ItemStack item = TypeBookInHand.getWritten().read(sender);
		
		String target = this.readArg();
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
			message(Lang.getSameTitle(old));
			return;
		}
		
		if (!BookUtil.isAuthorEquals(item, sender) && !Perm.TITLE_OTHER.has(sender, true)) return;
		
		BookUtil.setTitle(item, target);
		me.setItemInHand(item);
		
		message(Lang.getAlterTitle(old, target));
	}
}
