/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.ui;

/**
 * You must have a Point/Position class in an ui package!
 *
 * @author mathias fringes
 */
public final class CPos {

    // fields 

    public int x = 0;

    public int y = 0;

    // constructors

    public CPos() {
        super();
    }

    public CPos(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public CPos(CPos pos) {
        this(pos.x, pos.y);
    }

    // public

    public CPos add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public CPos add(CPos pos) {
        return add(pos.x, pos.y);
    }

    public boolean equals(CPos pos) {
        if (pos == null) {
            return false;
        }
        return ((x == pos.x) && (y == pos.y));
    }

    public CPos scale(float ratio) {
        return scale(ratio, ratio);
    }

    public CPos scale(float ratiox, float ratioy) {
        this.x = Math.round(this.x * ratiox);
        this.y = Math.round(this.y * ratioy);
        return this;
    }

    public CPos set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    // overridden

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CPos)) {
            return false;
        }
        return this.equals((CPos) obj);
    }

    @Override
    public int hashCode() {
        return x ^ y;
    }

    @Override
    public String toString() {
        return "CPos (" + x + "|" + y + ")";
    }

}