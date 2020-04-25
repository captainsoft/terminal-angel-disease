/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui_swing;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * A panel with a colored background.
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public class ColorPanel extends JPanel {

    public ColorPanel(Color color) {
        super();
        this.setBackground(color);
    }

    public ColorPanel(Color color, LayoutManager layout) {
        super(layout);
        this.setBackground(color);
    }

}