/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.event;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.PuzzleLoader;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.puzzle.AbstractPuzzle;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.TileUpdate;

/**
 * Puzzle for an Event.
 *
 * @author mathias fringes
 */
public final class EventPuzzle extends AbstractPuzzle {

    // fields

    private final PuzzleLoader puzzleLoader;
    private final PuzzleFileData puzzleData;
    private Position position;

    // constructors

    public EventPuzzle(Position p) {
        this(p, null);
    }

    public EventPuzzle(Position p, PuzzleFileData data) {
        super();
        this.puzzleLoader = TadRepo.inst().puzzleLoader();
        this.puzzleData = data;
        this.position = p;
    }

    // public

    public void executeEvent(int eventId) {
        if (eventId != 0 && eventId != 255) {
            Event event = puzzleLoader.loadEvent(gameEngine.levelMap().nr(), eventId);
            playEvent(event);
        }
    }

    // private

    private void playEvent(Event event) {
        Position position = (event.ownPosition == null) ? gameEngine.party().position() : event.ownPosition;
        gameEngine.updateTile(new TileUpdate(position, event.tileValue));
        executeEvent(event.nextEventId);
        if (event.isActive) {
            gameEngine.reExecuteTileAction();
        }
    }

    // Puzzle

    public void execute() {
        for (int i = 1; i < 9; i++) {
            int eventId = puzzleData.value(i);
            executeEvent(eventId);
        }
        if (puzzleData.value(9) != 255) {
            gameEngine.levelMap().tile(position).set(3, puzzleData.value(9));
        }
    }

}
