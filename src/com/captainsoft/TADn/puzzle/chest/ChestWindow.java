/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.chest;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Window displaying a chest.
 *
 * @author mathias fringes
 */
public final class ChestWindow extends UiBoxContainer {
		
	// fields
	
	private static final Font fnt = new Font(Fonts.Times, Font.BOLD, 18);
	
	public final ItemBox[] itemBox = new ItemBox[6];
	
	// constructors
	
	public ChestWindow(Chest chest) {
		super();
		initBackground(TadRepo.inst().ImageLoader().load("ifc", 20));		
		//
		TextBox text = new TextBox();		
		text.color = new Color(192, 192, 192);
		text.font = fnt;
		text.text = TadRepo.inst().Trans().word("chest.found");
		text.set(15, 15, 200, 40);
		add(text);
		//	
		for(int i = 0; i < 6; i++) {
			itemBox[i] = new ItemBox();
			itemBox[i].pos((i * 65 + 31) - (i < 3 ? 0 : 195), (i < 3) ? 80 : 135);
			itemBox[i].name = "chest item box " + i;
			itemBox[i].item(chest.item(i));			
			add(itemBox[i]);
		}		
	}	
	
	// public methods
	
	public boolean allTaken() {
		for (int i = 0; i < itemBox.length; i++) {
			if (itemBox[i].item() != null) {				
				return false;
			}
		}
		return true;
	}	
		
}