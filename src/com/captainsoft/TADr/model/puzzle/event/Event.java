/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle.event;

import com.captainsoft.TADr.model.*;
import com.captainsoft.TADr.model.puzzle.*;

/**
 * An event that changes tile values.
 *
 * @author mathias fringes
 */
public final class Event {

    // fields

    public final boolean isActive;

    public final int nextEventId;

    public final TileValues tileValue;

    public final Position ownPosition;

    // constructors

    public Event(int[] data) {
        super();
        if (data[0] != 0 && data[1] != 0) {
            ownPosition = new Position(data[0], data[1]);
        } else {
            ownPosition = null;
        }

        tileValue = new TileValues();
        for (int i = 0; i < 4; i++) {
            tileValue.set(i + 1, data[2 + i]);
        }

        nextEventId = ((data[6] == TileValues.NOV) ? 0 : data[6]);
        isActive = (data[6] == TileValues.NOV);
    }

}
