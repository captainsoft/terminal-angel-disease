/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.loop;

import com.captainsoft.spark.utils.Log;

/**
 * Checks the FPS every second and gives "time to sleep" - advice! ;)
 *
 * @author mathias fringes
 */
final class FPSCounter {

    // fields

    private final int ONE_SECOND = 1000;
    private final int TARGET = 60;

    private int fps = 0;
    private int sleep = 10;
    private long last;

    // constructors

    public FPSCounter() {
        super();
        last = System.currentTimeMillis();
    }

    // public

    /**
     * Gets the current amount of ms that the engine should sleep to keep
     * the fps closest to the target.
     *
     * @return
     */
    public int sleep() {
        return sleep;
    }

    public void count() {
        fps++;
        if (System.currentTimeMillis() - last > ONE_SECOND) {

            if (fps > TARGET) {
                sleep++;
            } else if (fps < TARGET) {
                sleep--;
                if (sleep < 0) {
                    sleep = 0;
                }
            }
            // Log.fps("FPS: " + fps + "  sleep " + sleep);

            last = System.currentTimeMillis();
            fps = 0;
        }
    }

}
