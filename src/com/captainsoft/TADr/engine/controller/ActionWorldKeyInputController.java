package com.captainsoft.TADr.engine.controller;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.commands.QuickSaveCommand;
import com.captainsoft.TADr.engine.commands.ShowDiskCommand;
import com.captainsoft.TADr.engine.commands.ShowWindowCommand;
import com.captainsoft.TADr.engine.debug.DebugKeysController;
import com.captainsoft.TADr.gui.windows.CaptainsoftInfoWindow;
import com.captainsoft.TADr.gui.windows.HelpWindow;
import com.captainsoft.TADr.model.questlog.QuestLogWindow;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.control.key.KeyInputRespChain;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.Utils;

/**
 * Handles general key input.
 *
 * @author mathias fringes
 */
public final class ActionWorldKeyInputController extends KeyInputRespChain {

    // fields

    private final GameEngine gameEngine;

    // constructors

    public ActionWorldKeyInputController(GameEngine gameEngine) {
        super();
        this.gameEngine = gameEngine;
        add(new DebugKeysController(gameEngine));
    }

    // KeyInput

    @Override
    public boolean keyPress(int keyCode) {

        switch (keyCode) {

            case 49:
            case 50:
            case 51:
            case 52:
                gameEngine.mainViewer().switchMember(keyCode-48);
                break;

            case KeyCodes.Space:
                gameEngine.stopParty();
                break;

            case KeyCodes.Shift:
                gameEngine.nextCommand(new Command() {
                       public void execute() {
                           gameEngine.meeeehParty();
                       }
                   });
                break;

            case KeyCodes.B:
                gameEngine.mainViewer().mapDrawer.paintingInfo.turn();
                gameEngine.mainViewer().mapDrawer.retileParty();
                gameEngine.mainViewer().MapUpdater().update();
                break;

            case KeyCodes.F1:
                gameEngine.nextCommand(new ShowWindowCommand(new HelpWindow()));
                break;

            case KeyCodes.F2:
                gameEngine.nextCommand(new ShowWindowCommand(new QuestLogWindow()));
                break;

            case KeyCodes.F3:
                gameEngine.nextCommand(new ShowWindowCommand(new CaptainsoftInfoWindow()));
                break;

            case KeyCodes.F5:
                gameEngine.nextCommand(new QuickSaveCommand());
                break;

            case KeyCodes.ESC:
                gameEngine.nextCommand(new ShowDiskCommand());
                break;

            default:
                Log.debug("Unmapped default keycode: " + keyCode);
                break;
        }

        return super.keyPress(keyCode);
    }

    // overridden

    @Override
    public String toString() {
        return Utils.stringer("ActionWorldKeyInputController", super.toString());
    }

}