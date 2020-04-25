/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.GameLoader;
import com.captainsoft.TADr.model.Game;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Saves the current game.
 *
 * @author mathias fringes
 */
public final class QuickSaveCommand extends GameEngineCommand {

    // constructors

    public QuickSaveCommand() {
        super();
    }

    // Command

    public void execute() {
        if (!gameEngine.canShowDiskMenu()) {
            return;
        }
        Game game = gameEngine.game();
        if (game == null) {
            return;
        }
        if (game.isNewGame()) {
            gameEngine.sayAnonym("quicksave.cannot.isnew");
        } else {
            GameLoader gameLoader = TadRepo.inst().GameLoader();
            gameLoader.save(game);
            gameEngine.sayAnonym("quicksave", game.name());
        }
    }

    // overridden

    @Override
    public String toString() {
        return stringer("QuickSaveCommand");
    }

}