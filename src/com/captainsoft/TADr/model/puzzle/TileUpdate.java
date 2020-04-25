/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle;

import static com.captainsoft.spark.utils.Utils.stringer;

import com.captainsoft.TADr.model.Position;

/**
 * A specific request for a tile value update on a position.
 *
 * @author mathias fringes
 */
public final class TileUpdate {

    // fields

    private final Position position;
    private final TileValues tileValues;

    // constructors

    public TileUpdate(int x, int y, TileValues tileValues) {
        this(new Position(x, y), tileValues);
    }

    public TileUpdate(Position position, TileValues tileValues) {
        this.tileValues = tileValues;
        this.position = position;
    }

    // public

    public Position position() {
        return position;
    }

    public TileValues values() {
        return tileValues;
    }

    // overridden

    @Override
    public String toString() {
        return stringer("TileUpdate", position, tileValues);
    }

}
