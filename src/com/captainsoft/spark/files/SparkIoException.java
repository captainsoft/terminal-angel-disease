/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.files;

import java.io.IOException;

/**
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class SparkIoException extends RuntimeException {

    public SparkIoException(String message, IOException e) {
        super(message, e);
    }

}