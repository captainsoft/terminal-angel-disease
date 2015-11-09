/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.controller;

import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.menu.MenuController;
import com.captainsoft.TADn.model.Direction;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.utils.Log;

/**
 * Handles general mouse and key input.
 * 
 * @author mathias fringes
 */
public final class MainInputController {

	// fields
	
	private final DebugKeysController debugKeyController;
	private final GameEngine gameEngine;	

	// constructors
	
	public MainInputController(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;	
		this.debugKeyController = new DebugKeysController(gameEngine);
	}

	// public 
	
	public void closeWindow() {
		gameEngine.eventCommand(new Command() {
			public void execute() {
				new MenuController(gameEngine).showQuitGameFrame();			
			}
		});
	}

	public void keyPress(int keyCode) {		
		switch (keyCode) {
			case 49:
			case 50:
			case 51:
			case 52:
				gameEngine.mainViewer().switchMember(keyCode-48);	
				break;
			case 39:
			case 68:
				gameEngine.goPartyGo(Direction.East);
				break;
			case 37:
			case 65:
				gameEngine.goPartyGo(Direction.West);
				break;
			case 38:
			case 87:
				gameEngine.goPartyGo(Direction.North);
				break;
			case 40:
			case 83:					
				gameEngine.goPartyGo(Direction.South);
				break;	
			case 116:
				// QUICK SAVE
				gameEngine.quickSave();
				break;
			
		}
		System.out.println(keyCode);
		this.debugKeyController.keyPress(keyCode);
	}

	public void click(int x, int y, int button) {
		Log.debug("Mouseclick: " + x + " " + y + " " + button);
	}

}