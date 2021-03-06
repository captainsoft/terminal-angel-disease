/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight;

import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.party.InventoryPlace;
import com.captainsoft.TADr.model.party.PartyMember;

/**
 * A fighting party member.
 *
 * @author mathias fringes
 */
public final class PartyFighter {

    // fields

    public final int index;
    public final Item weapon;
    public final PartyMember member;

    public Mode mode = Mode.Ready;
    public Monster targetMonster;

    private int curFitPoints;
    private int restCounter;
    private int hitCounter;

    // constructors

    PartyFighter(int index, PartyMember member) {
        super();
        this.index = index;
        this.member = member;
        curFitPoints = member.getPtsFit();
        weapon = member.inventory().item(InventoryPlace.Weapon);
    }

    // accessors

    public int hitCount() {
        return hitCounter;
    }

    public int curFitPoints() {
        return curFitPoints;
    }

    public void rest(int time) {
        restCounter = time;
    }

    public int restCount() {
        return restCounter;
    }

    public boolean isShortAfterHit() {
        return (hitCounter == -16);
    }

    // public

    public String name() {
        return member.name();
    }

    public void hit() {
        hitCounter = -20;
    }

    public void shaolinize() {
        curFitPoints += 10;
    }

    public boolean tick() {
        boolean change = false;
        if (restCounter < 0) {
            restCounter++;
            change = true;
        }
        if (hitCounter < 0) {
            hitCounter++;
            change = true;
        }
        return change;
    }

    //
    // nested

    public static enum Mode {
        Defense,
        Ready
    }

}