/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.menux;

import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * A lined bordered box.
 *
 * @author mathias fringes
 */
public final class MxBorder extends UiBox {

    // constructors

    public MxBorder() {
        this(100, 10);
    }

    public MxBorder(int width, int height) {
        super(0, 0, width, height);
        clickable(false);
    }

    // overridden

    @Override
    protected void draw(Surface s) {
        super.draw(s);

        s.color(WindowColors.dk);
        s.rect(0, 0, width - 1, height - 1);

        s.color(WindowColors.br);
        s.rect(1, 1, width - 1, height - 1);
    }

}