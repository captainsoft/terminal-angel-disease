/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui_swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * A window that closes the application when it is closed.
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public class ExitFrame extends JFrame {

    // constructors

    public ExitFrame() {
        addWindowListener(new ExitWindowListener());
    }

    //
    // nested

    private final class ExitWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

}