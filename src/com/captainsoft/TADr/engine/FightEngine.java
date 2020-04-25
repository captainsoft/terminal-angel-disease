/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine;

import com.captainsoft.TADr.engine.debug.Debugger;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.PositionBox;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.TileDanger;
import com.captainsoft.spark.utils.Utils;

/**
 * Fight engine.
 *
 * @author mathias fringes
 */
final class FightEngine {

    // public

    /**
     * Checks if the party should do a random fight.
     * Returns null if it shouldn't.
     *
     * @param levelMap
     * @param position
     * @return
     */
    public Position shouldFight(LevelMap levelMap, Position position) {
        if (Debugger.Inst.noMonsters) {
            return null;
        }

        if (levelMap.tile(position).danger() == TileDanger.High) {
            return position;
        }

        Position fightPosition = null;
        boolean doFight = false;

        //
        Position topLeft = position.add(-1, -1);
        for (Position p : new PositionBox(topLeft, 3, 3)) {

            TileDanger danger = levelMap.tile(p).danger();
            switch (danger) {
                case High:
                    doFight = (Utils.random(3) > 1);
                    break;
                case Low:
                    doFight = false; // (Utils.random(150) == 1);
                    break;
                default:
                    break;
            }

            //
            if (doFight) {
                fightPosition = p;
                break;
            }
        }

        return fightPosition;
    }

}