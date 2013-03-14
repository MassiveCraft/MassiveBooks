package com.massivecraft.massivebooks.cmd;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.massivebooks.BookUtil;
import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdBookUnsign extends MassiveBooksCommand
{
	public CmdBookUnsign()
	{
		super();
		this.addAliases(ConfServer.aliasesBookUnsign);
		this.addRequirements(ReqHasPerm.get(Perm.UNSIGN.node));
		this.addRequirements(ReqIsPlayer.get());
	}
	
	@Override
	public void perform()
	{
		ItemStack item = this.arg(ARBookInHand.getWritten());
		if (item == null) return;
		
		if (!BookUtil.isAuthorOf(sender, item) && !Perm.UNSIGN_OTHER.has(sender, true)) return;
		
		ItemStack unsignedItem = null;
		try
		{
			unsignedItem = BookUtil.createUnsigned(item);
		}
		catch (Exception e)
		{
			sendMessage(Lang.COULD_NOT_UNSIGN_TEMPLATE, e.getMessage());
		}
		me.setItemInHand(unsignedItem);
		
		sendMessage(Lang.SUCCESS_UNSIGN);
	}
}
