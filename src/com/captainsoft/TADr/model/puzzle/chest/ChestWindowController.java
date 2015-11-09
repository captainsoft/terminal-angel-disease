/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.chest;

import com.captainsoft.TADr.engine.commands.GameEngineCommand;
import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.spark.ui.CloseWindowController;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * Controller for a chest window.
 *
 * @author mathias fringes
 */
public final class ChestWindowController extends CloseWindowController {
	
	// fields	
	
	boolean leftDisplay = false;
	
	private final Chest chest;

	// constructors
	
	public ChestWindowController(Chest chest) {
		super();		
		this.chest = chest;
	}	
	
    // WindowController

	public UiBoxContainer createWindow(BoxCommandList mg) {
		
		final ChestWindow cw = new ChestWindow(chest);
		cw.pos(leftDisplay ? 350 : 65, 30);
		
		for (int i = 0; i < 6; i++) {
			
			final ItemBox b = cw.itemBox[i];
			if (b == null) {
				continue;
			}
			
			mg.setRightCmd(b, new GameEngineCommand("take item from chest") {

				public void execute() {
					if (gameEngine.mainViewer().hasItemInHand()) {
						return;
					}
					gameEngine.takeItem(b.item());
					b.item(null);
					gameEngine.mainViewer().updateBox(b);
					if (cw.allTaken()) {							
						gameEngine.closeWindows();
						executeCloseCommand();					
					}
				}
			});
						
		}
		return cw;
	}	

}
