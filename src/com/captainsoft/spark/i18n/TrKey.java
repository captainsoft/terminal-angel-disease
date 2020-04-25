/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.i18n;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * A translation key (implemented via string) that can hold replacements and variables.
 *
 * @author mathias fringes
 */
public final class TrKey {

    // fields

    private static final int NoVariant = 0;

    private final String key;
    private final String[] data;

    private int variant = NoVariant;

    // constructors

    public TrKey(String key, Object... data) {
        super();
        this.key = key;
        this.data = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = data[i] == null ? "dd" : data[i].toString();
        }
    }

    // accessors

    public String[] data() {
        return data;
    }

    public String key() {
        return (variant == NoVariant) ? key : key + "." + variant;
    }

    public int variant() {
        return variant;
    }

    public TrKey variant(int variant) {
        this.variant = variant;
        return this;
    }

    // overridden

    @Override
    public String toString() {
        return stringer("TrKey", key);
    }

}