/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim.anims;

import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.box.BoxUtils;
import com.captainsoft.spark.ui.box.UiBox;

/**
 * 
 *
 * @author mathias fringes
 */
public final class PosAnimation extends IndexedAnimation<CPos>{

	//
		
	private UiBox box;

	//
	
	public PosAnimation(UiBox box) {
		super();
		this.box = box;
		defaultValue(BoxUtils.pos(box));
	}
	
	public PosAnimation(UiBox box, CPos position) {
		this(box);
		first(position);
	}	
	
	// 
	
	public void add(int frame, int x, int y) {
		set(frame, new CPos(x, y));
	}

	//
	
	@Override
	protected void valueChange(CPos value) {	
		BoxUtils.pos(box, value);		
	}

}