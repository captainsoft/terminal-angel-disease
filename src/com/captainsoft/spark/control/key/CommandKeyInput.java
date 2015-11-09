/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.control.key;

import com.captainsoft.spark.command.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Command key input.
 *
 * @author mathias fringes
 */
public final class CommandKeyInput implements KeyInput {

    // fields

    public final Map<Integer, Command> commands = new HashMap<Integer, Command>();

    // constructors

    public CommandKeyInput(Map<Integer, Command> commands) {
        super();
        this.commands.putAll(commands);
    }

    // public

    public void put(int keyCode, Command c) {
        commands.put(keyCode, c);
    }

    // KeyInput

    public boolean keyPress(int keyCode) {
        Command c = commands.get(keyCode);
        if (c != null) {
            c.execute();
            return true;
        }
        return false;
    }

}