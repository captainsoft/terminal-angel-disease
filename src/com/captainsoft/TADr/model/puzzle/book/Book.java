/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.book;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * A page in a book.
 *
 * @author mathias fringes
 */
public final class Book {
	
	public int nextPage = -1;

	public int startPage;

	public String text;
	
	public Book() {
		super();
	}
	
	public boolean hasNextPage() {
		return (nextPage > 0);
	}
		
	@Override
	public String toString() {
        return stringer("Book", startPage);
	}
	
}