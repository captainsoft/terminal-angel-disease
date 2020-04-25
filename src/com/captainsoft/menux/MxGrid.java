/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.menux;

import com.captainsoft.spark.ui.CDim;
import com.captainsoft.spark.ui.Gap;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * A grid layout.
 *
 * @author mathias fringes
 */
public final class MxGrid {

    // fields

    public int x_span;
    public int y_span;
    public CDim padding = new CDim(0, 0);
    public Gap margin = new Gap();

    private int xg_s = 0;
    private int yg_s = 0;
    private UiBoxContainer target;

    // constructors

    public MxGrid(int x_span, int y_span) {
        super();

        this.x_span = x_span;
        this.y_span = y_span;
    }

    // public

    public MxGrid target(UiBoxContainer target) {
        this.target = target;
        xg_s = (target.width - margin.left - margin.right) / x_span;
        yg_s = (target.height - margin.top - margin.bottom) / y_span;
        return this;
    }

    public MxGrid add(UiBox component, int x, int y) {
        component.x = (x - 1) * xg_s + (isNotFirst_x(x) * padding.width) + margin.left;
        component.y = (y - 1) * yg_s + (isNotFirst_y(y) * padding.height) + margin.top;
        target.add(component);
        return this;
    }

    public MxGrid fill(UiBox component, int x, int y) {
        return fill(component, x, y, 1, 1);
    }

    public MxGrid fill(UiBox component, int x, int y, int xs, int ys) {
        component.width = xg_s * xs - isNotFirst_x(x) * padding.width;
        component.height = yg_s * ys - isNotFirst_y(y) * padding.height;
        return add(component, x, y);
    }

    // private

    private int isNotFirst_x(int x) {
        return (x == 1) ? 0 : 1;
    }

    private int isNotFirst_y(int y) {
        return (y == 1) ? 0 : 1;
    }

}
