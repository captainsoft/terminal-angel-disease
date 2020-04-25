/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.swing;

import java.awt.Component;

import javax.swing.SwingUtilities;

import com.captainsoft.spark.ui.BoxUpdater;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * An updater for a single, specified Java AWT component.
 *
 * @author mathias fringes
 */
public final class SingleComponentUpdater implements BoxUpdater {

    // fields

    private Component component;

    // constructors

    public SingleComponentUpdater(Component component) {
        super();
        this.component = component;
    }

    public void component(Component component) {
        this.component = component;
    }

    // overridden

    @Override
    public void update(UiBox box) {
        box.update();
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    component.repaint();
                }
            });
        } else {
            component.repaint();
        }
    }

}