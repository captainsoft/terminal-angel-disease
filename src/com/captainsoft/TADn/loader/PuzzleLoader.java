/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader;

import com.captainsoft.TADn.puzzle.PuzzleFileData;
import com.captainsoft.TADn.puzzle.book.Book;
import com.captainsoft.TADn.puzzle.event.Event;
import com.captainsoft.TADn.puzzle.talk.TalkPage;

/**
 * 
 *
 * @author mathias fringes
 */
public interface PuzzleLoader {

	public Book loadBook(int id);

	public Event loadEvent(int map, int id);
	
	public PuzzleFileData loadPuzzle(int map, int id);

	public TalkPage loadTalkPage(int map, int id);
	
}
