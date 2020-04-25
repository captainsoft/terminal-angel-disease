/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;

/**
 * An descriptor for a Animation.
 *
 * @author mathias fringes
 */
public interface FrameAnimation {

    /**
     * Plays the next step of this animation, starting with one.
     *
     * @param step the step
     * @return The amount of gap after the animation, or null if the animation is over.
     */
    public FrameMx play(int step);

}