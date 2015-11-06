package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.type.TypeBookInHand;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;

public class CmdBookSave extends MassiveBooksCommand
{
	public CmdBookSave()
	{
		// Aliases
		this.addAliases("save");
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.SAVE.node));
		this.addRequirements(RequirementIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		ItemStack item = TypeBookInHand.getEither().read(sender);		
		
		String title = BookUtil.getTitle(item);
		if (title == null)
		{
			message(Lang.BOOK_MUST_HAVE_TITLE);
			return;
		}
		
		MBook mbook = MBookColl.get().get(title, true);
		mbook.setItem(item);
		
		message(Lang.getSuccessSave(item));
	}

}
