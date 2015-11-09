/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.model.Direction;

/**
 * @author mathias fringes
 */
public final class GoCommand extends GameEngineCommand {

    private final Direction direction;

    public GoCommand(Direction direction) {
        super("GoCommand " + direction);
        this.direction = direction;
    }

    public void execute() {
        gameEngine.goPartyGo(direction);
    }

}
