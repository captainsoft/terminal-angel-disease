/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.ui.drawing;

/**
 * Slices a surface into one or a group of tiles. Positions are one-based!
 *
 * @author mathias fringes
 */
public final class SurfaceSlicer {

	// fields

	private final int gapSize;
	private final int tileSize;
	private final Surface surface;
	
	// constructors
		
	public SurfaceSlicer(Surface surface, int tileSize) {
		this(surface, tileSize, 0);
	}
	
	public SurfaceSlicer(Surface surface, int tileSize, int gapSize) {
		super();
		this.surface = surface;
		this.tileSize = tileSize;
		this.gapSize = gapSize;
	}
	
	// public

    /**
     * Gets the tile at the tile position.
     *
     * @param x the x tile position (starts with one).
     * @param y the y tile position (starts with one).
     * @return
     */
	public Surface tile(int x, int y) {
		return tile(x, y, 1, 1);
	}
	
	public Surface tile(int x, int y, int x_span, int y_span) {
		return surface.stamp(
				(x - 1) * tileSize + ((x - 1) * gapSize), 
				(y - 1) * tileSize + ((y - 1) * gapSize), 
				 x_span * tileSize + ((x_span - 1) * gapSize),
				 y_span * tileSize + ((y_span - 1) * gapSize));
	}
	
}