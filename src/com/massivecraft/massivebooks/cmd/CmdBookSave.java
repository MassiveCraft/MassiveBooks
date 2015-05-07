package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.cmd.arg.ARBookInHand;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;

public class CmdBookSave extends MassiveBooksCommand
{
	public CmdBookSave()
	{
		// Aliases
		this.addAliases("save");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.SAVE.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform() throws MassiveException
	{
		ItemStack item = ARBookInHand.getEither().read(sender);		
		
		String title = BookUtil.getTitle(item);
		if (title == null)
		{
			sendMessage(Lang.BOOK_MUST_HAVE_TITLE);
			return;
		}
		
		MBook mbook = MBookColl.get().get(title, true);
		mbook.setItem(item);
		
		sendMessage(Lang.getSuccessSave(item));
	}

}
