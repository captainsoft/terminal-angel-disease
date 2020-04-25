/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.loader;

import com.captainsoft.TADr.model.fight.Monster;

/**
 * Loads monster data
 *
 * @author mathias fringes
 */
public interface MonsterLoader {

    public Integer[] loadMonsterParty(int map, int id);

    public Monster loadMonster(int id);

}
