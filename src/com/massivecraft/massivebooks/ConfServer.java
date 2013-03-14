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
	
	// -------------------------------------------- //
	// PERSISTENCE
	// -------------------------------------------- //
	
	public static transient ConfServer i = new ConfServer();
	public ConfServer() { super(MassiveBooks.get()); }
	
}
