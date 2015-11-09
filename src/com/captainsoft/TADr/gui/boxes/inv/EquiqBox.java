/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.gui.boxes.inv;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.party.Inventory;
import com.captainsoft.TADr.model.party.InventoryPlace;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Waht does the party member wear? Weapons, armor and action belt.
 *
 * @author mathias fringes
 */
public final class EquiqBox extends UiBoxContainer {

	// fields
	
	private final static Color fntColor = new Color(192, 192, 192);
	
	public ArrayList<ItemBox> allItemBoxes = new ArrayList<ItemBox>();
	public TextBox protectBox;

	private ItemBox[] beltItemBoxes = new ItemBox[4];
	private ItemBox[] equiqItemBoxes = new ItemBox[5];	

	// constructors
	
	public EquiqBox() {
		super("equiq-box");
		Surface background = TadRepo.inst().ImageLoader().load("ifc", 31);  
		initBackground(background);		
		pos(16, 48);
		//
		// protect label
		protectBox = new TextBox();
		protectBox.color(new Color(154, 176, 199));
		protectBox.oneLine = true;
		protectBox.font = new Font(Fonts.Times, Font.BOLD, 12);
		protectBox.alignLeft();
		protectBox.set(10, 153, 100, 20);
		protectBox.stampBackground(background);
		add(protectBox);	
		//
		// item boxes (equip)
		int bx = 9;
		int by = 9;
		for (int i = 0; i < 5; i++) {
			ItemBox itemBox = new ItemBox();
			itemBox.inventoryIndex = InventoryPlace.equiqPositions.get(i).index();
			itemBox.name = "ItemBox (equip) " + i;
			itemBox.pos(bx, by);
			equiqItemBoxes[i] = itemBox;
			add(itemBox);
			allItemBoxes.add(itemBox);
			by += (48 * (i == 3 ? 2 : 1));
			if (i == 2) {
				bx = 137; 
				by = 25;
			}
		}
		//
		// items boxes (belt)
		bx = 137;
		by = 73;
		for (int i = 0; i < 4; i++) {
			ItemBox itemBox = new ItemBox();
			itemBox.inventoryIndex = InventoryPlace.beltPositions.get(i).index();
			itemBox.size(20, 20);			
			itemBox.name = "ItemBox (belt) " + i;
			itemBox.pos(bx, by);			
			beltItemBoxes[i] = itemBox;
			add(itemBox);			
			allItemBoxes.add(4 + i, itemBox);
			if (i == 1) {
				by += 20;
				bx -= 20;
			} else {
				bx += 20;
			}							
		}		
		//
		// item Box labels
		Font efont = new Font(Fonts.Times, Font.BOLD, 12);
		for (int i = 0; i < 3; i++) {
			TextBox lbl = new TextBox();
			lbl.alignLeft();
			lbl.oneLine = true;
			lbl.size(70, 18);
			
			lbl.pos(53, i * 48 + 8);
			lbl.text = TadRepo.inst().Trans().word("equiq.left." + (i+1));
			lbl.font = efont;
			lbl.color(fntColor);
			add(lbl);
		}
		for (int i = 0; i < 3; i++) {
			TextBox lbl = new TextBox();
			lbl.alignRight();
			lbl.oneLine = true;
			lbl.size(70, 18);						
			lbl.pos(62, i * 48 + 35);			
			lbl.font = efont;
			
			lbl.color(fntColor);
			lbl.text = TadRepo.inst().Trans().word("equiq.right." + (i+1));
			add(lbl);
			
			
		}
		
	}
	
	// public
	
	public void member(PartyMember member) {
		Inventory inventory = member.inventory();		
		for (int i = 0; i < equiqItemBoxes.length; i++) {
			ItemBox itemBox = equiqItemBoxes[i];			
			Item item = inventory.item(InventoryPlace.equiqPositions.get(i));
			itemBox.item(item);
		}		
		for (int i = 0; i < beltItemBoxes.length; i++) {
			ItemBox itemBox = beltItemBoxes[i];
			Item item = inventory.item(InventoryPlace.beltPositions.get(i));
			itemBox.item(item);
		}
	}
	
}
