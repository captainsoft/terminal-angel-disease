/*

 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.boxesInv;

import java.awt.Color;

import com.captainsoft.spark.ui.box.UiDrawBox;
import com.captainsoft.spark.ui.drawing.Surface;

/** 
 * 
 * @author mathias fringes
 */
public final class QuickMemberBox extends UiDrawBox {
	
	// fields
	
	private static final Color colSelected = new Color(255, 192, 192);
	
	public boolean selected = false;
	
	// constructors
	
	public QuickMemberBox() {
		super();
		size(26, 29);
	}
	
	// overridden methods
	
	@Override	
	protected void draw(Surface s) {	
		super.draw(s);		
		if (selected) {			
			s.rect(colSelected, 1, 1, 26-2, 29-2);
		}
	}

}
