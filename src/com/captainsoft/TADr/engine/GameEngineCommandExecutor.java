/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine;

import com.captainsoft.spark.command.AbstractCommand;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.mouse.BoxCommandExecuter;
import com.captainsoft.spark.ui.mouse.ClickCommand;

/**
 * Command executor for direct commands via the game engine.
 *
 * @author mathias fringes
 */
public final class GameEngineCommandExecutor implements BoxCommandExecuter {

    // constructors

    public GameEngineCommandExecutor() {
        super();
    }

    // overridden

    public void execute(Command c) {
        doExecute(c);
    }

    public void execute(final ClickCommand cc, final UiBox box, final int x, final int y, final MouseButton button) {

        doExecute(new AbstractCommand() {
            public void execute() {
                cc.click(box, x, y, button);
            }
        });
    }

    // private

    private void doExecute(Command c) {
        TadRepo.inst().GameEngine().directCommand(c);
    }

}
