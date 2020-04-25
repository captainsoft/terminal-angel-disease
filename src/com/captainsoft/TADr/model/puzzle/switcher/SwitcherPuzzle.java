/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.switcher;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.puzzle.AbstractPuzzle;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.TileUpdate;
import com.captainsoft.TADr.model.puzzle.event.EventPuzzle;
import com.captainsoft.TADr.sound.SndFacade;

/**
 * Puzzle for a switch.
 *
 * @author mathias fringes
 */
public final class SwitcherPuzzle extends AbstractPuzzle {

    // fields

    private final Position position;
    private final Switcher switcher;

    // constructors

    public SwitcherPuzzle(Position p, PuzzleFileData data) {
        super();
        this.position = p;
        this.switcher = new Switcher(data);
    }

    // Puzzle

    @Override
    public void execute() {
        SndFacade.switchSound();
        gameEngine.updateTile(new TileUpdate(position, switcher.tileValue));
        for (Integer i : switcher.events) {
            new EventPuzzle(position).executeEvent(i);
        }
    }

}
