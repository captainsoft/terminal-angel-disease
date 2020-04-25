/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.puzzle;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Raw puzzle data loaded from the puzzle data file.
 *
 * @author mathias fringes
 */
public final class PuzzleFileData {

    // constants

    public static final int ItemPuzzleId = 3;

    // fields

    private int[] data = new int[10];

    // constructors

    public PuzzleFileData() {
        super();
    }

    // public

    public int id() {
        return data[0];
    }

    public int value(int index) {
        return data[index];
    }

    public void value(int index, int value) {
        data[index] = value;
    }

    // overridden

    @Override
    public String toString() {
        return stringer("PuzzleFileData", data);
    }

}