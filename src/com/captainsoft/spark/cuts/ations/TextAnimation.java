/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts.ations;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.box.TextBox;

/**
 * A text change animation for TextBox.
 *
 * @author mathias fringes
 */
public final class TextAnimation implements FrameAnimation {

    // fields

	private final String text;
	private final TextBox textBox;	

    // constructors

	public static TextAnimation change(TextBox textBox, String text) {
		return new TextAnimation(textBox, text);
	}

    // public

	public TextAnimation(TextBox textBox, String text) {
		super();
		this.textBox = textBox; 
		this.text = text;		
	}

    // FrameAnimation

	@Override
	public FrameMx play(int step) {
		this.textBox.text = text;
		return null;
	}

}