/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.box;

import java.awt.*;

import com.captainsoft.spark.ui.drawing.*;

/**
 * A box with a colored background.
 *
 * @author mathias fringes
 */
public final class ColorBox extends UiBoxContainer {

    // fields

    public Color color = Color.BLACK;

    // constructors

    public ColorBox() {
        super();
    }

    public ColorBox(Color color) {
        super();
        this.color = color;
    }

    public ColorBox(int width, int height) {
        super(width, height);
    }

    // overridden

    @Override
    protected void draw(Surface s) {
        s.color(color);
        s.fill(0, 0, width, height);
    }

}
