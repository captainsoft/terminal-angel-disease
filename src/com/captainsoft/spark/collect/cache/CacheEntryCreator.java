/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.spark.collect.cache;

/**
 * Creates cache objects, depending on the key.
 * <K> is the key type.
 * <V> is the cached object's type.
 *
 * @author mathias fringes
 */
public interface CacheEntryCreator<K, V> {

    public V create(K key);

}