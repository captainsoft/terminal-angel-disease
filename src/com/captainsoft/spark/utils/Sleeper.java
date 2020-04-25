/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.utils;

/**
 * Sleeps the rest of a specified start time.
 *
 * @author mathias fringes
 */
public final class Sleeper {

    // fields

    private int sleep = 0;
    private long start = 0;
    private float factor = 1;

    // constructors

    public Sleeper(int sleep) {
        super();
        time(sleep);
    }

    // public

    public void sleep() {
        long gap = System.currentTimeMillis() - start;
        long rest = sleep - gap;
        if (rest > 0) {
            Utils.sleepyMillis(factoredSleep());
        }
    }

    public void sleepAndReset() {
        sleep();
        reset();
    }

    public void reset() {
        start = System.currentTimeMillis();
    }

    public void time(int sleep) {
        this.sleep = sleep;
        reset();
    }

    public void timeAndSleep(int sleep) {
        time(sleep);
        sleep();
    }

    public void factor(float factor) {
        this.factor = factor;
    }

    // private

    private long factoredSleep() {
        return (factor != 1) ? Math.round(sleep * factor) : sleep;
    }

    // overridden

    @Override
    public String toString() {
        return "Sleeper: " + sleep + " (f:" + factor + ")";
    }

}
