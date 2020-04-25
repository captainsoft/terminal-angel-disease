/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.talk;

import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.puzzle.*;

/**
 * Talk puzzle data.
 *
 * @author mathias fringes
 */
final class Talk {

    // fields

    private final int talkPageId;
    private final int hasItemTalkId;
    private final int hasNoItemTalkId;

    final int dependingItemId;
    final boolean takeItem;

    int thirdMapValue = TileValues.NOV;
    int thirdMapHasItem = TileValues.NOV;
    int thirdMapHasNoItem = TileValues.NOV;

    // constructors

    public Talk(PuzzleFileData fileData) {
        super();
        talkPageId = fileData.value(1);
        dependingItemId = fileData.value(2);
        hasItemTalkId = fileData.value(3);
        hasNoItemTalkId = fileData.value(4);
        takeItem = (fileData.value(5) == 1);
        thirdMapValue = fileData.value(6);
        thirdMapHasNoItem = fileData.value(7);
        thirdMapHasItem = fileData.value(8);
    }

    // public

    public int getRealTalkPageId(Party party) {
        if (dependingItemId > 0) {
            return (party.inventory().hasItem(dependingItemId) ? hasItemTalkId : hasNoItemTalkId);
        } else {
            return talkPageId;
        }
    }

}
