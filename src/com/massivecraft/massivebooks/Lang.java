package com.massivecraft.massivebooks;

import com.massivecraft.mcore.util.Txt;

public class Lang
{
	public static final String PERMISSION_DESCRIPTION_COPYCOST_TEMPLATE = "copy a book for the cost %f"; 
	public static final String ACCEPTED_ITEMS_WRITTEN = "a written book";
	public static final String ACCEPTED_ITEMS_QUILL = "a book and quill";
	public static final String ACCEPTED_ITEMS_EITHER = "a written book or a book and quill";
	public static final String BOOK_IN_HAND_ERROR_TEMPLATE = Txt.parse("<b>You must be holding %s.");
	
	public static final String SUCCESS_UNSIGN = Txt.parse("<g>The item in you hand was unsigned.");
	public static final String FAIL_UNSIGN = Txt.parse("<b>Could not unsign: %s");
	
	
	
	
}
