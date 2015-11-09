/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.spark._sparkmuell.anim.anims;

import java.awt.*;

import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.box.ColorBox;

/** 
 *
 * @author mathias fringes
 */
public final class ColorAnimation extends IndexedAnimation<Color> {

	//
	
	private ColorBox box;
	
	//
	
	public ColorAnimation(ColorBox box) {
		super();
		this.box = box;
		defaultValue(box.color);
	}
	
	public ColorAnimation(ColorBox box, Color color) {
		this(box);
		first(color);
	}
	
	//
	
	@Override
	protected void valueChange(Color value) {		
		box.color = value;
	}

}
