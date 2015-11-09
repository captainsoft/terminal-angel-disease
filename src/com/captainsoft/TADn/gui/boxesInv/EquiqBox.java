/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui.boxesInv;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.TADn.party.Inventory;
import com.captainsoft.TADn.party.InventoryPlace;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * 
 *
 * @author mathias fringes
 */
public final class EquiqBox extends UiBoxContainer {

	//
	
	public ArrayList<ItemBox> allItemBoxes = new ArrayList<ItemBox>();
	public TextBox protectBox;

	private ItemBox[] beltItemBoxes = new ItemBox[4];
	private ItemBox[] equiqItemBoxes = new ItemBox[5];	

	//
	
	public EquiqBox() {
		super("equiq-box");
		Surface background = TadRepo.inst().ImageLoader().load("ifc", 31);  
		initBackground(background);		
		pos(16, 48);
		//
		// protect label
		protectBox = new TextBox();
		protectBox.color = new Color(192, 192, 192);
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
	}
	
	//
	
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
