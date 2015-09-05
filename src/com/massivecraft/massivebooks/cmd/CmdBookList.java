package com.massivecraft.massivebooks.cmd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.ArgSetting;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;

public class CmdBookList extends MassiveBooksCommand
{
	public CmdBookList()
	{
		this.addAliases("list");
		
		this.addArg(ArgSetting.getPage());
		
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		
		// Create Messages
		List<String> lines = new ArrayList<String>();
		
		Collection<MBook> mbooks = MBookColl.get().getAll();
		for (MBook mbook : mbooks)
		{
			lines.add(Lang.descDisplayName(mbook.getItem()));
		}
		
		// Send them
		this.message(Txt.getPage(lines, page, String.valueOf(mbooks.size())+" Saved Books", this));	
	}

}
