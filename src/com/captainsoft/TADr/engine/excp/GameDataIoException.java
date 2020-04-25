/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.excp;

/**
 * GameDataIoException
 *
 * @author mathias fringes
 */
public final class GameDataIoException extends RuntimeException {

    private static final long serialVersionUID = 3727562286950392868L;

    public GameDataIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameDataIoException(String message) {
        super(message);
    }

}