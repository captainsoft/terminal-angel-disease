/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.gui.boxes.main;

import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

import java.awt.*;

/**
 * The big surrounding frame box!
 * 
 * @author mathias fringes
 */
public final class BackdropBox extends UiBoxContainer {

    // fields

	public TextBox moneyBox;
	public UiBox diskBox;
	public UiBox helpWindowBox;
	public UiBox infoWindowBox;
    public QuestChickenBox questChicken;

    //constructors

	public BackdropBox(Surface background) {
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
		moneyBox.color(new Color(192, 192, 0));
		moneyBox.font = new Font(Fonts.Times, Font.BOLD, 20);
		moneyBox.name = "money-box";
		moneyBox.oneLine = true;
		moneyBox.alignLeft();
		moneyBox.set(755, 574, 130, 25);
		moneyBox.stampBackground(background());
		add(moneyBox);
		//
		// help window (over title)
		helpWindowBox = new UiBox(15, 4, 295, 44);
		helpWindowBox.name = "helpWindowBox";		
		add(helpWindowBox);
        //
        // quest chicken
        questChicken = new QuestChickenBox(382, 9, 40, 40);
        questChicken.stampBackground(background());
        questChicken.name = "questChickenBox";
        add(questChicken);
		// 
		// captainsoft info window
		infoWindowBox = new UiBox(512, 10, 170, 36);
		infoWindowBox.name = "infoWindowBox";		
		add(infoWindowBox);
	}
		
}