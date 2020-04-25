/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;

/**
 * The favorite null-does-nothing Animation.
 *
 * @author mathias fringes
 */
public final class NullAnimation implements FrameAnimation {

    // fields

    private FrameMx length;

    // constructors

    public NullAnimation() {
        this(null);
    }

    // public

    public NullAnimation(float length) {
        this(new FrameMx(length));
    }

    public NullAnimation(FrameMx length) {
        super();
        this.length = length;
    }

    // FrameAnimation

    @Override
    public FrameMx play(int step) {
        return (step == 1) ? length : null;
    }

}