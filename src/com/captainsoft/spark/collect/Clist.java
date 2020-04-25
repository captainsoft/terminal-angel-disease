/*
 * Copyright Captainsoft 2011.
 * All rights reserved.
 */
package com.captainsoft.spark.collect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Extends the default java list and adapt to my own needs.
 *
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class Clist<T> extends ArrayList<T> {

    // constructors

    public Clist(T... data) {
        super(data.length);
        Collections.addAll(this, data);
    }

    public Clist(Collection<T>... l) {
        for (Collection<T> t : l) {
            addAll(t);
        }
    }

    // public

    public void add(T... data) {
        for (T t : data) {
            add(t);
        }
    }

}