/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.party;

import java.awt.Color;

import com.captainsoft.TADr.model.item.Item;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * A party member.
 * 
 * @author mathias fringes
 */
public final class PartyMember {

	// fields
	
	public final FunPoints fun;
	public final SkillPoints weaponSkill;
	public final SkillPoints defenseSkill;
	public final SkillPoints specialAttackSkill;
	public final SkillPoints specialMemberSkill;

	private final int nr;
	private final Color color;
	private final String name;
	private final Inventory inventory;
	
	private int ptsFit;
	private int ptsFox;
	private int maxWgt;
	private int protect;	
	
	// constructors

	public PartyMember(int nr, String name, Color color) {
		this.nr = nr;
		this.name = name;
		this.color = color;
		//
		inventory = new Inventory();
		fun = new FunPoints();
		weaponSkill = new SkillPoints();
		defenseSkill = new SkillPoints();
		specialAttackSkill = new SkillPoints();
		specialMemberSkill = new SkillPoints();
	}

	// public

	public Item getInventoryItem(int index) {
		return inventory.item(index);
	}

	public void setInventoryItem(int index, Item itemNr) {
		inventory.item(index, itemNr);
	}

	public int maxWgt() {
		return maxWgt;
	}

	public int protect() {
		return protect;
	}

	public void calcProtection() {
		protect = 0;
		for (InventoryPlace pos : InventoryPlace.armorPositions) {
			if (inventory.item(pos) != null) {
				protect += inventory.item(pos).value();
			}
		}
	}

	public int getPtsFit() {
		return ptsFit;
	}

	public int getPtsFox() {
		return ptsFox;
	}

	public void protect(int protection) {
		this.protect = protection;
	}

	public void setPtsFit(int fitPts) {
		this.ptsFit = fitPts;
		this.maxWgt = ptsFit * 14;
	}

	public void setPtsFox(int foxPts) {
		this.ptsFox = foxPts;
	}

	public boolean isOverweight() {
		return (inventory.weight() > maxWgt);
	}

	public Inventory inventory() {
		return inventory;
	}
	
	public String name() {
		return this.name;
	}
	
	public int nr() {
		return this.nr;
	}

	public Color color() {
		return color;
	}
	
	// overridden
	
	@Override
	public String toString() {
		return stringer("PartyMember", nr, name);
	}

}
