/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.boxes.main;

import com.captainsoft.TADr.painting.GameLevelMapDrawer;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * The simple map view backdrop box.
 *
 * @author mathias fringes
 */
public final class SimpleMapView extends UiBoxContainer {

    // fields

    private final GameLevelMapDrawer mapDrawer;

    // constructors

    public SimpleMapView(GameLevelMapDrawer mapDrawer) {
        super(0, 0, GameLevelMapDrawer.PX_WIDTH, GameLevelMapDrawer.PX_HEIGHT);
        this.mapDrawer = mapDrawer;
    }

    // UiBoxContainer

    @Override
    protected void draw(Surface s) {
        s.blit(mapDrawer.surface(), 0, 0);
    }

}