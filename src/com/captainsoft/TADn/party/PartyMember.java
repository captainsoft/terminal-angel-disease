/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import java.awt.Color;

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
	public final SkillPoints special4;

	private final int nr;
	private final Color color;
	private final String name;
	private final Inventory inventory;
	
	private int ptsFit;
	private int ptsFox;
	private int maxWgt;
	private int protect;	
	private String specialName1;
	private String specialName2;
	private String specialName3;
	private String specialName4;

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
		special4 = new SkillPoints();
	}

	// public methods

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

	public void protect(int i) {
		protect = i;
	}

	public void setPtsFit(int i) {
		ptsFit = i;
		this.maxWgt = ptsFit * 10;
	}

	public void setPtsFox(int i) {
		ptsFox = i;
	}

	public boolean isOverweight() {
		return (inventory.weight() > maxWgt);
	}

	public Inventory inventory() {
		return inventory;
	}

	public void setSpecialNames(String specialName1, String specialName2, String specialName3, String specialName4) {
		this.specialName1 = specialName1; 
		this.specialName2 = specialName2;
		this.specialName3 = specialName3;
		this.specialName4 = specialName4;
	}
	
	public String name() {
		return this.name;
	}

	public String getSpecialName1() {
		return this.specialName1;
	}

	public String getSpecialName2() {
		return this.specialName2;
	}

	public String getSpecialName3() {
		return this.specialName3;
	}

	public String getSpecialName4() {
		return this.specialName4;
	}

	public int nr() {
		return this.nr;
	}

	public Color color() {
		return this.color;
	}
	
	//
	
	public String toString() {
		return (nr + " " + name);
	}

}
