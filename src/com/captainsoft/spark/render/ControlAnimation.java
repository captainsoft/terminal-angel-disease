/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.render;

/**
 * An Animation with extended control methods to be executed before, after,
 * and after each step.
 *
 * @author mathias fringes
 */
public class ControlAnimation extends Animation {

    // fields

    private final Animation animation;
    private boolean firstStep = false;

    // constructors

    public ControlAnimation(Animation animation) {
        super();
        this.animation = animation;
    }

    // protected

    protected void before() {
    }

    protected void afterStep() {
    }

    protected void after() {
    }

    // Animation

    @Override
    public final int play() {
        if (firstStep) {
            before();
            firstStep = false;
        }
        int nextMillis = animation.play();
        afterStep();
        if (nextMillis < 0) {
            after();
        }
        return nextMillis;
    }

}