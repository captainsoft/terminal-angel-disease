/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.box;

import java.awt.*;

import com.captainsoft.spark.ui.drawing.*;

/**
 * A Ui box with background (surface or color).
 *
 * @author mathias fringes
 */
public class UiDrawBox extends UiBox {

    // fields

    public Surface background;
    public Color backColor;

    // constructors

    public UiDrawBox() {
        this(0, 0, 100, 100);
    }

    public UiDrawBox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    // public

    public final void stampBackground(Surface surface) {
        this.background = surface.stamp(x, y, width, height);
    }

    // overridden

    @Override
    protected void draw(Surface s) {
        super.draw(s);
        if (backColor != null) {
            s.fill(backColor, 0, 0, width, height);
        }
        if (background != null) {
            s.blit(background);
        }
    }

}