/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model;

import com.captainsoft.spark.utils.PointBox;

/**
 * A specific typed PointBox for Position.
 *
 * @author mathias fringes
 */
public class PositionBox extends PointBox<Position> {

	public PositionBox(Position left, int width, int height) {
		super(left, width, height);		
	}
	
	@Override
	protected final Position createPoint(int x, int y) {
		return new Position(x, y);
	}
	
}