/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.painting;

import com.captainsoft.TADn.model.Direction;

/**
 * 
 *
 * @author mathias fringes
 */
public final class PaintingInfo {
	
	// fields
	
	private Direction faceDirection = Direction.East;
	private int partyMemberPic = 1;
	
	// constructors
	
	public PaintingInfo() {
		super();
	}
	
	// accessors
	
	public Direction getFaceDirection() {
		return this.faceDirection;
	}	
	
	public void setFaceDirection(Direction faceDirection) {
		if (faceDirection == Direction.East || faceDirection == Direction.West) {
			this.faceDirection = faceDirection;
		}
	}
	
	public int getPartyMemberPic() {
		return partyMemberPic;
	}
	
	public void setPartyMemberPic(int partyMemberPic) {
		this.partyMemberPic = partyMemberPic;
	}

}