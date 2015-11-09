/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.talk;

import java.awt.*;

import com.captainsoft.TADn.*;
import com.captainsoft.TADn.gui.boxesCommon.*;
import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.ui.*;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.*;

/**
 * Talk window
 * @author mathias fringes
 */
public final class TalkWindow extends UiBoxContainer {
	
	//
	
	private static final Font FntName = new Font(Fonts.Times, Font.BOLD, 18);
	private static final Font FntText = new Font(Fonts.Times, Font.PLAIN, 18);
	
	private int  portaitId = -1;
	private UiBox moreBullet;		
	private TextBox nameBox;
	private TextBox textBox;
	
	ImageBox portrait;
	
	//
	
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
		nameBox.color = new Color(224, 224, 224);	
		nameBox.font = FntName;
		nameBox.alignLeft();	
		nameBox.oneLine = true;
		nameBox.set(145, 12, 350, 20);
		add(nameBox);
		//
		textBox = new TextBox();
		textBox.color = new Color(192, 192, 192);
		textBox.font = FntText;
		textBox.alignLeft();		
		textBox.set(140, 36, 460, 85);		
		add(textBox);
		//
		moreBullet = new ImageBox(TadRepo.inst().ImageLoader().load("ifc", 37));
		moreBullet.pos(580, 105);
		add(moreBullet);
	}
	
	//
	
	public void showPage(TalkPage talkPage) {		
		moreBullet.visible(talkPage.showBullet != 0);
		nameBox.text = talkPage.name;
		textBox.text = talkPage.text;	
		if (this.portaitId != talkPage.portraitId) {
			portrait.imageSurface(TadRepo.inst().ImageLoader().load("npc", talkPage.portraitId));
		}
		this.portaitId = talkPage.portraitId;
	}
	
}
