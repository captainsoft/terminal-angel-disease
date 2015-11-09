/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.CPos;
import com.captainsoft.spark.ui.box.BoxUtils;
import com.captainsoft.spark.ui.box.UiBox;

/** 
 * Position the box to a new location.
 *
 * @author mathias fringes
 */
public final class PosAnimation implements FrameAnimation {

    // fields

	private final CPos pos;
	private final UiBox box;

    // constructors

	public PosAnimation(UiBox box, CPos pos) {
		super();
		this.box = box;
		this.pos = pos;
	}

    // FrameAnimation

	@Override
	public FrameMx play(int step) {
		BoxUtils.pos(box, pos);
		return null;
	}

}