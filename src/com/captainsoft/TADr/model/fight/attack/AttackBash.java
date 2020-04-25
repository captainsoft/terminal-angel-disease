/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight.attack;

import com.captainsoft.TADr.model.fight.*;

/**
 * An party attack result bash for one single monster.
 *
 * @author mathias fringes
 */
public final class AttackBash {

    // fields

    public boolean critical;
    public boolean hit;
    public int image;
    public int points;
    public Monster monster;

    // constructors

    public AttackBash() {
        super();
    }

    public AttackBash(Monster monster) {
        super();
        this.monster = monster;
    }

}