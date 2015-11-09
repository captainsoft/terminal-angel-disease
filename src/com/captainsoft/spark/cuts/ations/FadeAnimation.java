/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts.ations;

import java.awt.Color;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.box.TextBox;

/**
 * Text fade out animation.
 *
 * @author mathias fringes
 */
public final class FadeAnimation implements FrameAnimation {
	
	// static
	
	private final static Color[] fadeSwColors = {        
        new Color(224, 224, 224),
        new Color(192, 192, 192),
        new Color(128, 128, 128),
        new Color(64, 64, 63),
        Color.BLACK 
	};
		
	// fields
	
	private final FrameMx gap = new FrameMx(0.1f);
	private final TextBox textBox;
	
	// constructors
	
	public FadeAnimation(TextBox textBox) {
		super();
		this.textBox = textBox;
	}	
	
	// FrameAnimation

	@Override
	public FrameMx play(final int step) {
		if (step <= fadeSwColors.length) {
			textBox.color(fadeSwColors[step-1]);
			return gap;
		} else {
			return null;
		}
	}

}
