package com.massivecraft.massivebooks.cmd;

import java.util.Collection;

import com.massivecraft.massivebooks.Lang;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MBook;
import com.massivecraft.massivebooks.entity.MBookColl;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Parameter;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.pager.Pager;
import com.massivecraft.massivecore.pager.Stringifier;

public class CmdBookList extends MassiveBooksCommand
{
	public CmdBookList()
	{
		this.addAliases("list");
		
		this.addParameter(Parameter.getPage());
		
		this.addRequirements(RequirementHasPerm.get(Perm.LIST.node));
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		int page = this.readArg();
		
		// Pager Create
		Collection<MBook> mbooks = MBookColl.get().getAll();
		String title = String.valueOf(mbooks.size())+" Saved Books";
		Pager<MBook> pager = new Pager<MBook>(this, title, page, mbooks, new Stringifier<MBook>()
		{
			@Override
			public String toString(MBook mbook, int index)
			{
				return Lang.descDisplayName(mbook.getItem());
			}
		});
		
		// Pager Message
		pager.message();
	}

}
