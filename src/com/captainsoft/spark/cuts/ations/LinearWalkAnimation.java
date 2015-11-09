/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * Animation for linear paths. 
 *
 * @author mathias fringes
 */
public final class LinearWalkAnimation extends AbstractBoxAnimation {

	// fields
	
	private final int xstep; 
	private final int ystep; 
	private final int len;

	public boolean hideWhenOver = false;
	
	// constructors
	
	public LinearWalkAnimation(UiBox box, float gap, int xstep, int ystep, int len) {
		this(box, new FrameMx(gap), xstep, ystep, len);
	}
	
	public LinearWalkAnimation(UiBox box, FrameMx defaultGap, int xstep, int ystep, int len) {
		super(box, defaultGap);	
		this.xstep = xstep; 
		this.ystep = ystep; 
		this.len = len;
	}

	// AbstractBoxAnimation
	
	@Override
	public FrameMx play(int step) {
		if (step <= len) {
			box.x += xstep;
			box.y += ystep;
			return defaultGap;
		} else {
			if (hideWhenOver) {
				hide();
			}
			return null;
		}
	}
	
}