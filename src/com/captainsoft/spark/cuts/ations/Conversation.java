/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts.ations;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.cuts.FrameMx;
import com.captainsoft.spark.ui.box.TextBox;

/**
 * A conversation animation between two actors. Uses TextBoxes to display speech.
 *
 * @author mathias fringes
 */
public class Conversation implements FrameAnimation {
	
	// static
	
	public static Conversation talk(TextBox textBox, float defaultWait, String ... texts) {
		Conversation c = new Conversation(textBox, null, defaultWait);
		for (String text : texts) {
			c.say(textBox, text);
		}
		return c;
	}
	
	// fields
	
	private TextBox one;
	private TextBox two;
	private float defaultGap = 1;
	private float waitGap = 0.2f;
	private List<Statement> script = new ArrayList<Statement>();
	
	// constructors
	
	public Conversation(TextBox tx, float defaultGap) {
		this(tx, null, defaultGap);
	}
	
	public Conversation(TextBox one, TextBox two, float defaultGap) {
		super();
		this.one = one;
		this.two = two;
		this.defaultGap = defaultGap;
	}
		
	// public 
	
	public void sayOne(String text) {
		say(one, text, defaultGap);
	}
	
	public void sayTwo(String text) {
		say(two, text, defaultGap);
	}
	
	public void sayNothing(float wait) {
		if (two == null) {
			say(one, "", wait);			
		} else {
			say(one, "", 0);
			say(two, "", wait);
		}
	}
	
	public void say(TextBox box, String ... texts) {
		for (String text : texts) {
			// !! all of this is not called if use sayOne, sayTwo!!
			float gap = text.length() < 70 ?  defaultGap : defaultGap * 2; 		
			say(box, text, gap);
			if (waitGap > 0) {
				sayNothing(waitGap);
			}
		}
	}
	
	public void conver(String ... texts) {
		for (int i = 0; i < texts.length; i++) {
			say(i % 2 == 0 ? one : two, texts[i]);
		}
	}

	public void say(TextBox box, String text, float gap) {
		Statement cs = new Statement();
		cs.gap = new FrameMx(gap);
		cs.text = text;
		cs.textbox = box;
		//
		script.add(cs);		
	}
	
	public void waitGap(float gap) {
		this.waitGap = gap;
	}
	
	// private 
	
	private void otherSilent(TextBox box) {
		TextBox b = (box == one) ? two : one;
		if (b != null) {
			b.text = "";
		}
	}
	
	// FrameAnimation
	
	@Override
	public FrameMx play(int step) {	
		int index = step -1;
		if (index >= script.size()) {
			return null;
		} else {
			Statement cs = script.get(index);
			otherSilent(cs.textbox);
			cs.say();
			return cs.gap;
		}	
	}
	
	//
	// nested classes
	
	private class Statement {
		TextBox textbox;
		String text;
		FrameMx gap;		
		void say() {
			textbox.text = text;
		}
	}

}
