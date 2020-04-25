/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.fight.Monster.State;
import com.captainsoft.TADr.model.fight.attack.PartyAttack;
import com.captainsoft.TADr.model.fight.attack.party.MetzelAttack;
import com.captainsoft.TADr.model.fight.attack.party.PoliceArrestAttack;
import com.captainsoft.TADr.model.fight.attack.party.RempelAttack;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.utils.Utils;

/**
 * Fight party vs. monsters.
 *
 * @author mathias fringes
 */
public final class Fight {

    // fields

    private static final int Aggressive = 100;   // of 100 max.

    private boolean active = true;
    private boolean usesShaolin = false;

    public final MonsterParty monsterParty;
    public final Position position;
    public final PartyFighter[] fighters;

    public Command afterFightCommand;
    public Item rewardItem;

    // constructors

    public Fight(Party party, Position position, int... monsterIds) {
        super();
        this.position = position;
        //
        // monster
        monsterParty = new MonsterParty(monsterIds);
        rewardItem = calculateRewardItem();
        //
        // fighter		
        fighters = new PartyFighter[4];
        for (int i = 0; i < 4; i++) {
            fighters[i] = new PartyFighter(i, party.member(i + 1));
            fighters[i].targetMonster = monsterParty.rndNotDead();
        }
    }

    // public

    public void allAttackMonster(Monster monster) {
        for (PartyFighter f : fighters) {
            f.targetMonster = monster;
        }
    }

    public Monster findAttackingMonster() {
        int index = Utils.random(0, MonsterParty.MAX_PLACED);
        Monster m = monsterParty.monsterPlace[index];
        if (m != null && !m.isDead() && m.state == State.Ready) {
            if (Math.random() * Aggressive <= m.agressive) {
                return m;
            }
        }
        return null;
    }

    public PartyAttack createSpecialAttack(PartyFighter fighter) {
        PartyAttack pa = null;
        switch (fighter.member.nr()) {
            case 1:
                pa = new PoliceArrestAttack(fighter, monsterParty);
                break;
            case 2:
                pa = new RempelAttack(fighter, monsterParty);
                break;
            case 3:
                pa = new MetzelAttack(fighter, monsterParty);
                break;
            default:
                break;
        }
        return pa;
    }

    public TrKey shaolinize(PartyFighter fighter) {
        if (!usesShaolin) {
            usesShaolin = true;
            for (PartyFighter f : fighters) {
                f.shaolinize();
            }
            return new TrKey("fight.use.shaolin.ok", fighter.member.name());
        } else {
            return new TrKey("fight.use.shaolin.twice", fighter.member.name());
        }
    }

    public TrKey incrFunPoints(PartyFighter fighter, Item item) {
        int value = item.value();
        fighter.member.fun.addCur(value + (int) (Math.random() * (value / 2)));
        return new TrKey("fight.use.incrFunPoints", fighter.member.name(), item.name());
    }

    public boolean isActive() {
        return active;
    }

    public void removeAttackTarget(Monster monster) {
        if (!monsterParty.allDead()) {
            for (PartyFighter f : fighters) {
                if (f.targetMonster == monster) {
                    f.targetMonster = monsterParty.rndNotDead();
                }
            }
        }
    }

    public void executeAfterFight() {
        if (afterFightCommand != null) {
            afterFightCommand.execute();
        }
    }

    public void stop() {
        active = false;
    }

    // private

    private Item calculateRewardItem() {
        Item item = null;
        if (monsterParty.monsterItemId > 0) {
            if (Utils.random(6) == 2) {
                item = TadRepo.inst().ItemRepo().item(monsterParty.monsterItemId);
            }
        }
        return item;
    }

}