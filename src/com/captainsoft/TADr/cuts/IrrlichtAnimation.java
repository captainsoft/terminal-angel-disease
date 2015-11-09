/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.cuts;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.cuts.ations.FrameAnimation;
import com.captainsoft.spark.ui.box.UiBox;

/** 
 * Irrlicht animation for the intro.
 *
 * @author mathias fringes
 */
final class IrrlichtAnimation implements FrameAnimation {

	// fields
	
	private static final FrameMx GAP = new FrameMx(0.02f);	
	
	private final int y;
	private final UiBox box;
	
	public boolean stopInMiddle = false;
	
	// constructors
	
	IrrlichtAnimation(UiBox box, int y) {
		super();
		this.box = box;
		box.visible(false);
		this.y = y;
	}
	
	// overridden

	public FrameMx play(int step) {
		if (step == 1) {
			box.visible(true);
			box.x = 60;
		}
		//
		if (stopInMiddle) {
			if (step > 136 && step <= 360) {
			} else if (step > 360) {
				box.x -= 15;
			} else {
				box.x += 3;
			}
			if (box.x < -20) {
				box.visible(false);
				return null;
			}
		} else {
			if (step > 320) {
				box.visible(false);
				return null;
			}			
			else {
				box.x += 3;
			}
		}
		box.y = (int)(y + (Math.random() * 8));
		//
		return GAP;
	}

}