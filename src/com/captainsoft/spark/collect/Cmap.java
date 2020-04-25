/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extend the java default maps with my own needs.
 *
 * @param <K>
 * @param <V>
 * @author mathias fringes
 */
@SuppressWarnings("serial")
public final class Cmap<K, V> extends HashMap<K, V> {

    // constructors

    public Cmap() {
        super();
    }

    public Cmap(Map<K, V> m) {
        super(m);
    }

    @SuppressWarnings("unchecked")
    public Cmap(Object... data) {
        assert (data.length % 2 == 0);
        for (int i = 0; i < data.length; i += 2) {
            put((K) data[i], (V) data[i + 1]);
        }
    }

    // public

    public void remove(K... keys) {
        for (K key : keys) {
            super.remove(key);
        }
    }

    public void retain(K... keys) {
        List<K> remove = new ArrayList<K>();
        List<K> retain = Arrays.asList(keys);
        for (K key : keySet()) {
            if (!retain.contains(key)) {
                remove.add(key);
            }
        }
        for (K key : remove) {
            super.remove(key);
        }
    }

}