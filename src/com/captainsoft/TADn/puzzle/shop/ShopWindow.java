/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.puzzle.shop;

import java.awt.Color;
import java.awt.Font;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;

/**
 * Window for a shop.
 *
 * @author mathias fringes
 */
public final class ShopWindow extends UiBoxContainer {
	
	// fields
	
	private static final Font fnt_n = new Font(Fonts.Times, Font.BOLD, 14);
	private static final Font fnt_k = new Font(Fonts.Times, Font.PLAIN, 14);
	
	public UiBox exitBox;
	public ItemBox itemBoxes[];
	public ImageBox sellButton;
	public TextBox shopManSaysBox;
	
	// constructors
	
	public ShopWindow(Shop shop) {
		super();
		initBackground(TadRepo.inst().ImageLoader().load("ifc", 33));
		//
		// info label
		shopManSaysBox = new TextBox();		
		shopManSaysBox.oneLine = true;		
		shopManSaysBox.color = new Color(224, 224, 224);
		shopManSaysBox.font = new Font(Fonts.Times, Font.BOLD, 20);
		shopManSaysBox.alignLeft();
		shopManSaysBox.set(15, 8, 380, 25);
		shopManSaysBox.stampBackground(background());		
		add(shopManSaysBox);
		//
		// item & text boxes
		itemBoxes = new ItemBox[9];		
		for(int i = 0; i < itemBoxes.length; i++) {
			Item item = shop.item(i);
			// item box
			itemBoxes[i] = new ItemBox();
			itemBoxes[i].name = "shopItemBox_" + i;
			itemBoxes[i].pos(((i % 2) == 0 ? 18 : 272), 48 + ((i % 2) == 0 ? i * 24 : (i -1) * 24));
			itemBoxes[i].item(item);
			itemBoxes[i].disableIfEmpty();
			add(itemBoxes[i]);
			//
			// text boxes
			if (item != null) {
				//
				TextBox itemNameTextBox = new TextBox();
				itemNameTextBox.color = new Color(224, 224, 224);
				itemNameTextBox.font = fnt_n;
				itemNameTextBox.oneLine = true;
				itemNameTextBox.alignLeft();
				itemNameTextBox.size(180, 18);
				itemNameTextBox.pos(((i % 2) == 0 ? 67 : 322), 46 + ((i % 2) == 0 ? i * 24 : (i -1) * 24));
				itemNameTextBox.text = item.name();
				add(itemNameTextBox);
				//
				TextBox itemInfoTextBox = new TextBox();
				itemInfoTextBox.color = new Color(192, 192, 192);
				itemInfoTextBox.font = fnt_k;
				itemInfoTextBox.oneLine = true;
				itemInfoTextBox.alignLeft();
				itemInfoTextBox.size(180, 18);
				itemInfoTextBox.pos(((i % 2) == 0 ? 67 : 322), 64 + ((i % 2) == 0 ? i * 24 : (i -1) * 24));
				itemInfoTextBox.text = item.coins() + " Chips";
				//
				if (item.isArmor()) {
					itemInfoTextBox.text += " " + item.value() + " RP - " + item.suitString();
				}
				if (item.isWeapon()) {
					itemInfoTextBox.text += " " + item.value() + " AP - " + item.suitString();
				}				
				add(itemInfoTextBox);
			}
		}
		//
		// sell button
		sellButton = new ImageBox(TadRepo.inst().ImageLoader().load("ifc", 35));
		sellButton.name = "shopSellButton"; 
		sellButton.pos(336, 264);
		add(sellButton);
		//
		// exit box
		exitBox = new UiBox("exitShopBox", 433, 264, 70, 23);
		add(exitBox);
	}
		
	// public methods
	
	public void shopManSays(String text) {
		shopManSaysBox.text = text;
	}

}
