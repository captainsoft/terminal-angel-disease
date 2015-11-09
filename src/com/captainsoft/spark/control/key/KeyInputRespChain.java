/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.control.key;

import com.captainsoft.spark.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * KeyInput container as a chain of responsibility.
 *
 * @author mathias fringes
 */
public class KeyInputRespChain implements KeyInput {

    // fields

    private final List<KeyInput> list = new ArrayList<KeyInput>();

    // constructors

    public KeyInputRespChain() {
        super();
    }

    public KeyInputRespChain(KeyInput ... input) {
        super();
        for (KeyInput ki : input) {
            add(ki);
        }
    }

    // public

    public final void add(KeyInput input) {
        if (input != this) {
            list.add(input);
        }
    }

    public final void clear() {
        list.clear();
    }

    public final void lenientOn(KeyInput keyInput) {
        first(new KeyInputRespChain(keyInput, new EmptyKeysController(true)));
    }

    public final void first(KeyInput input) {
        if (input != this) {
            list.add(0, input);
        }
    }

    public final void removeFirst(int count) {
        for (int i = 0; i < count; i++) {
            if (list.size() > 0) {
                list.remove(0);
            }
        }
    }

    public final void set(KeyInput input) {
        list.clear();
        add(input);
    }

    // KeyInput

    public boolean keyPress(int keyCode) {

        for (KeyInput k : list) {
           if (k.keyPress(keyCode)) {
               return true;
           }
        }
        return false;
    }

    // overridden

    @Override
    public String toString() {
        return Utils.stringer("KeyInputRespChain", list.size());
    }

}