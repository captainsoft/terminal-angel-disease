/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.spark.utils.Log;

/**
 * Shows the disk menu.
 *
 * @author mathias fringes
 */
public final class ShowDiskCommand extends GameEngineCommand {

    // Command

    public void execute() {
        if (gameEngine.canShowDiskMenu()) {
            Log.info("Show disk menu");
            MenuController mc = new MenuController(gameEngine);
            mc.show();
        }
    }

    // overridden

    @Override
    public String toString() {
        return "(ShowDiskCommand)";
    }

}