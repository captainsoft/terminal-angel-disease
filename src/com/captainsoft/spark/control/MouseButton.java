/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.control;

/**
 * MouseButtons.
 *
 * @author mathias fringes
 */
public enum MouseButton {

    // enums

	Unknown(0),
	Left(1),
	Right(3);

    // fields

	private final int id;

    // constructors

	MouseButton(int id) {
		this.id = id;
	}

    // public

	public boolean match(int id) {
		return (id == this.id);
	}

    // static
	
	public static MouseButton get(int id) {
		switch(id) {
			case 1:
				return Left;
			case 3:
				return Right;
			default:
				return Unknown; 
		}
	}

}
