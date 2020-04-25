/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr;

/**
 * Awt thread exception handler.
 *
 * @author mathias fringes
 */
final class AwtEventDispatcherExceptionHandler {

    // static

    public static void registerToAwtThread() {
        System.setProperty("sun.awt.exception.handler", AwtEventDispatcherExceptionHandler.class.getName());
    }

    // constructors

    public AwtEventDispatcherExceptionHandler() {
        super();
    }

    // public

    public void handle(Throwable throwable) {
        try {
            TadExceptionHandler.errorMessageAndExit("A fatal error in the AWT thread occurred!", throwable);
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
