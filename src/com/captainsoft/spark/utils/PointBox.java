/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A rectangle with an enumeration of all included point objects!
 *
 * @author mathias fringes
 */
public class PointBox<T extends Point> implements Enumeration<T>, Iterator<T>, Iterable<T> {
		
	// fields
	
	private final T topLeft;
	private final int width;
	private final int height;
	
	private int x;
	private int y; 

	// constructors
	
	protected PointBox(T topLeft, int width, int height) {
		super();
		this.topLeft = topLeft;
		this.width = width;
		this.height = height;
		this.x = topLeft.x;
		this.y = topLeft.y;
	}
	
	public static PointBox<Point> create(int width, int height) {
		return new PointBox<Point>(Point.one, width, height);
	}
	
	public static PointBox<Point> create(Point topLeft, int width, int height) {
		return new PointBox<Point>(topLeft, width, height);
	}
	
	// protected
	
	@SuppressWarnings("unchecked")
	protected T createPoint(int x, int y) {
		return (T) new Point(x, y);
	}
	
	// Enumeration

	public final boolean hasMoreElements() {
		return ((x < (topLeft.x + width)) && (y < (topLeft.y + height)));
	}

	public final T nextElement() {		
		if (!hasMoreElements()) {
			throw new NoSuchElementException("No more position in enumeration!");
		}
		T p = createPoint(x, y);
		x++;
		if (x == (topLeft.x + width)) {
			y++;
			x = topLeft.x;
		}		
		return p;			
	}
	
	// Iterator

	public final boolean hasNext() {
		return hasMoreElements();
	}

	public final T next() {
		return nextElement();
	}

	public final void remove() {
		throw new UnsupportedOperationException();
	}

	// Iterable

	public final Iterator<T> iterator() {
		return this;
	}
	
}