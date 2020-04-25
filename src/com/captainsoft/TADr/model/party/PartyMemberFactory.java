/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.party;

import java.awt.Color;

import com.captainsoft.TADr.engine.TadRepo;

/**
 * Creates party members.
 *
 * @author mathias fringes
 */
public final class PartyMemberFactory {

    // fields

    public static final Color pm1 = new Color(39, 107, 255);
    public static final Color pm2 = new Color(0, 128, 0);
    public static final Color pm3 = new Color(192, 0, 64);
    public static final Color pm4 = new Color(192, 0, 192);

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
        return new PartyMember(1, TadRepo.inst().Trans().word("intro.drucing.1"), pm1);
    }

    private PartyMember createBlob() {
        return new PartyMember(2, TadRepo.inst().Trans().word("intro.drucing.2"), pm2);
    }

    private PartyMember createOzelot() {
        return new PartyMember(3, TadRepo.inst().Trans().word("intro.drucing.3"), pm3);
    }

    private PartyMember createProfessor() {
        return new PartyMember(4, TadRepo.inst().Trans().word("intro.drucing.4"), pm4);
    }

}
