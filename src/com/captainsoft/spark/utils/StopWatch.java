/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.utils;

/**
 * A stop watch Measures time.
 *
 * @author mathias fringes
 */
public final class StopWatch {

    // fields

    private final long start;
    private final String info;

    // constructors

    public StopWatch() {
        this("");
    }

    // public

    public StopWatch(String info) {
        super();
        this.info = info;
        start = System.currentTimeMillis();
    }

    public void print() {
        System.out.println(this);
    }

    // overridden

    @Override
    public String toString() {
        long now = System.currentTimeMillis();
        return "StopWatch: " + info + " " + (now - start) + " ms";
    }

}
