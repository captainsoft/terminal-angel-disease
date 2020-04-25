/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui_swing;

import java.awt.event.*;

/**
 * Closes the window on close (...).
 *
 * @author mathias fringes
 */
public final class CloseWindowAdapter extends WindowAdapter {

    // constructors

    public CloseWindowAdapter() {
        super();
    }

    // overridden methods

    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
    }

}