/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.book;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.PuzzleLoader;
import com.captainsoft.TADr.sound.SndFacade;
import com.captainsoft.spark.command.AbstractCommand;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * Books window controller.
 *
 * @author mathias fringes
 */
public final class BookWindowController extends BaseWindowController {
		
	// fields
	
	private final GameEngine gameEngine;
	private final PuzzleLoader puzzleLoader;
	
	private Book book;
		
	// constructors
	
	public BookWindowController(GameEngine gameEngine, int index) {
		super();
		this.gameEngine = gameEngine;
		this.puzzleLoader = TadRepo.inst().puzzleLoader();
		book = puzzleLoader.loadBook(index);
	}
		
	// overridden

	public UiBoxContainer createWindow(BoxCommandList mg) {
		final BookWindow w = new BookWindow();		
		w.text(book.text);
		w.pos(170, 30);
		//
		Command c = new AbstractCommand("turn the next page of this book") {

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