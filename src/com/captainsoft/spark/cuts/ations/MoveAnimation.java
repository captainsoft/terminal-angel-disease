/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * Move the box to a new position.
 *
 * @author mathias fringes
 */
public final class MoveAnimation implements FrameAnimation {

    // fields

    private final boolean center;
    private final CPos movePosition;
    private final UiBox box;

    // constructors

    public MoveAnimation(UiBox box, CPos movePosition) {
        this(box, movePosition, true);
    }

    // public

    public MoveAnimation(UiBox box, CPos movePosition, boolean center) {
        super();
        this.box = box;
        this.movePosition = movePosition;
        this.center = center;
    }

    // FrameAnimation

    @Override
    public FrameMx play(int step) {
        if (center) {
            box.center();
        }
        box.move(movePosition.x, movePosition.y);
        return null;
    }

}
