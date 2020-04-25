/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.painting.animations;

import java.awt.Color;

import com.captainsoft.TADr.painting.GameLevelMapDrawer;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Curtain animations when entering a new map.
 *
 * @author mathias fringes
 */
public final class VorhangAnimation extends Animation {

    // fields

    private final Updater updater;
    private final Surface surface;
    private final GameLevelMapDrawer simpleMapDrawer;

    // constructors

    public VorhangAnimation(Updater updater, Surface surface, GameLevelMapDrawer simpleMapDrawer) {
        this.updater = updater;
        this.surface = surface;
        this.simpleMapDrawer = simpleMapDrawer;
    }

    // private

    private void vorhangZu(int step) {
        int width = surface.width();
        int height = surface.height();

        int factor = step * 10;

        surface.fill(Color.BLACK, 0, 0, factor + 89, height);
        surface.fill(Color.BLACK, width - factor - 89, 0, width, height);
        //
        surface.fill(Color.BLACK, 0, 0, width, factor - 10);
        surface.fill(Color.BLACK, 0, height - factor - 10, width, height);

        simpleMapDrawer.flip();
        updater.update();
    }

    // Animation

    @Override
    public int play() {
        step++;
        if (step < 23) {
            vorhangZu(step);
            return 70;
        } else {
            return -1;
        }
    }

}
