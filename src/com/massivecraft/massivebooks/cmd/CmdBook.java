package com.massivecraft.massivebooks.cmd;

import com.massivecraft.massivebooks.ConfServer;
import com.massivecraft.massivebooks.Perm;
import com.massivecraft.mcore.cmd.HelpCommand;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;

public class CmdBook extends MassiveBooksCommand
{
	// SubCommands
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
	
	public CmdBook()
	{
		super();
		
		// Aliases
		this.setAliases(ConfServer.aliasesBook);
		
		// Help SubCommand 
		this.addSubCommand(HelpCommand.get());
		
		// Add SubCommands
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
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.BOOK.node));
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}
