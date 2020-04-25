/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.render;

import java.util.ArrayList;
import java.util.List;

/**
 * A planned, build-up Animation, that consists of AnimationItems. Useful for having
 * anonymous Animation classes.
 *
 * @author mathias fringes
 */
public final class PlanAnimation extends Animation {

    // fields

    private List<AnimationItem> items;
    private int step = 0;

    // constructors

    public PlanAnimation() {
        this.items = new ArrayList<AnimationItem>();
    }

    // public

    public void add(AnimationItem item) {
        items.add(item);
    }

    public void gap(final int length) {
        items.add(new AnimationItem() {

            public int play() {
                return length;
            }
        });
    }

    // Animation

    @Override
    public int play() {
        AnimationItem item = items.get(step);
        step++;
        return item.play();
    }

}