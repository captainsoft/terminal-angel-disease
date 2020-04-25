/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.controller;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.commands.SwitchMemberCommand;
import com.captainsoft.TADr.model.party.PartyMember;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * The boss. Handles all mouse wheel activity.
 *
 * @author mathias fringes
 */
public final class ChiefMouseWheelController implements MouseWheelListener {

    // constructors

    public ChiefMouseWheelController() {
        super();
    }

    // MouseWheelListener

    public void mouseWheelMoved(MouseWheelEvent e) {

        PartyMember currentMember = GameEngine.instance().mainViewer().currentMember();
        if (currentMember == null) {
            return;
        }
        int scrollToNr;

        int notches = e.getWheelRotation();
        if (notches < 0) {
            // up
            scrollToNr = prevPartyMemberNr(currentMember.nr());
        } else {
            // down
            scrollToNr = nextPartyMemberNr(currentMember.nr());

        }

        GameEngine.instance().directCommand(new SwitchMemberCommand(scrollToNr));
    }


    // private

    private int nextPartyMemberNr(int fromNumber) {
        if (fromNumber == 4) {
            return 1;
        } else {
            return ++fromNumber;
        }
    }

    private int prevPartyMemberNr(int fromNumber) {
        if (fromNumber == 1) {
            return 4;
        } else {
            return --fromNumber;
        }
    }

}