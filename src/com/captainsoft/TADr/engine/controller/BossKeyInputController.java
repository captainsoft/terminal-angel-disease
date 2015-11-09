/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.controller;

import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.control.key.KeyInput;
import com.captainsoft.spark.utils.Utils;

/**
 * Overall F12 boss key input. Quits the game immediately at any stage!
 *
 * @author mathias fringes
 */
public final class BossKeyInputController implements KeyInput {

    // KeyInput

    public boolean keyPress(int keyCode) {
        switch(keyCode) {
            case KeyCodes.F12:
                System.exit(0);
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return Utils.stringer("BossKeyInputController");
    }

}
