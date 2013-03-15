package com.massivecraft.massivebooks;

import org.bukkit.inventory.ItemStack;

import com.massivecraft.mcore.mixin.Mixin;
import com.massivecraft.mcore.util.Txt;

public class Lang
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static final String STATE_POWERTOOL = Txt.parse("POWERTOOL");
	public static final String STATE_UNLOCKED = Txt.parse("UNLOCKED");
	public static final String COLOR_STATE = Txt.parse("<h>");
	public static final String COLOR_SIGNATURE = Txt.parse("<white>");
	public static final String BY_EMPHASIZED = Txt.parse("<em>by");
	public static final String NO_TITLE_COLORED = Txt.parse("<silver><em>No Title");
	public static final String NO_AUTHOR_COLORED = Txt.parse("<silver><em>No Author");
	
	public static final String PERMISSION_DESCRIPTION_COPYCOST_TEMPLATE = Txt.parse("copy a book for the cost %f");
	
	public static final String ACCEPTED_ITEMS_WRITTEN = Txt.parse("a written book");
	public static final String ACCEPTED_ITEMS_QUILL = Txt.parse("a book and quill");
	public static final String ACCEPTED_ITEMS_EITHER = Txt.parse("a written book or a book and quill");
	public static final String BOOK_IN_HAND_ERROR_TEMPLATE = Txt.parse("<b>You must be holding %s.");
	
	public static final String SAME_UNLOCK_X = Txt.parse("%s <g>is already unlocked.");
	public static final String ALTER_UNLOCK_X = Txt.parse("%s <g>is now unlocked.");
	
	public static final String SAME_LOCK_X = Txt.parse("%s <g>is already locked.");
	public static final String ALTER_LOCK_X = Txt.parse("%s <g>is now locked.");
	
	public static final String SAME_CLEAR_X = Txt.parse("%s <g>is already cleared.");
	public static final String ALTER_CLEAR_X = Txt.parse("%s <g>is now cleared.");
	
	public static final String SAME_TITLE_X = Txt.parse("<g>The title is already %s<g>.");
	public static final String ALTER_TITLE_X_Y = Txt.parse("<g>Changed title from %s <g>to %s<g>.");
	
	public static final String SAME_AUTHOR_X = Txt.parse("<g>The author is already %s<g>.");
	public static final String ALTER_AUTHOR_X_Y = Txt.parse("<g>Changed author from %s <g>to %s<g>.");
	
	public static final String SUCCESS_LOAD = Txt.parse("<g>Loaded %s<g>.");
	public static final String SUCCESS_SAVE = Txt.parse("<g>Saved %s<g>.");
	public static final String SUCCESS_DELETED = Txt.parse("<g>Deleted %s<g>.");
	public static final String BOOK_MUST_HAVE_TITLE = Txt.parse("<b>The book must have a title.");
	
	public static final String TIMES_MUST_BE_POSITIVE = Txt.parse("<b>times must be positive");
	public static final String NOT_ENOUGH_X = Txt.parse("<b>Not enough <h>%s<b>.");
	public static final String FAILED_TO_REMOVE_X = Txt.parse("<b>Failed to remomve %s.");
	public static final String RESOURCE_MONEY = Txt.parse("money");
	public static final String RESOURCE_BOOKS = Txt.parse("books");
	public static final String RESOURCE_INKSACS = Txt.parse("inc sacs");
	public static final String RESOURCE_FEATHERS = Txt.parse("feathers");
	public static final String RESOURCE_ROOM = Txt.parse("inventory space");
	public static final String SUCCESS_COPY1 = Txt.parse("<g>Created <h>%d <g>copies of the book.");
	public static final String SUCCESS_COPY2 = Txt.parse("<i>These resources were used:");
	public static final String SUCCESS_COPY3 = Txt.parse("<k>Money: <v>%f <k>Books: <v>%d <k>Ink Sacs: <v>%d <k>Feathers: <v>%d");
	
	public static final String REQUIRED_X_POSSESSED_Y_MISSING_Z = Txt.parse("<k>Required: <v>%s <k>Possessed: <v>%s <k>Missing: <v>%s");
	
	public static final String POWERTOOL_CHANGED_TO_TRUE = Txt.parse("<lime>TRUE<i> The book is now a powertool.");
	public static final String POWERTOOL_CHANGED_TO_FALSE = Txt.parse("<rose>FALSE<i> The book is no longer a powertool.");
	public static final String POWERTOOL_ALREADY_TRUE = Txt.parse("<lime>TRUE<i> The book is already a powertool.");
	public static final String POWERTOOL_ALREADY_FALSE = Txt.parse("<rose>FALSE<i> The book is already not a powertool.");
	
	public static final String POWERTOOL_YOU_SHOULD = Txt.parse("<b>This powertool should be used on another player.");
	public static final String POWERTOOL_YOU_SHOULDNT = Txt.parse("<b>This powertool shouldn't be used on another player.");
	public static final String POWERTOOL_RAN = Txt.parse("<g>RAN: <h>%s");
	public static final String POWERTOOL_FAILED = Txt.parse("<b>FAILED: <h>%s <b>%s");
	
	public static final String POWERTOOL_COLOR_ME = Txt.parse("<pink>");
	public static final String POWERTOOL_COLOR_YOU = Txt.parse("<gold>");
	public static final String POWERTOOL_COLOR_COMMAND = Txt.parse("<c>");
	public static final String POWERTOOL_COLOR_CHAT = Txt.parse("<white>");
	
	// -------------------------------------------- //
	// METHODS
	// -------------------------------------------- //
	
	public static String descTitle(String title)
	{
		if (title == null) return NO_TITLE_COLORED;
		return COLOR_SIGNATURE+title;
	}
	
	public static String descAuthor(String author)
	{
		if (author == null) return NO_AUTHOR_COLORED;
		return COLOR_SIGNATURE+Mixin.getDisplayName(author);
	}
	
	public static String descSignature(ItemStack item)
	{
		String title = BookUtil.getTitle(item);
		String author = BookUtil.getAuthor(item);
		
		String titleDescription = descTitle(title);
		String authorDescription = descAuthor(author);
		
		String glue = " " + COLOR_SIGNATURE + Lang.BY_EMPHASIZED + " ";
		
		return titleDescription + glue + authorDescription;
	}
	
	public static String descDisplayName(ItemStack item)
	{
		boolean powertool = BookUtil.containsFlag(item, Const.POWERTOOL);
		boolean unlocked = BookUtil.isUnlocked(item);
		
		if (BookUtil.isBookMetaEmpty(item) && ! powertool) return null;
		
		String ret = descSignature(item);
		
		if (powertool)
		{
			ret = COLOR_STATE + STATE_POWERTOOL + " " + ret;
		}

		if (unlocked)
		{
			ret = COLOR_STATE + STATE_UNLOCKED + " " + ret;
		}
		
		return ret;
	}
	
	public static String getSameUnlock(ItemStack current)
	{
		return String.format(Lang.SAME_UNLOCK_X, descDisplayName(current));
	}
	public static String getAlterUnlock(ItemStack before)
	{
		return String.format(Lang.ALTER_UNLOCK_X, descDisplayName(before));
	}
	
	public static String getSameLock(ItemStack current)
	{
		return String.format(Lang.SAME_LOCK_X, descDisplayName(current));
	}
	public static String getAlterLock(ItemStack before)
	{
		return String.format(Lang.ALTER_LOCK_X, descDisplayName(before));
	}
	
	public static String getSameClear(ItemStack current)
	{
		return String.format(Lang.SAME_CLEAR_X, descDisplayName(current));
	}
	public static String getAlterClear(ItemStack before)
	{
		return String.format(Lang.ALTER_CLEAR_X, descDisplayName(before));
	}
	
	public static String getSameTitle(String title)
	{
		return String.format(Lang.SAME_TITLE_X, descTitle(title));
	}
	public static String getAlterTitle(String before, String after)
	{
		return String.format(ALTER_TITLE_X_Y, descTitle(before), descTitle(after));
	}
	
	public static String getSameAuthor(String author)
	{
		return String.format(Lang.SAME_AUTHOR_X, descAuthor(author));
	}
	public static String getAlterAuthor(String before, String after)
	{
		return String.format(ALTER_AUTHOR_X_Y, descAuthor(before), descAuthor(after));
	}
	
	public static String getSuccessLoad(ItemStack item)
	{
		return String.format(Lang.SUCCESS_LOAD, descDisplayName(item));
	}
	public static String getSuccessSave(ItemStack item)
	{
		return String.format(Lang.SUCCESS_SAVE, descDisplayName(item));
	}
	public static String getSuccessDelete(ItemStack item)
	{
		return String.format(Lang.SUCCESS_DELETED, descDisplayName(item));
	}
	
}
