package com.massivecraft.massivebooks;

import java.util.List;

import com.massivecraft.mcore.SimpleConfig;
import com.massivecraft.mcore.util.MUtil;

public class ConfServer extends SimpleConfig
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	public static String dburi = "default";
	public static List<String> aliasesBook = MUtil.list("book", "books");
	public static List<String> aliasesBookUnsign = MUtil.list("unsign");
	public static List<String> aliasesBookClear = MUtil.list("clear");
	public static List<String> aliasesBookTitle = MUtil.list("title");
	public static List<String> aliasesBookAuthor = MUtil.list("author");
	public static List<String> aliasesBookCopy = MUtil.list("copy");
	public static List<String> aliasesBookPowertool = MUtil.list("pt", "powertool");
	
	// -------------------------------------------- //
	// PERSISTENCE
	// -------------------------------------------- //
	
	public static transient ConfServer i = new ConfServer();
	public ConfServer() { super(MassiveBooks.get()); }
	
}

