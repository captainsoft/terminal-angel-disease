/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting.animations;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.model.PositionFrame;

/**
 * The list of current animations.
 *
 * @author mathias fringes
 */
public final class AnimationList {
	
	// fields
	
	private final int width;
	private final int height;
	private final Map<Position, Animation> animations;		
	private final Set<Animation> visibleAnimations = new HashSet<Animation>();
	
	// constructors
	
	public AnimationList(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		animations = new HashMap<Position, Animation>();
	}
	
	// public
	
	public void clear() {
		animations.clear();
	}
	
	public void set(Position p, Animation m) {
		animations.put(p, m);		
	}
	
	public void setPosition(Position p) {
		calculateVisibleAnimations(p);
	}
	
	public void play() {		
		for(Animation m : visibleAnimations) {
			m.play();
		}
	}
	
	// private
	
	private void calculateVisibleAnimations(Position p) {		
		//
		Enumeration<Position> lme = new PositionFrame(p, width, height);
		while (lme.hasMoreElements()) {
			Position pos = lme.nextElement();			
			Animation ma = animations.get(pos);
			if (ma != null) {
				visibleAnimations.add(ma);				
			}
		}
	}

}
