/*
 * Copyright Captainsoft 2010-2012. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.cuts;

import com.captainsoft.spark.anim.FrameMx;
import com.captainsoft.spark.anim.ations.Animation;
import com.captainsoft.spark.ui.box.UiBox;

/** 
 *
 * @author mathias fringes
 */
final class IrrlichtAnimation implements Animation {

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
	
	@Override
	public FrameMx play(int step) {
		if (step == 1) {
			box.visible(true);
			box.x = 70;
		}
		//
		if (stopInMiddle) {
			if (step > 100 && step <= 120) {
			} else if (step > 120) {
				box.x -= 15;
			} else {
				box.x += 4;
			}
			if (box.x < -20) {
				box.visible(false);
				return null;
			}
		} else {
			if (step > 150) {
				box.visible(false);
				return null;
			}			
			else {
				box.x += 5;
			}
		}
		box.y = (int)(y + (Math.random() * 8));
		//
		return GAP;
	}

}