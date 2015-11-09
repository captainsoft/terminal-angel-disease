/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.book;

/**
 * A book domain object.
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
		return "Book: " + startPage;
	}
	
}