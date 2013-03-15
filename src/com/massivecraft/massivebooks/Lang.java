package com.massivecraft.massivebooks;

import com.massivecraft.mcore.util.Txt;

public class Lang
{
	public static final String NO_SIGNATURE = Txt.parse("<silver><em>not signed");
	public static final String NO_TITLE = Txt.parse("<silver><em>no title");
	public static final String NO_AUTHOR = Txt.parse("<silver><em>no author");
	
	public static final String PERMISSION_DESCRIPTION_COPYCOST_TEMPLATE = Txt.parse("copy a book for the cost %f");
	
	public static final String ACCEPTED_ITEMS_WRITTEN = Txt.parse("a written book");
	public static final String ACCEPTED_ITEMS_QUILL = Txt.parse("a book and quill");
	public static final String ACCEPTED_ITEMS_EITHER = Txt.parse("a written book or a book and quill");
	public static final String BOOK_IN_HAND_ERROR_TEMPLATE = Txt.parse("<b>You must be holding %s.");
	
	// TODO: Call it "lock" instead of "sign"?
	
	public static final String ALREADY_UNSIGN = Txt.parse("<g>The book is already unsigned.");
	public static final String SUCCESS_UNSIGN_X = Txt.parse("<g>Unsigned %s<g>.");
	
	public static final String ALREADY_CLEAR = Txt.parse("<g>The book is already cleared.");
	public static final String SUCCESS_CLEAR = Txt.parse("<g>Cleared item in your hand.");
	
	public static final String TITLE_ALREADY_X = Txt.parse("<g>The title is already %s<g>.");
	public static final String TITLE_CHANGED_FROM_X_TO_Y = Txt.parse("<g>Changed title from %s <g>to %s<g>.");
	
	public static final String AUTHOR_ALREADY_X = Txt.parse("<g>The author is already %s<g>.");
	public static final String AUTHOR_CHANGED_FROM_X_TO_Y = Txt.parse("<g>Changed author from %s <g>to %s<g>.");
	
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
	
}
