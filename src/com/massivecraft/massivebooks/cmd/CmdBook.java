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
	public CmdBookPowertool cmdBookPowertool = new CmdBookPowertool();
	// TODO
	
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
		this.addSubCommand(this.cmdBookPowertool);
		// TODO
		
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
