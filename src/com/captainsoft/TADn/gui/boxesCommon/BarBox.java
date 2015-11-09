/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.boxesCommon;

import java.awt.Color;

import com.captainsoft.spark.ui.box.UiDrawBox;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.utils.Utils;

/**
 * Rectangle box with a color and a min/max value. 
 * Used for displaying both party and monster's fun points, and the "puste" display in fights.
 * 
 * @author mathias fringes
 */
public class BarBox extends UiDrawBox {
	
	// fields
		
	public Color color = new Color(211, 96, 207);
	public int cur;
	public int max;	
	
	// constructors
	
	public BarBox() {
		this(1);		
	}
	
	public BarBox(int max) {		
		this(max, max);
	}
		
	public BarBox(int max, int cur) {
		super();
		this.cur = cur;
		this.max = max;
		size(100, 10);
	}
	
	// overridden methods
	
	@Override
	protected void draw(Surface s) {
		super.draw(s);						
		double c = cur / (double)max * width;
		int bwidth = Utils.truce((int)c, 1, width);	
		if (cur == 0) {
			bwidth = 0;
		}
		s.fill(color, 0, 0, bwidth, height);		
	}

}
