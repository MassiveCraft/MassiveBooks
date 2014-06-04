package com.massivecraft.massivebooks.cmd;

import java.util.List;

import com.massivecraft.massivebooks.MassiveBooks;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.massivebooks.entity.MConf;
import com.massivecraft.massivecore.cmd.HelpCommand;
import com.massivecraft.massivecore.cmd.VersionCommand;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;

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
		// Add SubCommands
		this.addSubCommand(HelpCommand.get());
		this.addSubCommand(this.cmdBookUnlock);
		this.addSubCommand(this.cmdBookLock);
		this.addSubCommand(this.cmdBookClear);
		this.addSubCommand(this.cmdBookTitle);
		this.addSubCommand(this.cmdBookAuthor);
		this.addSubCommand(this.cmdBookCopy);
		this.addSubCommand(this.cmdBookList);
		this.addSubCommand(this.cmdBookLoad);
		this.addSubCommand(this.cmdBookGive);
		this.addSubCommand(this.cmdBookSave);
		this.addSubCommand(this.cmdBookDelete);
		this.addSubCommand(this.cmdBookAutoupdate);
		this.addSubCommand(this.cmdBookPowertool);
		this.addSubCommand(this.cmdBookCopyrighted);
		this.addSubCommand(this.cmdBookVersion);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.BOOK.node));
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
