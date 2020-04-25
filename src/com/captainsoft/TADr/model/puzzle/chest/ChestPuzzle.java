/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.chest;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.puzzle.AbstractPuzzle;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.TileValues;
import com.captainsoft.TADr.model.questlog.QuestLogEngine;
import com.captainsoft.TADr.sound.SndFacade;
import com.captainsoft.spark.utils.Log;

import static com.captainsoft.spark.utils.Truth.is;

/**
 * Puzzle for a chest.
 *
 * @author mathias fringes
 */
public final class ChestPuzzle extends AbstractPuzzle {

    // fields

    private final Chest chest;

    private final Position position;

    // constructors 

    public ChestPuzzle(Position position, PuzzleFileData data) {
        super();
        this.chest = createFromPuzzleFileData(data);
        this.position = position;
    }

    // private

    private Chest createFromPuzzleFileData(PuzzleFileData data) {
        Chest chest = new Chest();
        for (int i = 0; i < 6; i++) {
            chest.item(i, TadRepo.inst().ItemRepo().item(data.value(i + 4)));
        }
        chest.needsKey = (data.value(1) >= 99);
        chest.thiefLevel = data.value(1);
        chest.tileValues = new TileValues();
        Log.log(data);
        chest.tileValues.set(1, data.value(2));
        chest.tileValues.set(3, data.value(3));
        // debug thing cause is forgot sometimes, causes you can open a chest eternally...!
        chest.tileValues.set(3, 0);
        Log.log(chest.tileValues);
        return chest;
    }

    // public

    public void execute(ItemPosition lockPickItemPosition) {
        Party party = gameEngine.party();
        boolean useLockPick = (lockPickItemPosition != null);

        if (chest.needsKey) {
            String key = useLockPick ? "chest.needsKeyNoDietrich" : "chest.needsKey";
            gameEngine.say(party.detective(), key);
            return;
        }

        if (party.detective().specialMemberSkill.value() >= chest.thiefLevel || useLockPick) {

            if (useLockPick) {
                gameEngine.deleteItem(lockPickItemPosition);
            }

            showChest(party);

            QuestLogEngine.inst.solveQuestAt(position);

        } else {
            if (!useLockPick) {
                gameEngine.say(party.detective(), "chest.cannotOpen");
            }
        }
    }

    private void showChest(Party party) {
        boolean update = chest.updateTile(gameEngine.levelMap().tile(position));
        if (update) {
            gameEngine.mainViewer().updatePosition(position);
        }
        SndFacade.chestSound();
        ChestWindowController wc = new ChestWindowController(chest);

        Position center = gameEngine.mainViewer().mapDrawer.tileView().center();
        Position partyPosition = party.position();
        wc.leftDisplay = partyPosition.x < center.x;

        gameEngine.showWindow(wc);
    }

    // Puzzle

    public void execute() {
        execute(null);
    }

}
