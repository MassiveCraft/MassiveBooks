package com.massivecraft.massivebooks.cmd;

import java.util.List;

import com.massivecraft.massivebooks.MassiveBooks;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MConf;
import com.massivecraft.massivecore.command.VersionCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

public class CmdBook extends MassiveBooksCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdBookUnlock cmdBookUnlock = new CmdBookUnlock();
	public CmdBookLock cmdBookLock = new CmdBookLock();
	public CmdBookClear cmdBookClear = new CmdBookClear();
	public CmdBookTitle cmdBookTitle = new CmdBookTitle();
	public CmdBookAuthor cmdBookAuthor = new CmdBookAuthor();
	public CmdBookCopy cmdBookCopy = new CmdBookCopy();
	public CmdBookList cmdBookList = new CmdBookList();
	public CmdBookLoad cmdBookLoad = new CmdBookLoad();
	public CmdBookGive cmdBookGive = new CmdBookGive();
	public CmdBookSave cmdBookSave = new CmdBookSave();
	public CmdBookDelete cmdBookDelete = new CmdBookDelete();
	public CmdBookAutoupdate cmdBookAutoupdate = new CmdBookAutoupdate();
	public CmdBookPowertool cmdBookPowertool = new CmdBookPowertool();
	public CmdBookCopyrighted cmdBookCopyrighted = new CmdBookCopyrighted();
	public VersionCommand cmdBookVersion = new VersionCommand(MassiveBooks.get(), Perm.VERSION.node, "v", "version");
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdBook()
	{
		// Children
		this.addChild(this.cmdBookUnlock);
		this.addChild(this.cmdBookLock);
		this.addChild(this.cmdBookClear);
		this.addChild(this.cmdBookTitle);
		this.addChild(this.cmdBookAuthor);
		this.addChild(this.cmdBookCopy);
		this.addChild(this.cmdBookList);
		this.addChild(this.cmdBookLoad);
		this.addChild(this.cmdBookGive);
		this.addChild(this.cmdBookSave);
		this.addChild(this.cmdBookDelete);
		this.addChild(this.cmdBookAutoupdate);
		this.addChild(this.cmdBookPowertool);
		this.addChild(this.cmdBookCopyrighted);
		this.addChild(this.cmdBookVersion);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.BOOK.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesBook;
	}

}
