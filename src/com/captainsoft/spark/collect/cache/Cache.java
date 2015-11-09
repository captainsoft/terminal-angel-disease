/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.collect.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.captainsoft.spark.command.ParamCommand;

/**
 * Cache with fixed size. Hopefully thread safe. Keys can be any class of
 * Comparable, even simple numbers and strings.
 *
 * @author mathias fringes
 */
public final class Cache<K extends Comparable<K>, V> {
	
	// fields
	
	public String name = "cache";
	
	private final CacheEntryCreator<K, V> creator;
	private final int maxSize;
	private final List<K> keys = new ArrayList<K>();
	private final SortedMap<K, V> cache = new TreeMap<K, V>();

	private ParamCommand<V> onRemoveCommand;
	
	// constructors
	
	public Cache(CacheEntryCreator<K, V> creator) {
		this(creator, 10);		
	}
	
	public Cache(CacheEntryCreator<K, V> creator, int maxSize) {
		super();
		this.creator = creator;
		this.maxSize = maxSize;
	}
	
	// public
	
	public synchronized boolean contains(K key) {
		return cache.containsKey(key);
	}
		
	public synchronized V get(K key) {
		if (contains(key)) {			
			popKey(key);					
			return cache.get(key);	
		} else {	
			V value = creator.create(key);
			addToCache(key, value);
			return value;
		}
	}
		
	public synchronized int size() {
		return cache.size();
	}
	
	public void onRemove(ParamCommand<V> onRemoveCommand) {
		this.onRemoveCommand = onRemoveCommand;
	}
	
	// private	
	
	private void addToCache(K key, V value) {
		if (size() == maxSize) {
			remove(keys.get(0));
		}
		cache.put(key, value);
		keys.add(key);
	}
	
	private void popKey(K key) {
		keys.remove(key);
		keys.add(key);	
	}	
	
	private void remove(K key) {		
		V removed = cache.remove(key);
		keys.remove(key);
		//
		if (onRemoveCommand != null) {
			onRemoveCommand.execute(removed);
		}
	}
	
}
