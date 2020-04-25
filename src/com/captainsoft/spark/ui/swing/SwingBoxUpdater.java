/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.swing;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import com.captainsoft.spark.ui.BoxUpdater;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.utils.Log;

/**
 * Handles update requests from Boxes.
 *
 * @author mathias fringes
 */
public final class SwingBoxUpdater implements BoxUpdater {

    // fields

    private List<Component> components = new ArrayList<Component>(3);
    private Map<UiBox, Component> boxMap = new HashMap<UiBox, Component>(3);

    // constructors

    public SwingBoxUpdater() {
        super();
    }

    // public

    public void registerComponent(Component component, UiBox box) {
        components.add(component);
        boxMap.put(box, component);
    }

    public void updateBoxDrawing(UiBox... boxes) {
        synchronized (boxes[0].getLowestBox()) {
            for (UiBox b : boxes) {
                b.update();
            }
        }
        pingBoxRefresh(boxes[0]);
    }

    // private

    private Component getComponentForBox(UiBox box) {
        if (components.size() == 1) {
            return components.get(0);
        } else {
            return boxMap.get(box);
        }
    }

    private void pingBoxRefresh(UiBox box) {
        final UiBox lbox = box.getLowestBox();
        final Component c = getComponentForBox(lbox);
        if (c != null) {
            if (!SwingUtilities.isEventDispatchThread()) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        // Log.force("Painting asynchronly: " + lbox);
                        c.repaint();
                    }
                });
            } else {
                Log.force("----Painting NOT NOT NOT asynchronly: " + lbox);
                c.repaint();
            }
        } else {
            Log.warn("No component found for box " + lbox);
        }
    }

    // BoxUpdater

    public void update(UiBox box) {
        updateBoxDrawing(box);
    }

    // overridden

    @Override
    public String toString() {
        return this.getClass().getName() + ": size [" + components.size() + "]";
    }

}