package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBookClear extends MassiveBooksCommand
{
	public CmdBookClear()
	{
		super();
		this.addAliases(ConfServer.aliasesBookUnsign);
		this.addRequirements(ReqHasPerm.get(Perm.CLEAR.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		ItemStack item = this.arg(ARBookInHand.getEither());
		if (item == null) return;
		
		try
		{
			if (!BookUtil.isAuthorEquals(item, sender) && !Perm.CLEAR_OTHER.has(sender, true)) return;
			
			BookUtil.clear(item);
			me.setItemInHand(item);
			
			sendMessage(Lang.SUCCESS_CLEAR);
		}
		catch (Exception e)
		{
			sendMessage(Lang.FAIL_CLEAR, e.getMessage());
		}
		
	}
}
