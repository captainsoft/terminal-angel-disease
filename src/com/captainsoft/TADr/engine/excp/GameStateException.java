/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.excp;

/**
 * GameStateException
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class GameStateException extends RuntimeException {

    public GameStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameStateException(String message) {
        super(message);
    }

}
