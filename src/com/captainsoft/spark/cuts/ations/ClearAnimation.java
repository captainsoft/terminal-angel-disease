/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Clears all children on a box.
 *
 * @author mathias fringes
 */
public class ClearAnimation implements FrameAnimation {

	private final UiBoxContainer box;
	
	public ClearAnimation(UiBoxContainer box) {
		super();
		this.box = box;
	}
	
	@Override
	public FrameMx play(int step) {
		box.removeAll();
		return null;
	}
	
}