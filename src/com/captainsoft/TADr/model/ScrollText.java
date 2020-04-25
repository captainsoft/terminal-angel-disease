/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model;

/**
 * The scroll text for game info output.
 *
 * @author mathias fringes
 */
public final class ScrollText {

    // fields

    private static final int LEN = 4;

    private int[] types = new int[LEN];
    private String[] texts = new String[LEN];

    // constructors

    public ScrollText() {
        super();
        clear();
    }

    // public

    public void clear() {
        for (int i = 0; i < texts.length; i++) {
            texts[i] = "";
            types[i] = 0;
        }
    }

    public int length() {
        return texts.length;
    }

    public void scroll(int type, String text) {
        if (text.startsWith(".")) {
            type = 0;
            text = text.substring(1);
        }
        for (int i = 0; i < texts.length - 1; i++) {
            texts[i] = texts[i + 1];
            types[i] = types[i + 1];
        }
        texts[texts.length - 1] = text;
        types[types.length - 1] = type;
    }

    public String text(int index) {
        return texts[index];
    }

    public int type(int index) {
        return types[index];
    }

}