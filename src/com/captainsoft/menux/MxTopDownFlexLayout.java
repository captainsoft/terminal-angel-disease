/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.menux;

import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Top down flexible layout.
 *
 * @author mathias fringes
 */
public final class MxTopDownFlexLayout {

    // fields

    public int next_y = 0;
    public int side_margin = 0;
    public int def_gap = 0;

    private final UiBoxContainer target;

    // constructors

    public MxTopDownFlexLayout(UiBoxContainer target) {
        super();
        this.target = target;
    }

    // public

    public MxTopDownFlexLayout add(UiBox component) {
        component.width = target.width - 2 * side_margin;
        component.y = next_y;
        component.x = side_margin;
        next_y += component.height;
        next_y += def_gap;
        target.add(component);
        return this;
    }

    public MxTopDownFlexLayout gap(int gap) {
        next_y += gap;
        return this;
    }

}
