/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.boxesMain;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADn.model.ScrollText;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * 
 *
 * @author mathias fringes
 */
public final class ScrollBox extends UiBoxContainer {
	
	// fields
	
	private final ScrollText scrollText;	
	
	private static Color[] typeColors = new Color[5];
	
	static {
		typeColors[0] = new Color(35, 35, 35);
		typeColors[1] = new Color(0, 0, 250);
		typeColors[2] = new Color(0, 128, 0);
		typeColors[3] = new Color(192, 64, 0);
		typeColors[4] = new Color(192, 0, 176);
	}	                                  	                                
	
	// constructors
	
	public ScrollBox(Surface background, ScrollText scrollText) {
		super("scrollbox");
		this.scrollText = scrollText;		
		initBackground(background);	
		surface().turnAliasOn();
		surface().font(new Font(Fonts.Times, Font.BOLD, 20));	
	}

	// overridden methods
	
	@Override
	public void draw(Surface s) {		
		super.draw(s);
		for (int i = 0; i < scrollText.length(); i++) {
			s.color(typeColors[scrollText.type(i)]);
			s.text(scrollText.text(i), 7, i * 22 + 18);
		}		
	}

}