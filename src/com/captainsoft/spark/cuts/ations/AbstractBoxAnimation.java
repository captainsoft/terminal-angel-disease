/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * Base class for Animation operationg on a box.
 *
 * @author mathias fringes
 */
public abstract class AbstractBoxAnimation implements FrameAnimation {

    // fields

    protected final UiBox box;
    protected final FrameMx defaultGap;

    private int currentStep = 0;

    // constructors

    public AbstractBoxAnimation(UiBox box) {
        this(box, null);
    }

    public AbstractBoxAnimation(UiBox box, FrameMx defaultGap) {
        super();
        this.box = box;
        this.defaultGap = defaultGap;
    }

    // public

    public FrameMx over() {
        return null;
    }

    // protected

    protected boolean time(int step) {
        return (step == currentStep);
    }

    protected FrameMx wait(float wait) {
        return new FrameMx(wait);
    }

    protected boolean toggle() {
        return (currentStep % 2 == 0);
    }

    protected void hide() {
        box.visible(false);
    }

    protected void showAt(int step) {
        if (time(step)) {
            box.visible(true);
        }
    }

    protected void hideAt(int step) {
        if (time(step)) {
            hide();
        }
    }

    // FrameAnimation

    public FrameMx play(int step) {
        currentStep = step;
        return defaultGap;
    }

}