/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.menux;

import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * A horizontal line separator.
 *
 * @author mathias fringes
 */
public final class MxSeparator extends UiBox {

    // fields

    int hgap;

    // constructors

    public MxSeparator() {
        this(5);
    }

    public MxSeparator(int hgap) {
        super();
        this.hgap = hgap;
        clickable(false);
    }

    // overridden

    @Override
    protected void draw(Surface s) {
        super.draw(s);

        int mid = this.height / 2 - 1;

        s.color(WindowColors.dk);
        s.line(hgap, mid, width - hgap - 1, mid);

        mid++;
        s.color(WindowColors.br);
        s.line(hgap, mid, width - hgap - 1, mid);
    }

}