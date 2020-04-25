/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.utils.Log;

/**
 * Collects boxes that need to be updated.
 *
 * @author mathias fringes
 */
final class DirtyUpdater {

    // fields

    private MainViewer mainViewer;

    private Set<UiBox> dirtyBoxes = new HashSet<UiBox>();

    // constructors

    public DirtyUpdater(MainViewer mainViewer) {
        super();
        this.mainViewer = mainViewer;
    }

    // public

    public void dirty(UiBox... boxes) {
        Collections.addAll(dirtyBoxes, boxes);
    }

    public void dirtyUpdate(UiBox... boxes) {
        dirty(boxes);
        dirtyUpdate();
    }

    public void dirtyUpdate() {
        if (dirtyBoxes.size() == 0) {
            Log.warn("Dirty update with no boxes!");
            return;
        }
        UiBox[] boxes = new UiBox[dirtyBoxes.size()];
        dirtyBoxes.toArray(boxes);
        mainViewer.updateBox(boxes);
        dirtyBoxes.clear();
    }

}