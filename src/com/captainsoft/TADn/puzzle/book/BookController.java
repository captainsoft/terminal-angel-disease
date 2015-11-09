/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.book;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.loader.PuzzleLoader;
import com.captainsoft.TADn.sound.SndFacade;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * Controller for books.
 *
 * @author mathias fringes
 */
public final class BookController extends BaseWindowController {
		
	// fields
	
	private final GameEngine gameEngine;
	private final PuzzleLoader puzzleLoader;
	
	private Book book;
		
	// constructors
	
	public BookController(GameEngine gameEngine, int index) {
		super();
		this.gameEngine = gameEngine;
		this.puzzleLoader = TadRepo.inst().puzzleLoader();
		book = puzzleLoader.loadBook(index);
	}
		
	// overridden methods
		
	@Override
	public UiBoxContainer createWindow(BoxCommandList mg) {
		final BookWindow w = new BookWindow();		
		w.text(book.text);
		w.pos(170, 30);
		//
		Command c = new Command() {
			@Override
			public void execute() {
				if (book.hasNextPage()) {
					SndFacade.bookSound();
					book = puzzleLoader.loadBook(book.nextPage);					
					w.text(book.text);
					gameEngine.mainViewer().updateBox(w);
				} else {
					gameEngine.closeWindows();
				}
			}	
		};
		mg.setCmd(w, c);		
		//
		return w;
	}

}