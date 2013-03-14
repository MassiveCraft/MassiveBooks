package com.massivecraft.massivebooks;

import com.massivecraft.mcore.util.Txt;

public class Lang
{
	public static final String PERMISSION_DESCRIPTION_COPYCOST_TEMPLATE = "copy a book for the cost %f"; 
	
	public static final String ACCEPTED_ITEMS_WRITTEN = "a written book";
	public static final String ACCEPTED_ITEMS_QUILL = "a book and quill";
	public static final String ACCEPTED_ITEMS_EITHER = "a written book or a book and quill";
	public static final String BOOK_IN_HAND_ERROR_TEMPLATE = Txt.parse("<b>You must be holding %s.");
	
	public static final String ALREADY_UNSIGN = Txt.parse("<g>The book is already unsigned.");
	public static final String SUCCESS_UNSIGN = Txt.parse("<g>Unsigned the book in your hand.");
	public static final String FAIL_UNSIGN = Txt.parse("<b>Could not unsign: %s");
	
	public static final String ALREADY_CLEAR = Txt.parse("<g>The book is already cleared.");
	public static final String SUCCESS_CLEAR = Txt.parse("<g>Cleared item in your hand.");
	public static final String FAIL_CLEAR = Txt.parse("<b>Could not clear: %s");
	
	public static final String ALREADY_TITLE = Txt.parse("<g>The book already has that title.");
	public static final String SUCCESS_TITLE = Txt.parse("<g>Set title for item in you hand.");
	public static final String FAIL_TITLE = Txt.parse("<b>Could not set title: %s");
	
	public static final String ALREADY_AUTHOR = Txt.parse("<g>The book already has that author.");
	public static final String SUCCESS_AUTHOR = Txt.parse("<g>Set author for item in you hand.");
	public static final String FAIL_AUTHOR = Txt.parse("<b>Could not set author: %s");
	
	public static final String TIMES_MUST_BE_POSITIVE = Txt.parse("<b>times must be positive");
	
	public static final String NOT_ENOUGH_X = Txt.parse("<b>Not enough %s.");
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
	
}
