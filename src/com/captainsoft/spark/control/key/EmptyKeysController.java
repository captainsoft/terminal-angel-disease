/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.control.key;

/**
 * Does either let through or stops the key input.
 *
 * @author mathias fringes
 */
public final class EmptyKeysController implements KeyInput {

    // fields

    private boolean bouncer;

    // constructors

    public EmptyKeysController() {
        this(false);
    }

    // public

    public EmptyKeysController(boolean bouncer) {
        this.bouncer = bouncer;
    }

    // KeyInput

    public boolean keyPress(int keyCode) {
        return bouncer;
    }

}
