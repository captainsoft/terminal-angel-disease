/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.utils;

/**
 * Utility methods for exceptions.
 *
 * @author mathias fringes
 */
public final class ExcUtils {

    // constructors

    private ExcUtils() {
        super();
    }

    // public

    public static void argNotNull(String name, Object argument) {
        if (argument == null) {
            throw new IllegalArgumentException("The argument " + name + " must not be null!");
        }
    }

    public static void argZeroPositive(String name, int argument) {
        if (argument < 0) {
            throw new IllegalArgumentException("The argument " + name + " must be greater or equal to zero!");
        }
    }

    /**
     * The argument must be positive, zero is not allowed!
     *
     * @param name
     * @param argument
     */
    public static void argPlusPositive(String name, int argument) {
        if (argument < 1) {
            throw new IllegalArgumentException("The argument " + name + " must be greater than  zero!");
        }
    }

    /**
     * Checks for an argument to be in a specified range. Min and max values are inclusive, i.e.
     * [1-4] will throw an exception for below 0 and 5 and more.
     *
     * @param name
     * @param argument
     * @param min
     * @param max
     */
    public static void argIn(String name, int argument, int min, int max) {
        if (argument < min || argument > max) {
            throw new IllegalArgumentException("The argument " + name + " must be inside [" + min + "-" + max + "]!");
        }
    }

    public static void argNotEmpty(String argument) {
        if (argument == null || argument.trim().length() == 0) {
            throw new IllegalArgumentException("The argument " + argument + " is somehow empty!");
        }
    }

}
