/*
 * Copyright Captainsoft 2010-2012.
 * All rights reserved.
 */
package com.captainsoft.spark.cuts.ations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * Switches boxes on/off.
 *
 * @author mathias fringes
 */
public final class VisibleAnimation implements FrameAnimation {

    // fields

    private boolean visible;
    private List<UiBox> boxes = new ArrayList<UiBox>(0);

    // constructors

    public static VisibleAnimation hide(UiBox... boxes) {
        VisibleAnimation va = new VisibleAnimation();
        va.boxes.addAll(Arrays.asList(boxes));
        va.visible = false;
        return va;
    }

    public VisibleAnimation() {
        super();
    }

    public VisibleAnimation(UiBox box) {
        this(box, true);
    }

    public VisibleAnimation(UiBox box, boolean visible) {
        super();
        this.boxes.add(box);
        this.visible = visible;
        //
        if (visible) {
            box.visible(false);
        }
    }

    // Animation

    @Override
    public FrameMx play(int step) {
        for (UiBox box : boxes) {
            box.visible(visible);
        }
        return null;
    }

}