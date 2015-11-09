/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.painting.animations;

import com.captainsoft.TADr.model.Direction;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.painting.GameLevelMapDrawer;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.drawing.Surface;

import static com.captainsoft.TADr.model.Direction.*;
import static com.captainsoft.TADr.painting.GameLevelMapDrawer.*;

/**
 * Scroll the map view one tile in any direction.
 * 
 * @author mathias fringes
 */
final class MapScroller {
	
	// fields

    private final GameLevelMapDrawer mapDrawer;
    private final Surface xSurface;
    private final Surface ySurface;

	// constructors
	
	public MapScroller(GameLevelMapDrawer mapDrawer) {
		this.mapDrawer = mapDrawer;
		xSurface = new Surface(X_SPAN * SQUARE, SQUARE);
		ySurface = new Surface(SQUARE, Y_SPAN * SQUARE);
	}			
	
	// public
	
	/**
	 * Move the map in one direction, but do not do the scrolling!
	 * 
	 * @param dir
	 */
	public void moveMapViewDontScroll(Direction dir) {
				
		Position newPosition = mapDrawer.tileView.topLeft.add(X_SH, Y_SH);
		
		int turn = (dir == East || dir == South) ? 1 : -1;
		
		if (dir.isHorizontal()) {
			fillXSurface(newPosition, turn);
			int yv = (dir == North) ? 0 : SQUARE * (Y_SPAN-1);
			backSurface().blit(surface(), 0, 40 * -turn);
			backSurface().blit(xSurface, 0, yv);
		}
		else if (dir.isVertical()) {
			fillYSurface(newPosition, turn);
			int xv = (dir == East) ? SQUARE * (X_SPAN-1) : 0;				
			backSurface().blit(surface(), -40 * turn, 0);
			backSurface().blit(ySurface, xv * turn, 0);		
		}
		mapDrawer.flip();
	}
	
	/**
	 * Scroll the map to the nextDirection. Step is in range 0-3 (4 steps).
	 * 
	 * @param dir
	 * @param step
	 */
	public void moveMapView(Direction dir, int step) {
	
		Position newPosition = mapDrawer.tileView.topLeft.add(X_SH, Y_SH);
		
		int step_w = step * 10;
				
		switch (dir) {
		
			case East:	
			case West:
				
				int turn = (dir == East) ? 1 : -1;
				
				if (step == 0) {
					fillYSurface(newPosition, turn);
				}
				
				int xv = (dir == East) ? SQUARE * (X_SPAN-1) : 0;				
				backSurface().blit(surface(), -10 * turn, 0);
				backSurface().blit(ySurface, xv + (30 - step_w) * turn, 0);
				
				mapDrawer.mapOffset = new CPos((30 - step_w)  * turn, 0);
				break;
		
			case North:
			case South:
				
				turn = (dir == South) ? 1 : -1;
				
				if (step == 0) {
					fillXSurface(newPosition, turn);
				}
				
				int yv = (dir == North) ? 0 : SQUARE * (Y_SPAN-1);
				backSurface().blit(surface(), 0, 10 * -turn);
				backSurface().blit(xSurface, 0, yv + (step_w - 30) * -turn);
				
				mapDrawer.mapOffset = new CPos(0, (30 - step_w) * turn);
				break;
						
			default:
				break;
		}
		
		if (step == 3) {			
			mapDrawer.mapOffset = new CPos(0, 0);
		}
				
		mapDrawer.flip();
	}
	
	// private
	
	private Surface forPos(Position p) {
        return mapDrawer.tilePainter.createTileImage(p);
	}
	
	private Surface backSurface() {
		return mapDrawer.backSurface();
	}
	
	private void fillXSurface(Position newPosition, int turn) {
		for (int x = 0; x < X_SPAN; x++) {					
			Position pos = newPosition.add(x-X_SH, Y_SH * turn);
			Surface image = forPos(pos);
			xSurface.blit(image, x * SQUARE, 0);
		}
	}
	
	private void fillYSurface(Position newPosition, int turn) {
		for (int y = 0; y < Y_SPAN; y++) {				
			Position pos = newPosition.add(X_SH * turn, y - Y_SH);	
			Surface image = forPos(pos);
			ySurface.blit(image, 0, y * SQUARE);
		}
	}
		
	private Surface surface() {
		return mapDrawer.surface();
	}

}