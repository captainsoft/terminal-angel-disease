/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.loader.vbFile;

/**
 * Utilities for the old VB file format.
 *
 * @author mathias fringes
 */
final class VbFileUtils {

    private VbFileUtils() {
        super();
    }

    public static int turnInt(int b1, int b2, int b3, int b4) {
        return (b4 << 24) | (b3 << 16) | (b2 << 8) + b1;
    }

    public static int turnShort(int b1, int b2) {
        return (b2 << 8) + b1;
    }

    public static String decodeText(StringBuilder txt) {
        StringBuilder resultString = new StringBuilder(txt.length() + 5);
        for (int i = 0; i < txt.length(); i++) {
            resultString.append((char) (txt.charAt(i) - 1));
        }
        return resultString.toString();
    }

    public static String encodeText(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            sb.append((char) ((text.charAt(i) + 1)));
        }
        return sb.toString();
    }

}
