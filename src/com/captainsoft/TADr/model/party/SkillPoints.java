/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.party;

import static com.captainsoft.spark.utils.ExcUtils.*;
import static com.captainsoft.spark.utils.Utils.*;

/**
 * Points for changeable (learning) skills (1-99).
 *
 * @author mathias fringes
 */
public final class SkillPoints {

    // fields

    private final static int MAX_VALUE = 99;

    private int value;

    // constructors

    public SkillPoints() {
        this(0);
    }

    public SkillPoints(int value) {
        super();
        value(value);
    }

    // accessors

    public void value(int value) {
        argIn("value", value, 0, MAX_VALUE);
        this.value = value;
    }

    public int value() {
        return value;
    }

    // public

    public boolean canLearn() {
        return (value < MAX_VALUE);
    }

    public boolean learn() {
        return learn(1);
    }

    public boolean learn(int value) {
        if (canLearn()) {
            this.value += value;
            if (this.value > MAX_VALUE) {
                this.value = MAX_VALUE;
            }
            return true;
        } else {
            return false;
        }
    }

    // overridden

    @Override
    public String toString() {
        return stringer("SkillPoints", value);
    }

}