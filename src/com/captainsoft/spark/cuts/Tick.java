/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts;

import com.captainsoft.spark.cuts.ations.FrameAnimation;

/**
 * Schedules Animations.
 *
 * @author mathias fringes
 */
final class Tick {

    // fields

    private final FrameAnimation animation;

    private int step;
    private FrameMx next;

    // constructors	

    public Tick(FrameMx start, FrameAnimation animation) {
        super();
        this.next = start;
        this.animation = animation;
        reset();
    }

    // public

    public void play() {
        FrameMx f = animation.play(step++);
        next = (f == null) ? null : next.add(f);
    }

    public void reset() {
        step = 1;
    }

    public FrameMx next() {
        return next;
    }

    public boolean isOver() {
        return (next == null);
    }

    // overridden

    @Override
    public String toString() {
        return "" + next + ": " + animation;
    }

}