/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.controller;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.commands.GoCommand;
import com.captainsoft.TADr.model.Direction;
import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.control.key.KeyInput;

/**
 * @author mathias fringes
 */
public final class MoveKeysInputController implements KeyInput {

    private final GameEngine gameEngine;

    public MoveKeysInputController(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public boolean keyPress(int keyCode) {
        switch (keyCode) {
            case KeyCodes.CursorRight:
            case KeyCodes.D:
                gameEngine.nextCommand(new GoCommand(Direction.East));
                break;
            case KeyCodes.CursorLeft:
            case KeyCodes.A:
                gameEngine.nextCommand(new GoCommand(Direction.West));
                break;
            case KeyCodes.CursorUp:
            case KeyCodes.W:
                gameEngine.nextCommand(new GoCommand(Direction.North));
                break;
            case KeyCodes.CursorDown:
            case KeyCodes.S:
                gameEngine.nextCommand(new GoCommand(Direction.South));
                break;
            default:
                return false;
        }
        return true;
    }

}
