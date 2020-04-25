/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.render;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Base class for animations. Subclasses are asked to override the play() method and stick to
 * its contract.
 *
 * Animations steps are executed (via play() method) when a certain amount of milliseconds has passed.
 * The step variable keeps track of the animation frames and is independent of the time
 *
 * @author mathias fringes.
 */
public abstract class Animation {

    // fields

    /**
     * Indicated whether the animation was already played.
     */
    private boolean over = false;

    /**
     * The amount of millis still to pass until the next play() is called.
     */
    private int nextTime = 0;

    /**
     * Convenience variable, to be used by subclasses to keep track of the animation step.
     */
    protected int step = 0;

    protected String name;

    // constructors

    public Animation() {
        super();
    }

    public Animation(String name) {
        super();
        this.name = name;
    }

    // public

    /**
     * Pass this animation for the given amount of millis. This will play the animation, if the time
     * is scheduled! Don't call this method if the animation is already over, it will throw an exception!
     *
     * @param millis the amount of millis to pass.
     */
    public final void count(long millis) {
        if (over()) {
            throw new RuntimeException("Animation " + this + " is already over!");
        }
        //
        nextTime -= millis;
        //
        if (nextTime <= 0) {
            //
            int next = 0;
            while (next == 0) {
                next = play();
            }
            //
            if (next < 0) {
                over = true;
            } else {
                nextTime = next;
            }
        }
    }

    public final boolean over() {
        return over;
    }

    public void stop() {
        over = true;
    }

    protected final void restart() {
        over = false;
        nextTime = 0;
    }

    // abstract

    /**
     * Plays the next step of the animation. Is called by the animation framework, never
     * from other components!
     *
     * @return the amount of milliseconds to pass until the *next* step, or -1 if the animation is over.
     */
    public abstract int play();

    // overridden

    @Override
    public String toString() {
        return stringer("Animation", name);
    }

}
