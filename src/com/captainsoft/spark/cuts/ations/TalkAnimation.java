/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts.ations;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.utils.Pair;

/**
 * A talk animation with text boxes.
 *
 * @author mathias fringes
 */
public final class TalkAnimation implements FrameAnimation {

	// fields
	
	private int index = 0;
	private List<Pair<String, FrameMx>> script = new ArrayList<Pair<String, FrameMx>>();
	private TextBox textBox;
		
	// constructors
	
	public static TalkAnimation talk(TextBox textBox, float defaultWait, String ... texts) {
		TalkAnimation ta = new TalkAnimation(textBox);
		ta.add(defaultWait, texts);
		return ta;
	}
	
	public TalkAnimation(TextBox textBox) {
		super();
		this.textBox = textBox;
	}
	
	// public
	
	public void add(String text, float wait) {		
		script.add(new Pair<String, FrameMx>(text, new FrameMx(wait)));		
	}
	
	public void add(float defaultWait, String ... texts) {
		for(String text : texts) {
			add(text, defaultWait);
		}			
	}
	
	// Animation
	
	@Override
	public FrameMx play(int step) {
		if (index >= script.size()) {
			return null;
		} else {			
			Pair<String, FrameMx> cs = script.get(index);
			textBox.text = cs.a();
			index++;
			return cs.b();
		}		
	}
	
}
