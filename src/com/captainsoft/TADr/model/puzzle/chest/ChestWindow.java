/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.chest;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Window displaying a chest.
 *
 * @author mathias fringes
 */
final class ChestWindow extends UiBoxContainer {
		
	// fields
	
	private static final Font fnt = new Font(Fonts.Times, Font.BOLD, 18);
	
	public final ItemBox[] itemBox = new ItemBox[6];
	
	// constructors
	
	public ChestWindow(Chest chest) {
		super();
		initBackground(TadRepo.inst().ImageLoader().load("ifc", 20));		
		//
		TextBox text = new TextBox();		
		text.color(new Color(192, 192, 192));
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
	
	// public
	
	public boolean allTaken() {
        for (ItemBox anItemBox : itemBox) {
            if (anItemBox.item() != null) {
                return false;
            }
        }
		return true;
	}	
		
}