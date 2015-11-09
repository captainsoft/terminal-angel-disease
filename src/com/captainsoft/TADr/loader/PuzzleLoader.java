/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.loader;

import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.book.Book;
import com.captainsoft.TADr.model.puzzle.event.Event;
import com.captainsoft.TADr.model.puzzle.talk.TalkPage;

/**
 * Loads puzzle data.
 *
 * @author mathias fringes
 */
public interface PuzzleLoader {

	public Book loadBook(int id);

	public Event loadEvent(int map, int id);
	
	public PuzzleFileData loadPuzzle(int map, int id);

	public TalkPage loadTalkPage(int map, int id);
	
}
