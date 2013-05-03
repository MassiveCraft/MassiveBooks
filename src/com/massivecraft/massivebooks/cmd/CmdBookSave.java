package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBookSave extends MassiveBooksCommand
{
	public CmdBookSave()
	{
		this.addAliases(ConfServer.aliasesBookSave);
		
		this.addRequirements(ReqHasPerm.get(Perm.SAVE.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		ItemStack item = this.arg(ARBookInHand.getEither());
		if (item == null) return;
		
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
