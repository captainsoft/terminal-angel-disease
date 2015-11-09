/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim.anims;

import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.box.UiBox;

/** 
 *
 * @author mathias fringes
 */
public final class VisibleAnimation extends IndexedAnimation<Boolean> {
	
	//
	
	private UiBox box;
	
	//
	
	public VisibleAnimation(UiBox box) {
		super();
		this.box = box;
		defaultValue(box.visible());
	}
	
	public VisibleAnimation(UiBox box, boolean value) {
		this(box);		
		first(value);
	}
	
	//
	
	public void hideAt(int frame) {
		set(frame, false);
	}
	
	public void showAt(int frame) {
		set(frame, true);
	}
	
	//
	
	@Override
	protected void valueChange(Boolean value) {
		box.visible(value.booleanValue());		
	}
	
}
