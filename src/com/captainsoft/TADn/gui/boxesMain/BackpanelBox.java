/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.boxesMain;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 *
 * @author mathias fringes
 */
public final class BackpanelBox extends UiBoxContainer {
	
	public TextBox moneyBox;
	public UiBox diskBox;

	public BackpanelBox(Surface background) {
		super();
		name = "backpanel-box";
		initBackground(background);
		//
		// disk box
		diskBox = new UiBox(903, 581, 27, 28);
		diskBox.name = "disk-box";		
		add(diskBox);	
		//
		// money box
		moneyBox = new TextBox();
		moneyBox.color = new Color(192, 192, 0);
		moneyBox.font = new Font(Fonts.Times, Font.BOLD, 20);
		moneyBox.name = "money-box";
		moneyBox.oneLine = true;
		moneyBox.alignLeft();
		moneyBox.set(755, 580, 130, 25);
		moneyBox.stampBackground(background());
		add(moneyBox);
	}
		
}