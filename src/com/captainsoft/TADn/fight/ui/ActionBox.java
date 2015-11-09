/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight.ui;

import java.util.ArrayList;
import java.util.List;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.fight.Monster;
import com.captainsoft.TADn.fight.MonsterParty;
import com.captainsoft.TADn.fight.PartyFighter;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.TADn.party.InventoryPlace;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.ImageSurface;

/**
 * The attack command box for a party fighter.
 *
 * @author mathias fringes
 */
public final class ActionBox extends UiBoxContainer {
	
	// fields
	
	public final PartyFighter fighter;
	
	public List<MonsterSelectBox> monSelectBoxes;
	public List<ItemBox> itemBoxes;
	public UiBox attackButton;
	public UiBox defenseButton;
    public UiBox specialAttackButton;
	
    // constructors
    
	ActionBox(PartyFighter fighter, MonsterParty monsterParty) {
		super();
		this.fighter = fighter;		
		int number = fighter.member.nr();
		name = "actionBox[" + number + "]";
		initBackground(new ImageSurface(TadRepo.inst().ImageLoader().load("ifc", number + 2)));
		//
		// set up buttons
		attackButton = new UiBox();
		attackButton.set(18, 10, 87, 33);
		add(attackButton);
		//
		specialAttackButton = new UiBox();
		specialAttackButton.set(18, 50, 87, 20);
		add(specialAttackButton);
		//
		defenseButton = new UiBox();
		defenseButton.set(18, 98, 87, 20);
		add(defenseButton);		
		//
		itemBoxes = new ArrayList<ItemBox>();
		for (int i = 0; i < 4; i++) {
			ItemBox ib = new ItemBox();
			ib.name = "ItemBox [" + fighter.index + "," + i + "]";
			ib.inventoryIndex = InventoryPlace.beltPositions.get(i).index(); 
			ib.item(fighter.member.inventory().item(InventoryPlace.beltPositions.get(i)));
			ib.size(20, 20);
			ib.pos(i * 23 + 17, 74);
			add(ib);
			itemBoxes.add(ib);	
		}
		//		
		monSelectBoxes = new ArrayList<MonsterSelectBox>();		
		for(int i = 0; i < MonsterParty.MAX_PLACED; i++) {
			Monster m = monsterParty.monster(i);
			if (m != null) {
				MonsterSelectBox monBox = new MonsterSelectBox(i);
				monBox.name = "monBox " + i + " (" + number + ")";
				monBox.pos(i * 21 + 11, 125);
				monSelectBoxes.add(monBox);
				add(monBox);		
			}
		}		
	}
	
	// public methods
	
	public void select(Monster m) {
		MonsterSelectBox mbc = getAt(m.fightIndex);
		select(mbc);
	}
	
	public void removeMonster(Monster m) {
		MonsterSelectBox mbc = getAt(m.fightIndex);
		mbc.visible(false);
	}
	
	// private methods
	
	private MonsterSelectBox getAt(int index) {
		for(MonsterSelectBox b : monSelectBoxes) {
			if (index == b.monsterPlaceIndex) {
				return b;
			}
		}
		return null;
	}

	private void select(MonsterSelectBox mbc) {
		for (MonsterSelectBox msb : monSelectBoxes) {
			msb.selected = false;	
		}		 	
		mbc.selected = true;
	}

}