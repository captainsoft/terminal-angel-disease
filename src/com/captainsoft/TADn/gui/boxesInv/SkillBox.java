/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.boxesInv;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 *
 * @author mathias fringes
 */
public final class SkillBox extends UiBoxContainer {
	
	// fields
	
	private static final Font font = new Font(Fonts.Times, Font.BOLD, 14);
	private static final Color col = new Color(192, 192, 192);
	
	private TextBox[] captionBoxes;
	private TextBox[] valueBoxes;

	// constructors
	
	public SkillBox() {
		super("skill-box");
		final int len = 7;
		captionBoxes = new TextBox[len];
		valueBoxes = new TextBox[len];
		//
		for (int i = 0; i < len; i++) {
			//
			int y = i * 24 + 6;
			int hg = 20;
			//
			captionBoxes[i] = createDefaultTextBox();
			captionBoxes[i].alignLeft();
			captionBoxes[i].set(8, y, 120, hg);
			add(captionBoxes[i]);
			//
			valueBoxes[i] = createDefaultTextBox();	
			valueBoxes[i].alignRight();
			valueBoxes[i].set(135, y, 40, hg);
			add(valueBoxes[i]);
		}
	}

	public void updateValues(PartyMember member) {		
		captionBoxes[0].text = "FunPoints";
		captionBoxes[1].text = "FitPoints";
		captionBoxes[2].text = "FoxPoints";
		captionBoxes[3].text = member.getSpecialName1();
		captionBoxes[4].text = member.getSpecialName2();
		captionBoxes[5].text = member.getSpecialName3();
		captionBoxes[6].text = member.getSpecialName4();
		//
		valueBoxes[0].text = member.fun.cur() + " / " + member.fun.max();
		valueBoxes[1].text = member.getPtsFit()+ "";
		valueBoxes[2].text = member.getPtsFox()+ "";
		valueBoxes[3].text = member.weaponSkill.value() + "";
		valueBoxes[4].text = member.defenseSkill.value() + "";
		valueBoxes[5].text = member.specialAttackSkill.value() + "";
		valueBoxes[6].text = member.special4.value() + "";
	}

	// private 
	
	private TextBox createDefaultTextBox() {
		TextBox textBox = new TextBox();		
		textBox.oneLine = true;
		textBox.font = font;
		textBox.color = col;
		return textBox;
	}

}
