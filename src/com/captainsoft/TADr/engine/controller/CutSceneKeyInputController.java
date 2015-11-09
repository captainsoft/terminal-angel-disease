/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.controller;

import com.captainsoft.TADr.engine.commands.ShowCutSceneCommand;
import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.control.key.KeyInput;

/**
 * Key input controller for cut scenes (intro, outro).
 * 
 * @author mathias fringes
 */
public final class CutSceneKeyInputController implements KeyInput {

    // fields

	private final ShowCutSceneCommand cutSceneCommand;

    // constructors

	public CutSceneKeyInputController(ShowCutSceneCommand cutSceneCommand) {
		this.cutSceneCommand = cutSceneCommand;
	}

    // KeyInput

	public boolean keyPress(int keyCode) {
		switch (keyCode) {		
			case KeyCodes.ESC:
				cutSceneCommand.stop();
				break;
			default:
                return false;
		}
        return true;
	}

}