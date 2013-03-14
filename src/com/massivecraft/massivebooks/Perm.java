package com.massivecraft.massivebooks;

import org.bukkit.permissions.Permissible;

import com.massivecraft.mcore.util.PermUtil;

public enum Perm
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	BOOK("book"),

	UNSIGN("unsign"),
	UNSIGN_OTHER("unsign.other"),
	
	CLEAR("clear"),
	CLEAR_OTHER("clear.other"),
	
	TITLE("title"),
	TITLE_OTHER("title.other"),
	
	AUTHOR("author"),
	AUTHOR_OTHER("author.other"),
	
	COPY("copy"),
	COPY_OTHER("copy.other"),
	
	POWERTOOL("powertool"),
	POWERTOOL_OTHER("powertool.other"),
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final String node;
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	Perm(final String permissionNode)
	{
		this.node = "massivebooks."+permissionNode;
	}
	
	// -------------------------------------------- //
	// HAS
	// -------------------------------------------- //
	
	public boolean has(Permissible permissible, boolean informSenderIfNot)
	{
		return PermUtil.has(permissible, this.node, informSenderIfNot);
	}
	
	public boolean has(Permissible permissible)
	{
		return has(permissible, false);
	}
}