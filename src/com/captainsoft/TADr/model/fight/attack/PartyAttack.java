/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight.attack;

/**
 * An executed attack by one party member to the monster(s).
 *
 * @author mathias fringes
 */
public interface PartyAttack {

    /**
     * Will execute the attack, and return the results.
     *
     * @return an Attacks instance,
     */
    public Attack attack();

}
