/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.painting.animations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.PositionBox;
import com.captainsoft.spark.render.Animation;

/**
 * The list of all, and current (visible) animations for the level map.
 *
 * @author mathias fringes
 */
public final class MapAnimationList {
	
	// fields
	
	private final int width;	
	private final int height;	
	private final Map<Position, Animation> mapAnimations;		
	private final Set<Animation> visibleAnimations = new HashSet<Animation>();
	
	// constructors
	
	public MapAnimationList(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		mapAnimations = new HashMap<Position, Animation>();
	}
	
	// public
	
	public void clear() {
		mapAnimations.clear();
	}
	
	public void add(Position p, Animation m) {
		mapAnimations.put(p, m);		
	}
	
	public void setPosition(Position p) {
		calculateVisibleAnimations(p);
	}
		
	public Set<Animation> visible() {
		return visibleAnimations;
	}

	public void removeSmoke(Position position) {		
		for (int y = 0; y < 3; y++) {					
			mapAnimations.remove(position.addY(-y));			
		}		
	}
	
	public Animation get(Position position) {
		return mapAnimations.get(position);
	}
	
	// private
	
	private void calculateVisibleAnimations(Position p) {		
		//
		visibleAnimations.clear();		
		for (Position pos : new PositionBox(p, width, height)) {				
			Animation ma = mapAnimations.get(pos);
			if (ma != null) {
				visibleAnimations.add(ma);				
			}
		}
	}
	
}