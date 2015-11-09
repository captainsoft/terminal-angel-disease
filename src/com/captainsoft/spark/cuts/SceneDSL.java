/*
 * Copyright Captainsoft 2010-2012. 
 * All rights reserved.  
 */
package com.captainsoft.spark.cuts;

import java.awt.Font;
import java.util.List;

import com.captainsoft.spark.cuts.ations.ClearAnimation;
import com.captainsoft.spark.cuts.ations.FadeAnimation;
import com.captainsoft.spark.cuts.ations.FrameAnimation;
import com.captainsoft.spark.cuts.ations.NullAnimation;
import com.captainsoft.spark.cuts.ations.VisibleAnimation;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * An set of convenient DSL methods on a Scene.
 *
 * @author mathias fringes
 */
public final class SceneDSL {
	
	// static 
	
	private static final Font DefaultFont = new Font(Fonts.Times, Font.BOLD, 18);
	
	// fields

	private final Scene scene;
	private final UiBoxContainer backgroundBox;
	
	private FrameMx lastFrame;
	
	public boolean centerText = false;
	
	// constructors
	
	SceneDSL(Scene scene) {
		this.scene = scene;
		this.backgroundBox = scene.sceneBox();
		//
		lastFrame = FrameMx.Start;
	}
	
	// accessors
	
	public Scene scene() {
		return scene;
	}
	
	public float last() {
		return lastFrame.asFloat();
	}
	
	public void centerText() {
		centerText = true;
	}
	
	// public
	
	public SceneDSL at(float lastFrame) {
		this.lastFrame = new FrameMx(lastFrame);
		return this;
	}
	
	public void length(float length) {
		addAnimation(length + 1, new NullAnimation());
	}	
	
	public void pause(float length) {
		append(new NullAnimation(),	length);
	}	
		
	public void show(UiBox box, float showFrame, float hideFrame) {
		if (showFrame > 1) {
			addAnimation(showFrame, new VisibleAnimation(box, true));			
		}
		if (hideFrame > 1) {
			addAnimation(hideFrame, new VisibleAnimation(box, false));
		}
	}
		
	public void addAnimation(float frame, FrameAnimation a) {		
		addAnimation(new FrameMx(frame), a);		 
	}
		
	public void addAnimation(FrameMx frame, FrameAnimation a) {
		lastFrame = frame;
		scene.addAnimation(lastFrame, a);
	}	
	
	public void jump(float offset) {
		lastFrame = lastFrame.add(offset);
	}
	
	public void append(FrameAnimation a) {
		append(a, 0);
	}	
			
	public void append(FrameAnimation a, float offset) {		
		addAnimation(lastFrame.add(offset).asFloat(), a);
	}
	
	public void addBox(UiBox box) {
		addBox(box, 0, 0); 
	}
	
	public void addBox(UiBox box, int xoff, int yoff) {		
		backgroundBox.add(box);
		box.center();
		box.move(xoff, yoff);
	}
	
	public void clearAll() {
		append(new ClearAnimation(scene.sceneBox()));
	}
	
	public ImageBox image(Surface surface) {
		return image(surface, 0, 0);
	}
	
	public ImageBox image(Surface surface, int xoff, int yoff) {
		ImageBox b = new ImageBox(surface); 
		addBox(b, xoff, yoff);				
		return b;
	}	
		
	public ImageBox image(Surface surface, int xoff, int yoff, float showFrame, float hideFrame) {
		ImageBox b = image(surface, xoff, yoff);		
		show(b, showFrame, hideFrame);
		return b;
	}
	
	public TextBox text(String text) {
		return text(text, 0, 0);
	}

	public TextBox text(String text, int xoff, int yoff) {
		TextBox b = createTextBox();
		b.text = text;
		addBox(b, xoff, yoff);
		return b;
	}	
	
	public void textFade(TextBox t, float showFrame, float fadeFrame) {
		if (showFrame > 1) {
			addAnimation(showFrame, new VisibleAnimation(t, true));		
		}
		if (fadeFrame >= 1) {
			addAnimation(fadeFrame, new FadeAnimation(t));
		}
	}
	
	public TextBox textFade(String text, int xoff, int yoff, float showFrame, float fadeFrame) {
		TextBox t = text(text, xoff, yoff);
		textFade(t, showFrame, fadeFrame);
		return t;
	}	
	

	public void hideAt(float frame, UiBox ... boxes) {
		for(UiBox box : boxes) {
			addAnimation(frame, new VisibleAnimation(box, false));
		}		
	}
	
	// development and debugging
	
	public String getAnimationTable() {
		StringBuilder sb = new StringBuilder(); 
		List<Tick> anims = scene.getAnimationTickList();
		for(Tick t : anims) {
			sb.append(t.toString());
			sb.append("\r\n");
		}			
		return (sb.toString());	
	}
	
	// private
	
	private TextBox createTextBox() {
		TextBox t = new TextBox();
		t.size(350, 200);
		if (centerText) {
			t.alignCenter();
		} else {
			t.alignLeft();
		}		
		t.oneLine = false;
		t.font = DefaultFont;
		return t;
    }
	
}
