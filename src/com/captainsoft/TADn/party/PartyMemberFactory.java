/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import java.awt.Color;

/**
 * Creates party members.
 *
 * @author mathias fringes
 */
final class PartyMemberFactory {
	
	// constructors
	
	public PartyMemberFactory() {
		super();
	}
	
	// public 
	
	public PartyMember[] createAllMembers() {
		PartyMember[] members = new PartyMember[4];
		members[0] = createSummerkamp();
		members[1] = createBlob();
		members[2] = createOzelot();
		members[3] = createProfessor();
		return members;
	}
	
	// private
	
	private PartyMember createSummerkamp() {
		PartyMember pm = new PartyMember(1, "Detective Summerkamp", new Color(0, 0, 255));
		pm.setSpecialNames("Kampfen", "Verteidigung", "Polizeigriff", "Schlösser knacken");
		return pm;		
	}
		
	private PartyMember createBlob() {
		PartyMember pm = new PartyMember(2, "Kurtis der Blob", new Color(0, 128, 0));
		pm.setSpecialNames("Prügeln", "Zurückprügeln", "Umrempeln", "Zaubersprüche");
		return pm;
	}
	
	private PartyMember createOzelot() {
		PartyMember pm = new PartyMember(3, "Ritter vom Ozelot", new Color(192, 0, 64));
		pm.setSpecialNames("Duellieren", "Parieren", "Nahkampf", "Magische Formeln");
		return pm;
	}
	
	private PartyMember createProfessor() {
		PartyMember pm = new PartyMember(4, "Professor Zett", new Color(192, 0, 192));
		pm.setSpecialNames("Schubsen", "Raushaöten", "chem. Reaktion", "Schriften");
		return pm;
	}
	
}
