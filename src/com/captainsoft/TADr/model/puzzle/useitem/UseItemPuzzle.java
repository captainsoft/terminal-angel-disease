/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.useitem;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.item.ItemType;
import com.captainsoft.TADr.model.puzzle.AbstractPuzzle;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.TileUpdate;
import com.captainsoft.TADr.model.puzzle.event.EventPuzzle;
import com.captainsoft.TADr.model.questlog.QuestLogEngine;
import com.captainsoft.TADr.sound.SndFacade;
import com.captainsoft.spark.utils.Log;

/**
 * Use an item in the action world!
 *
 * @author mathias fringes
 */
public final class UseItemPuzzle extends AbstractPuzzle {

    // fields

    private final Item item;
    private final Position position;
    private final UseItem useItem;

    // constructors

    public UseItemPuzzle(Position p, Item item, PuzzleFileData pd) {
        super();
        this.position = p;
        if (item != null) {
            this.item = item;
        } else {
            this.item = gameEngine.itemInHand();
        }
        this.useItem = new UseItem(pd);
    }

    // Puzzle

    public void execute() {
        Log.trace("Executing item puzzle");
        if (item == null) {
            Log.trace("Abort executing item puzzle, cause item is null.");
            return;
        }
        if (useItem.itemId != item.id()) {
            gameEngine.say("item.use.wrong");
            return;
        }
        if (item.type() == ItemType.Key) {
            SndFacade.keySound();
        }

        QuestLogEngine.inst.solveQuestAt(position);

        gameEngine.updateTile(new TileUpdate(position, useItem.tileValues));
        if (useItem.eventId > 0) {
            new EventPuzzle(position).executeEvent(useItem.eventId);
        }
        if (useItem.killItem) {
            gameEngine.deleteItem(item);
        }

    }

}
