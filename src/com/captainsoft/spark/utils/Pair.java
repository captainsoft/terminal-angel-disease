/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.utils;

/**
 * A classic object pair.
 *
 * @author mathias fringes
 */
public final class Pair<A, B> {

    // fields
	
	private final A a;
	private final B b;

    // constructors
	
    public Pair(A a, B b) {
    	super();    	
    	this.a = a;
    	this.b = b;
	}

    // accessors
    
    public A a() {
		return a;
	}
    
    public B b() {
		return b;
	}

}