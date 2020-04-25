/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model;

/**
 * Direction.
 *
 * @author mathias fringes
 */
public enum Direction {

    Nowhere,
    North,
    East,
    South,
    West,
    Here;

    public Direction getOpposite() {
        switch (this) {
            case North:
                return South;
            case South:
                return North;
            case East:
                return West;
            case West:
                return East;
            default:
                return this;
        }
    }

    public boolean isVertical() {
        return (this == West || this == East);
    }

    public boolean isHorizontal() {
        return (this == North || this == South);
    }

}