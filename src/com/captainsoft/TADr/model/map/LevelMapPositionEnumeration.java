/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.map;

import static com.captainsoft.TADr.model.map.LevelMap.X_LEN;
import static com.captainsoft.TADr.model.map.LevelMap.Y_LEN;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.PositionBox;

/**
 * Enumeration all Position objects in a LevelMap!
 *
 * @author mathias fringes
 */
public final class LevelMapPositionEnumeration extends PositionBox {

    public LevelMapPositionEnumeration() {
        super(new Position(1, 1), X_LEN, Y_LEN);
    }

}