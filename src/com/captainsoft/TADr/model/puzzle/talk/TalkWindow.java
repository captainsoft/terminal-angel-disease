/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.talk;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Talk window.
 *
 * @author mathias fringes
 */
final class TalkWindow extends UiBoxContainer {
	
	// fields
	
	private static final Font FntName = new Font(Fonts.Times, Font.BOLD, 16);
	private static final Font FntText = new Font(Fonts.Times, Font.PLAIN, 16);
	
	private int  portaitId = -1;		
	private TextBox nameBox;
	private TextBox textBox;
	
	ImageBox portrait;
	
	// constructors
	
	public TalkWindow() {
		super();
		Surface s = TadRepo.inst().ImageLoader().load("ifc", 36);		
		initBackground(s);
		//
		portrait = new ImageBox();
		portrait.set(18, 18, 95, 95);
		add(portrait);
		//
		nameBox = new TextBox();		
		nameBox.color(new Color(237, 237, 237));
		nameBox.font = FntName;
		nameBox.alignLeft();	
		nameBox.oneLine = true;
		nameBox.set(145, 12, 350, 24);
		add(nameBox);
		//
		textBox = new TextBox();
		textBox.color(new Color(216, 216, 216));
		textBox.font = FntText;
		textBox.alignLeft();		
		textBox.set(140, 36, 460, 85);		
		add(textBox);	
	}
	
	// public
	
	public void showPage(TalkPage talkPage) {				
		nameBox.text = talkPage.name;
		textBox.text = talkPage.text;	
		if (this.portaitId != talkPage.portraitId) {
			portrait.imageSurface(TadRepo.inst().ImageLoader().load("npc", talkPage.portraitId));
		}
		portaitId = talkPage.portraitId;
	}
	
}
