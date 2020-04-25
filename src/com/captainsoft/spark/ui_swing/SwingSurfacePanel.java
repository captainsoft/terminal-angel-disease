/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui_swing;

import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

import javax.swing.*;
import java.awt.*;

/**
 * @author mathias fringes
 */
public class SwingSurfacePanel extends JPanel {


    // fields

    private final Surface surface;

    // constructors

    public SwingSurfacePanel(Surface surface) {
        super();
        this.surface = surface;
        setSize(new Dimension(surface.width(), surface.height()));
        setPreferredSize(getSize());
    }

    // public

    public final Surface surface() {
        return surface;
    }

    // overridden

    @Override
    public boolean isFocusable() {
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        synchronized (surface) {
            g.drawImage(surface.image(), 0, 0, this);
        }
    }

}
