/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.render;

/**
 * An Animation that contains nothing.
 *
 * @author mathias fringes
 */
public final class NullAnimation extends Animation {

    // Animation

	@Override
	public int play() {
		return -1;
	}

}