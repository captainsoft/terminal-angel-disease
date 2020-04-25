/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts.ations;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.cuts.FrameMx;

/**
 * A chain of Frame Animations.
 *
 * @author mathias fringes
 */
public final class AnimationChain implements FrameAnimation {

    // fields

    private int index;
    private int shiftStep;
    private List<FrameAnimation> animations;

    // constructors

    public AnimationChain() {
        super();
        animations = new ArrayList<FrameAnimation>(3);
        index = 0;
        shiftStep = 0;
    }

    // public

    public AnimationChain add(FrameAnimation... animations) {
        for (FrameAnimation a : animations) {
            this.animations.add(a);
        }
        return this;
    }

    public AnimationChain addAndWait(FrameAnimation animation, float wait) {
        add(new WaitAnimation(animation, new FrameMx(wait)));
        return this;
    }

    public AnimationChain addWait(float f) {
        add(new WaitAnimation(f));
        return this;
    }

    // overridden

    @Override
    public FrameMx play(final int step) {
        FrameMx nextFrame = null;
        if (index < animations.size()) {
            int playStep = step - shiftStep;
            nextFrame = animations.get(index).play(playStep);
            if (nextFrame == null) {
                index++;
                shiftStep += (step - shiftStep - 1);
                nextFrame = play(step);
            }
        }
        return nextFrame;
    }

}