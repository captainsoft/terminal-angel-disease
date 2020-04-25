/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.PuzzleLoader;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.puzzle.chest.ChestPuzzle;
import com.captainsoft.TADr.model.puzzle.event.EventPuzzle;
import com.captainsoft.TADr.model.puzzle.fight.FightPuzzle;
import com.captainsoft.TADr.model.puzzle.shop.ShopPuzzle;
import com.captainsoft.TADr.model.puzzle.switcher.SwitcherPuzzle;
import com.captainsoft.TADr.model.puzzle.talk.TalkPuzzle;
import com.captainsoft.TADr.model.puzzle.teleporter.TeleporterPuzzle;
import com.captainsoft.TADr.model.puzzle.useitem.UseItemPuzzle;
import com.captainsoft.spark.utils.Log;

/**
 * The main puzzle engine.
 *
 * @author mathias fringes
 */
public final class PuzzleEngine {

    // fields

    private final GameEngine gameEngine;
    private final PuzzleLoader puzzleLoader;

    // constructors

    public PuzzleEngine(GameEngine gameEngine) {
        super();
        this.gameEngine = gameEngine;
        this.puzzleLoader = TadRepo.inst().puzzleLoader();
    }

    // public

    public boolean hasActivePuzzle(Position p) {
        int pid = gameEngine.levelMap().tile(p).activePuzzle();
        return (pid > 0);
    }

    public boolean executeActivePuzzle(Position p) {
        int pid = gameEngine.levelMap().tile(p).activePuzzle();
        if (pid == 0) {
            return false;
        }
        Log.info("Executing active puzzle value: " + pid);
        executePuzzle(p, pid);
        return true;
    }

    public boolean executePassivePuzzle(final Position p) {
        final int pid = gameEngine.levelMap().tile(p).passivePuzzle();
        if (pid == 0) {
            return false;
        }
        Log.info("Executing passive puzzle value: " + pid + " @ " + p);
        executePuzzle(p, pid);
        return true;
    }

    public void doItemPuzzle(Item item, Position pos) {
        int pid = gameEngine.levelMap().tile(pos).passivePuzzle();
        if (pid == 0) {
            return;
        }
        Log.info("Executing item puzzle value: " + pid);
        PuzzleFileData data = puzzleLoader.loadPuzzle(gameEngine.levelMap().nr(), pid);
        if (data.id() == PuzzleFileData.ItemPuzzleId) {
            new UseItemPuzzle(pos, item, data).execute();
        } else {
            gameEngine.say("item.use.wrong");
        }
    }

    public void doChestLockpickPuzzle(Position pos, ItemPosition itemPosition) {
        int pid = gameEngine.levelMap().tile(pos).passivePuzzle();
        if (pid == 0) {
            return;
        }
        Log.info("Executing chest lockpick value: " + pid);
        PuzzleFileData data = puzzleLoader.loadPuzzle(gameEngine.levelMap().nr(), pid);
        if (data.id() == 6) {
            new ChestPuzzle(pos, data).execute(itemPosition);
        } else {
            gameEngine.say("item.use.wrong");
        }
    }

    // private

    private void executePuzzle(Position position, int pid) {
        PuzzleFileData data = puzzleLoader.loadPuzzle(gameEngine.levelMap().nr(), pid);
        Puzzle pz = createPuzzle(position, data);
        if (pz != null) {
            Log.debug("Created puzzle class: " + pz.getClass().getName());
            pz.execute();
        } else {
            Log.warn("Nothing found for position: " + position + " - PID: " + pid);
        }
    }

    private Puzzle createPuzzle(Position p, PuzzleFileData data) {
        switch (data.id()) {
            case 1:
                return new ShopPuzzle(gameEngine, data);
            case 2:
                return new SwitcherPuzzle(p, data);
            case 3:
                return new UseItemPuzzle(p, null, data);
            case 4:
                return new FightPuzzle(gameEngine, p, data);
            case 6:
                return new ChestPuzzle(p, data);
            case 8:
                return new EventPuzzle(p, data);
            case 7:
                return new TeleporterPuzzle(p, data);
            case 9:
                return new TalkPuzzle(p, data);
            default:
                return null;
        }
    }

}