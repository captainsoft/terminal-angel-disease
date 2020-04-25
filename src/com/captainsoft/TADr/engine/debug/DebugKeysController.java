/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.debug;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.model.fight.Fight;
import com.captainsoft.TADr.model.fight.FightWindowController;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.control.key.KeyInput;
import com.captainsoft.spark.utils.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.captainsoft.spark.utils.Truth.isEqual;


/**
 * Controller for handling debug keys.
 *
 * @author mathias fringes
 */
public final class DebugKeysController implements KeyInput {

    // fields

    private final GameEngine gameEngine;
    private final DigitKeyInputCollector mapTeleportHandler = new DigitKeyInputCollector("m", 2);
    private final DigitKeyInputCollector debuggerSwitch = new DigitKeyInputCollector("c", 1);

    // constructors

    public DebugKeysController(GameEngine gameEngine) {
        super();
        this.gameEngine = gameEngine;
    }

    // public

    public boolean keyPress(int keyCode) {

        Integer debuggerOn = debuggerSwitch.collect(keyCode);
        if (isEqual(debuggerOn, 1)) {
            Debugger.Inst.on = !Debugger.Inst.on;
            if (Debugger.Inst.on) {
                Log.enabled = true;
                Debugger.Inst.switchOn();
                Debugger.Inst.dump();
            }
            gameEngine.sayAsIs("Debugger is now: " + Debugger.Inst.on);
            return true;
        }

        if (!Debugger.Inst.on) {
            return false;
        }

        Integer mapNr = mapTeleportHandler.collect(keyCode);
        if (mapNr != null) {
            gameEngine.teleportParty(mapNr, gameEngine.party().position());
            return true;
        }

        switch (keyCode) {

            case KeyCodes.F:
                // OggPlayer.play();
                break;

            case KeyCodes.G:
                Debugger.Inst.godMode = true;
                break;

            case KeyCodes.E:
                Party party = gameEngine.party();
                PartyMember rm = party.member(party.randomMemberNr());
                rm.setPtsFit(rm.getPtsFit() + 1);
                rm = party.member(party.randomMemberNr());
                rm.setPtsFox(rm.getPtsFox() + 1);

                for (PartyMember m : party.members) {
                    m.fun.addMax(m.fun.max() / 25);
                    m.specialAttackSkill.learn(m.specialAttackSkill.value() / 10);
                    m.defenseSkill.learn(m.defenseSkill.value() / 10);
                    m.weaponSkill.learn(m.weaponSkill.value() / 10);
                    m.specialAttackSkill.learn(m.specialAttackSkill.value() / 10);
                    m.specialMemberSkill.learn(m.specialMemberSkill.value() / 10);
                    gameEngine.mainViewer().updateSkillView(m);
                }

                break;

            case KeyCodes.F6:
                gameEngine.sayAsIs("causing an exception");
                throw new RuntimeException("debug exception raised!");

            case KeyCodes.F7:
                gameEngine.sayAsIs("Starting dummy fight");
                startDummyFight();
                break;

            case KeyCodes.F8:
                gameEngine.mainViewer().backpanelWindowController.playChickenAnimation(1);
                //
                Runtime runtime = Runtime.getRuntime();
                System.out.println("Free memory: " + runtime.freeMemory());
                System.out.println("Max. memory: " + runtime.maxMemory());
                System.out.println("Total memory: " + runtime.totalMemory());
                break;

            case KeyCodes.F9:
                gameEngine.addCoins(10000);
                break;

            case KeyCodes.F10:
                gameEngine.sayAsIs("Reloading tiles");
                mapNr = gameEngine.party().mapNumber();
                gameEngine.party().mapNumber(-1);
                gameEngine.teleportParty(mapNr, gameEngine.party().position());
                break;

            case KeyCodes.F11:
                Debugger.Inst.noMonsters = !Debugger.Inst.noMonsters;
                gameEngine.sayAsIs("No Monsters is now: " + Debugger.Inst.noMonsters);
                break;

            default:
                return false;
        }

        return true;
    }

    // private

    private void startDummyFight() {
        List<Integer> ml = Arrays.asList(2, 2, 2, 2, 5);
        Collections.shuffle(ml);

        FightWindowController fw = new FightWindowController(
                gameEngine,
                new Fight(gameEngine.party(), gameEngine.party().position(),
                        ml.get(0),
                        ml.get(1),
                        ml.get(2),
                        ml.get(3),
                        ml.get(4)
                ));
        gameEngine.showWindow(fw);
        try {
            fw.beginFight();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
