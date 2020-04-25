package com.captainsoft.TADr.engine.commands;

import com.captainsoft.spark.ui.WindowController;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Show a window. Depends on the game state, i.e windows are shown only if the
 * disk menu is accessible.
 *
 * @author mathias
 */
public final class ShowWindowCommand extends GameEngineCommand {

    // fields

    private final WindowController wc;

    // constructors

    public ShowWindowCommand(WindowController wc) {
        this.wc = wc;
    }

    // Command

    public void execute() {
        if (gameEngine.canShowDiskMenu()) {
            gameEngine.showWindow(wc);
        }
    }

    // overridden

    @Override
    public String toString() {
        return stringer("ShowWindowCommand", wc);
    }

}