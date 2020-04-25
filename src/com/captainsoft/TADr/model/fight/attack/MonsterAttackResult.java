/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight.attack;

import com.captainsoft.TADr.model.fight.PartyFighter;

/**
 * The attack result from a monster attack.
 *
 * @author mathias fringes
 */
public final class MonsterAttackResult {

    public boolean hit;
    public int funLost;
    public PartyFighter partyVictim;
    public String attackText;

}
