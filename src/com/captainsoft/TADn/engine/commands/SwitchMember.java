/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.commands;

import com.captainsoft.TADn.gui.MainViewer;
import com.captainsoft.spark.control.Command;

/**
 * Switches to another party members
 *
 * @author mathias fringes
 */
public final class SwitchMember implements Command {
	
	private final int partyMemberNo;
	private final MainViewer mainViewer;
	
	public SwitchMember(MainViewer mainViewer, int partyMemberNo) {
		assert (partyMemberNo >= 1 && partyMemberNo <= 4);
		this.mainViewer = mainViewer;
		this.partyMemberNo = partyMemberNo;		
	}

	@Override
	public void execute() {					
		mainViewer.switchMember(partyMemberNo);		
	}

}
