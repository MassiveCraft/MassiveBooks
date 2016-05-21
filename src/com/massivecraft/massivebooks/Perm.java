package com.massivecraft.massivebooks;

import org.bukkit.permissions.Permissible;

import com.massivecraft.massivecore.util.PermissionUtil;

public enum Perm
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	BOOK("book"),

	UNLOCK("unlock"),
	UNLOCK_OTHER("unlock.other"),
	
	LOCK("lock"),
	LOCK_OTHER("lock.other"),
	
	CLEAR("clear"),
	CLEAR_OTHER("clear.other"),
	
	TITLE("title"),
	TITLE_OTHER("title.other"),
	TITLE_COLOR("title.color"),
	
	AUTHOR("author"),
	AUTHOR_OTHER("author.other"),
	
	COPY("copy"),
	COPY_OTHER("copy.other"),
	COPY_COPYRIGHTED("copy.copyrighted"),
	
	LIST("list"),
	LOAD("load"),
	GIVE("give"),
	SAVE("save"),
	DELETE("delete"),
	
	AUTOUPDATE("autoupdate"),
	
	POWERTOOL("powertool"),
	POWERTOOL_OTHER("powertool.other"),
	
	COPYRIGHTED("copyrighted"),
	COPYRIGHTED_OTHER("copyrighted.other"),
	
	VERSION("version"),
	
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
		return PermissionUtil.hasPermission(permissible, this.node, informSenderIfNot);
	}
	
	public boolean has(Permissible permissible)
	{
		return has(permissible, false);
	}
	
}
