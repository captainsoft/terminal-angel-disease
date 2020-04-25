/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.utils;

/**
 * 2d point.
 *
 * @author mathias fringes
 */
public class Point {

    // fields

    public static final Point zero = new Point(0, 0);
    public static final Point one = new Point(1, 1);

    public final int x;
    public final int y;

    // constructors

    public Point() {
        this(0, 0);
    }

    public Point(Point point) {
        this(point.x, point.y);
    }

    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    // public

    public final boolean equals(int x, int y) {
        return ((this.x == x) && (this.y == y));
    }

    public final boolean equals(Point position) {
        if (position == null) {
            return false;
        }
        if (position == this) {
            return true;
        }
        return equals(position.x, position.y);
    }

    // overridden

    @Override
    public final boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Point)) {
            return false;
        }
        Point p = (Point) object;
        return (equals(p));
    }

    @Override
    public final int hashCode() {
        return x ^ y;
    }

    @Override
    public final String toString() {
        return "(" + x + "/" + y + ")";
    }

}
