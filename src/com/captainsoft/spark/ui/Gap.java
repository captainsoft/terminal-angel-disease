/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui;

/**
 * Gap measurement between components.
 *
 * @author mathias fringes
 */
public final class Gap {

    // fields

    public int top;
    public int right;
    public int bottom;
    public int left;

    // constructors

    public Gap() {
        this(0, 0, 0, 0);
    }

    public Gap(int top, int right, int bottom, int left) {
        super();
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

}