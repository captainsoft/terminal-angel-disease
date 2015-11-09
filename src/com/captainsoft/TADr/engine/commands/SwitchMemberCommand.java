/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.commands;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.spark.command.AbstractCommand;

import static com.captainsoft.spark.utils.ExcUtils.*;
import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * Switches to another party member.
 *
 * @author mathias fringes
 */
public final class SwitchMemberCommand extends AbstractCommand{

    // fields

	private final int partyMemberNo;

    // constructors

	public SwitchMemberCommand(int partyMemberNo) {
        super("swithc member to " + partyMemberNo);
		argIn("partyMemberNo", partyMemberNo, 1, 4);
		this.partyMemberNo = partyMemberNo;		
	}

    // Command

	public void execute() {
        GameEngine.instance().mainViewer().switchMember(partyMemberNo);
	}

    // overridden
	
	@Override
	public String toString() {	
		return stringer("SwitchMemberCommand", partyMemberNo);
	}

}