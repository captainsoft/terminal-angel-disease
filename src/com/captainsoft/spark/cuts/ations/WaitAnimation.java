/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;

/**
 * Plays an animation, and waits a certain time afterwards.
 *
 * @author mathias fringes
 */
public final class WaitAnimation implements FrameAnimation {

    // fields

    private FrameAnimation animation;
    private FrameMx wait;
    private boolean over = false;

    // constructors

    public WaitAnimation(float wait) {
        this(null, new FrameMx(wait));
    }

    public WaitAnimation(FrameAnimation animation, FrameMx wait) {
        super();
        this.animation = animation;
        this.wait = wait;
    }

    // FrameAnimation

    @Override
    public FrameMx play(int step) {
        if (over) {
            return null;
        } else {
            if (animation == null) {
                over = true;
                return wait;
            } else {
                FrameMx next = animation.play(step);
                if (next == null) {
                    over = true;
                    return wait;
                } else {
                    return next;
                }
            }
        }
    }

}