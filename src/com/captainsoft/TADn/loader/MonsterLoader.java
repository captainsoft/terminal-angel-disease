/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader;

import com.captainsoft.TADn.fight.Monster;

/**
 * 
 *
 * @author mathias fringes
 */
public interface MonsterLoader {

	public Integer[] loadMonsterParty(int map, int id);

	public Monster loadMonster(int id);

}
