/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.controller;

/**
 *
 * @author mathias fringes
 */
public final class MovementKeyState {

    private final boolean[] keys = new boolean[88];

    private int last = 0;

    public void press(int keyCode) {
        if (keyCode > 87) {
            return;
        }
        keys[keyCode] = true;
        last = keyCode;
    }

    public void release(int keyCode) {
        if (keyCode > 87) {
            return;
        }
        keys[keyCode] = false;
    }

    public int firstKeyCode() {
        if (keys[last]) return last;
        if (keys[37]) return 37;
        if (keys[38]) return 38;
        if (keys[39]) return 39;
        if (keys[40]) return 40;
        if (keys[65]) return 65;
        if (keys[68]) return 68;
        if (keys[83]) return 83;
        if (keys[87]) return 87;
        return -1;
    }

}