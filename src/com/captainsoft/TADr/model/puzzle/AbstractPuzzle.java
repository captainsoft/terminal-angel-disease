/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;

/**
 * Base class for puzzles.
 *
 * @author mathias
 */
public abstract class AbstractPuzzle implements Puzzle {

    protected final GameEngine gameEngine;

    protected AbstractPuzzle() {
        super();
        this.gameEngine = TadRepo.inst().GameEngine();
    }

}