/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui.box;

/**
 * A 2D rectangular box structure.
 *
 * @author mathias fringes
 */
public class Box {

    // fields

    public int x;
    public int y;
    public int height;
    public int width;
    public String name = "";

    // constructors

    public Box() {
        this(0, 0);
    }

    public Box(int width, int height) {
        this(0, 0, width, height);
    }

    public Box(int x, int y, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // translation

    public final void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public final void pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final void posMiddle(int x, int y) {
        this.x = x - (width / 2);
        this.y = y - (height / 2);
    }

    public final void scale(int width, int height) {
        this.width += width;
        this.height += height;
    }

    public final void set(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public final void size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // public

    public final boolean contains(int x, int y) {
        return (x >= this.x && y >= this.y && x <= this.x + width - 1 && y <= this.y + height - 1);
    }

    // overridden

    @Override
    public String toString() {
        return name + " [" + x + "," + y + "-" + (x + width - 1) + "," + (y + height - 1) + "] (" + width + "x" + height + ")";
    }

}
