/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.collect;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * For-loop with index.
 *
 * @author mathias fringes
 */
public final class ForIndex<T> implements Iterable<T>, Iterator<T> {

    // fields

    private final List<T> list;

    private int index = -1;

    // constructors

    public static <T> ForIndex<T> create(T... list) {
        return new ForIndex<T>(list);
    }

    public ForIndex(List<T> list) {
        this.list = list;
    }

    public ForIndex(T... list) {
        this(Arrays.asList(list));
    }

    // public

    public int index() {
        return index;
    }

    // Iterable

    public boolean hasNext() {
        return ((index + 1) < list.size());
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No element anymore!");
        }
        return list.get(++index);
    }

    public void remove() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    // Iterator

    public Iterator<T> iterator() {
        return this;
    }

}