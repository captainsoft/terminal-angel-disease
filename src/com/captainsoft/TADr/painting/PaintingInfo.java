/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.painting;

import com.captainsoft.TADr.model.Direction;

/**
 * Basic painting info for party members.
 *
 * @author mathias fringes
 */
public final class PaintingInfo {

    // fields

    private Direction faceDirection = Direction.East;
    private int partyMemberPic = 2;

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

    // public

    public void turn() {
        setFaceDirection(faceDirection.getOpposite());
    }

}