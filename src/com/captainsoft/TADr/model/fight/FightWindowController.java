/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight;

import java.util.ArrayList;
import java.util.Collections;

import com.captainsoft.TADr.engine.debug.Debugger;
import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.TADr.model.i18n.TadTranslator;
import com.captainsoft.TADr.model.fight.PartyFighter.Mode;
import com.captainsoft.TADr.model.fight.attack.Attack;
import com.captainsoft.TADr.model.fight.attack.AttackBash;
import com.captainsoft.TADr.model.fight.attack.MonsterAttack;
import com.captainsoft.TADr.model.fight.attack.MonsterAttackResult;
import com.captainsoft.TADr.model.fight.attack.party.MagicItemAttack;
import com.captainsoft.TADr.model.fight.attack.party.SlimeBombAttack;
import com.captainsoft.TADr.model.fight.attack.party.WeaponAttack;
import com.captainsoft.TADr.model.fight.ui.ActionBox;
import com.captainsoft.TADr.model.fight.ui.FightWindow;
import com.captainsoft.TADr.model.fight.ui.MonsterBox;
import com.captainsoft.TADr.model.fight.ui.MonsterSelectBox;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.item.ItemType;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.TADr.sound.SndPlayer;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.render.AnimationItem;
import com.captainsoft.spark.render.PlanAnimation;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * The fight controller.
 *
 * @author mathias fringes
 */
public final class FightWindowController extends BaseWindowController {

    // fields

    private final Fight fight;
    private final GameEngine gameEngine;

    private Animation monsterAttackAnimation;
    private Animation partyCounterAnimation;
    private DirtyUpdater updater;
    private FightImages fightImages;
    private FightWindow fw;
    private SndPlayer sndPlayer;
    private TadTranslator tr;

    // constructors

    public FightWindowController(GameEngine gameEngine, Fight fight) {
        super();
        this.gameEngine = gameEngine;
        this.fight = fight;
        //
        this.updater = new DirtyUpdater(gameEngine.mainViewer());
        this.fightImages = FightImages.inst();
        this.sndPlayer = TadRepo.inst().SndPlayer();
        this.tr = TadRepo.inst().Trans();
    }

    // start/stop methods

    public void beginFight() {
        sndPlayer.playSound("sifc", 12);
        gameEngine.addAnimation(showIntroduction());
    }

    private Animation showIntroduction() {
        PlanAnimation pa = new PlanAnimation();

        final int timeFactor = 8;
        //
        // the party fights against...		
        pa.add(new AnimationItem() {
            public int play() {
                TrKey introText = new TrKey("fight.title.against", gameEngine.party().name());
                drawMonsterText(tr.word(introText));
                updater.dirtyUpdate();
                return (250 * timeFactor);
            }
        });
        pa.add(new AnimationItem() {
            public int play() {
                drawMonsterText("");
                updater.dirtyUpdate(fw.monsterBK);
                return (60 * timeFactor);
            }
        });
        //
        // monster party introduction
        final ArrayList<Monster> monsters = new ArrayList<Monster>(fight.monsterParty.allMonsters);
        Collections.shuffle(monsters);
        for (final Monster monster : monsters) {

            pa.add(new AnimationItem() {
                public int play() {
                    fw.showMonsterBox(monster.fightIndex);
                    drawMonsterText(monster.name);
                    updater.dirtyUpdate(fw.monsterBK);
                    sndPlayer.playSound("smpr", monster.speech == 0 ? 99 : monster.speech);
                    return (160 * timeFactor);
                }
            });
            pa.add(new AnimationItem() {
                public int play() {
                    drawMonsterText("");
                    updater.dirtyUpdate(fw.monsterBK);
                    return (60 * timeFactor);
                }
            });
        }
        //
        // start and lets go!
        pa.gap(80 * timeFactor);
        pa.add(new AnimationItem() {
            public int play() {
                sndPlayer.playSound("sifc", 2);
                drawMonsterText("");
                fw.showMonsterBarBoxes();
                startFightThreads();
                return -1;
            }
        });
        return pa;
    }

    private void startFightThreads() {
        fw.showActionPanels();
        fw.showGiveUpBox();
        updater.dirtyUpdate(fw);

        partyCounterAnimation = new Animation("partyCounterLoopAnimation") {
            @Override
            public int play() {
                fighterTick();
                return 100;
            }
        };
        monsterAttackAnimation = new Animation("monsterAttackLoopAnimation") {
            @Override
            public int play() {
                if (step == 0) {
                    // head start for the party.
                    step = 1;
                    return 2000;
                } else {
                    monsterTick();
                    return 312;
                }
            }
        };
        gameEngine.addAnimation(partyCounterAnimation);
        gameEngine.addAnimation(monsterAttackAnimation);
    }

    private void stopFight() {
        partyCounterAnimation.stop();
        monsterAttackAnimation.stop();
        fight.stop();
        fw.hideActionPanels();
        updater.dirtyUpdate(fw);

        // update map graphics
        gameEngine.updateFightTile(fight.position);
    }

    private void giveUpFight() {
        stopFight();
        gameEngine.reset();
        MenuController mc = new MenuController(gameEngine);
        mc.show();
    }

    // ticks

    private void fighterTick() {
        boolean partyChange = false;
        for (PartyFighter pf : fight.fighters) {
            boolean changeTick = pf.tick();
            if (changeTick) {
                int index = pf.index;
                partyChange = true;

                if (pf.hitCount() == 0) {
                    fw.hitBox[index].visible(false);
                }
                if (pf.restCount() == 0) {
                    fw.puffBox[index].visible(false);
                } else {
                    fw.barBox[index].cur--;
                }
                if ((pf.hitCount() == 0) && (pf.restCount() == 0)) {
                    if (pf.mode == Mode.Ready) {
                        fw.actionBoxes[index].visible(true);
                    }
                }
                if (pf.isShortAfterHit()) {
                    fw.hitTextBox[index].visible(true);
                }

                updater.dirty(fw.actionPanels[index]);
            }
        }
        if (partyChange) {
            updater.dirtyUpdate();
        }
    }

    private void monsterTick() {
        //	
        // attack
        Monster attackingMonster = fight.findAttackingMonster();
        if (attackingMonster != null) {
            executeMonsterAttack(attackingMonster);
        }
        //
        // status updates
        for (Monster monster : fight.monsterParty.allMonsters) {
            if (monster.tick()) {
                if (monster.ifaceTime == 0) {
                    MonsterBox mbox = fw.monsterBox[monster.fightIndex];
                    mbox.rehops();
                }
                if (monster.hitTime == 0) {
                    fw.monsterPointsBox[monster.fightIndex].visible(false);
                    fw.hitComicMonsterImage[monster.fightIndex].visible(false);
                }
                updater.dirtyUpdate(fw.monsterBK);
            }
        }
    }

    // private

    private void executeMonsterAttack(Monster monster) {
        if (!fight.isActive()) {
            return;
        }

        MonsterAttackResult monstarAttack = new MonsterAttack(monster, fight).attack();
        monster.toAttackState();
        drawMonsterAttack(monster, monstarAttack);

        // 
        if (monstarAttack.hit) {
            sndPlayer.playSound("smat", ((monster.speech > 0) ? monster.speech : 99));
            monstarAttack.partyVictim.hit();
            PartyMember member = monstarAttack.partyVictim.member;
            member.fun.addCur(-monstarAttack.funLost);
            if (Debugger.Inst.godMode) {
                member.fun.addCur(monstarAttack.funLost);
            }
            gameEngine.mainViewer().updateFunBars(member);

            if (member.fun.isSad()) {

                stopFight();
                GameOverWindowController gowc = new GameOverWindowController(gameEngine, member);
                gameEngine.showWindow(gowc);
            }
        }

    }

    private void executePartyAttack(PartyFighter fighter, Attack attack) {
        if (!fight.isActive()) {
            return;
        }

        if (attack.hasHit() && attack.sound > 0) {
            sndPlayer.playSound("sifc", attack.sound);
        }

        //
        // learn
        if (attack.hasLearned) {
            if (attack.learnText != null) {
                gameEngine.mainViewer().scroll(fighter.member.nr(), tr.word(attack.learnText));
            }
        }
        fighter.rest(-attack.restingTime());

        //
        // deduct monsters hit points and see if they're dead.
        boolean anyMonsterDead = false;
        for (AttackBash ab : attack.hitList()) {
            ab.monster.hit(ab.points, attack.monsterWaitFactor);
            if (ab.critical) {
                ab.monster.kill();
            }
            if (Debugger.Inst.godMonsters) {
                ab.monster.hitPoints = 1;
            }
            if (ab.monster.isDead()) {
                anyMonsterDead = true;
                fight.monsterParty.remove(ab.monster);
                fight.removeAttackTarget(ab.monster);

                for (ActionBox actionBox : fw.actionBoxes) {
                    actionBox.removeMonster(ab.monster);
                    actionBox.select(actionBox.fighter.targetMonster);
                }
                updater.dirty(fw.actionPanels);
            }
        }

        if (anyMonsterDead) {
            sndPlayer.playSound("sifc", 1);
        }
        drawPartyAttack(fighter, attack);

        // fight finished, all dead
        if (fight.monsterParty.allDead()) {
            stopFight();
            sndPlayer.playSound("sifc", 13);
            gameEngine.addAnimation(new Animation() {
                @Override
                public int play() {
                    step++;
                    switch (step) {
                        case 1:
                            return 2000;
                        case 2:
                        default:
                            WinWindowController wc = new WinWindowController(fight, gameEngine);
                            gameEngine.showWindow(wc);
                            return -1;
                    }
                }
            });
        }
    }

    private void weaponAttack(PartyFighter fighter) {
        Attack attack = new WeaponAttack(fighter, fighter.targetMonster).attack();
        executePartyAttack(fighter, attack);
    }

    private void specialAttack(PartyFighter fighter) {
        Attack attack = fight.createSpecialAttack(fighter).attack();
        executePartyAttack(fighter, attack);
    }

    private void selectMonster(PartyFighter fighter, Monster monster) {
        fighter.targetMonster = monster;
        ActionBox ab = fw.actionBoxes[fighter.index];
        ab.select(monster);
        updater.dirtyUpdate(ab);
    }

    private void selectAllMonster(Monster monster) {
        fight.allAttackMonster(monster);
        for (ActionBox ab : fw.actionBoxes) {
            ab.select(monster);
        }
        updater.dirtyUpdate(fw.actionPanels);
    }

    private void toDefenseState(PartyFighter fighter) {
        fighter.mode = Mode.Defense;
        int index = fighter.index;
        fw.toDefense(index);
        updater.dirtyUpdate(fw.actionPanels[index]);
    }

    private void cancelDefense(PartyFighter fighter) {
        fighter.mode = Mode.Ready;
        int index = fighter.index;
        fw.cancelDefense(index);
        updater.dirtyUpdate(fw.actionPanels[index]);
    }

    private void useItem(PartyFighter fighter, ItemBox ib) {
        Item item = ib.item();
        PartyMember member = fighter.member;
        Attack attack = null;
        TrKey pText;
        switch (item.type()) {
            case CurrentFunPoints:
                pText = fight.incrFunPoints(fighter, item);
                gameEngine.mainViewer().updateFunBars(member);
                attack = new Attack(pText, 10);
                break;
            case Shaolin:
                sndPlayer.playSound("sifc", 11);
                pText = fight.shaolinize(fighter);
                attack = new Attack(pText, 10);
                break;
            case SlimeBomb:
                attack = new SlimeBombAttack(fighter, fight.monsterParty).attack();
                break;
            default:
                break;
        }
        if (item.isFightItem()) {
            if (item.type() != ItemType.SlimeBomb && item.type() != ItemType.Shaolin) {
                attack = new MagicItemAttack(fighter, fight.monsterParty, item).attack();
            }
        }
        if (attack != null) {
            member.inventory().remove(ib.inventoryIndex);
            ib.item(null);
            updater.dirty(ib);
            executePartyAttack(fighter, attack);
        }
    }

    // draw methods

    private void drawMonsterAttack(Monster monster, MonsterAttackResult ar) {

        if (ar.hit) {
            int index = ar.partyVictim.index;
            fw.hitBox[index].visible(true);
            fw.hitTextBox[index].visible(false);
            fw.hitTextBox[index].text = ar.funLost > 0 ? ar.funLost + "" : "";
            fw.hitComicImage[index].imageSurface(fightImages.weaponAttackImage());
        }

        fw.monsterBox[monster.fightIndex].hops();
        updater.dirty(fw.monsterBK);
        drawMonsterText(ar.attackText);
        updater.dirtyUpdate();
    }

    private void drawPartyAttack(PartyFighter fighter, Attack attack) {
        drawPartyText(tr.word(attack.text));
        int time = attack.restingTime();
        if (time == 0) {
            return;
        }
        int index = fighter.index;
        fw.actionBoxes[index].visible(false);
        fw.puffBox[index].visible(true);
        fw.barBox[index].max = time;
        fw.barBox[index].cur = time;
        updater.dirty(fw.actionPanels[index]);
        drawMonsterHit(attack);
    }

    private void drawMonsterHit(Attack attack) {
        for (AttackBash attackBash : attack.hitList()) {
            Monster monster = attackBash.monster;
            int points = attackBash.points;
            int image = attackBash.image;
            //		
            int idx = monster.fightIndex;
            if (image > 0) {
                fw.hitComicMonsterImage[idx].imageSurface(fightImages.effectImage(image));
            } else {
                fw.hitComicMonsterImage[idx].imageSurface(fightImages.weaponAttackImage());
            }
            fw.hitComicMonsterImage[idx].visible(true);

            if (attackBash.points > 0) {
                fw.monsterBarBox[idx].cur = monster.hitPoints;
            }
            if (attackBash.points >= 0) {
                String pointsText = (attackBash.critical ? "" : (points > 0 ? points + "" : ""));
                fw.monsterPointsBox[idx].visible(true);
                fw.monsterPointsBox[idx].text = pointsText;
            }

            if (monster.isDead()) {
                fw.monsterBarBox[idx].visible(false);
                fw.monsterBox[idx].visible(false);
            }
        }
        updater.dirtyUpdate(fw.monsterBK);
    }

    private void drawPartyText(String text) {
        fw.partyText.text = text;
        updater.dirty(fw.partyText);
    }

    private void drawMonsterText(String text) {
        fw.monsterText.text = text;
        updater.dirty(fw.monsterText);
    }

    //
    // WindowController

    public UiBoxContainer createWindow(BoxCommandList mg) {
        fw = new FightWindow(fight, gameEngine.levelMap().fightBackgroundNr());
        fw.pos(30, 20);
        //
        // add commands
        for (int i = 0; i < fw.actionBoxes.length; i++) {
            final ActionBox ab = fw.actionBoxes[i];
            //
            // attack
            mg.setCmd(ab.attackButton, new Command() {
                public void execute() {
                    FightWindowController.this.weaponAttack(ab.fighter);
                }
            });
            // special
            if (i != 3) {
                mg.setCmd(ab.specialAttackButton, new Command() {
                    public void execute() {
                        FightWindowController.this.specialAttack(ab.fighter);
                    }
                });
            }
            //
            // use belt item
            for (final ItemBox ib : ab.itemBoxes) {
                mg.setCmd(ib, new Command() {
                    public void execute() {
                        if (ib.item() != null) {
                            FightWindowController.this.useItem(ab.fighter, ib);
                        }
                    }
                });
            }
            //
            // defense
            mg.setCmd(ab.defenseButton, new Command() {
                public void execute() {
                    FightWindowController.this.toDefenseState(ab.fighter);
                }
            });
            //
            // select monster
            for (final MonsterSelectBox mbc : ab.monSelectBoxes) {
                mg.setCmd(mbc, new Command() {
                    public void execute() {
                        Monster monster = fight.monsterParty.monster(mbc.monsterPlaceIndex);
                        FightWindowController.this.selectMonster(ab.fighter, monster);
                    }
                });
                mg.setRightCmd(mbc, new Command() {
                    public void execute() {
                        Monster monster = fight.monsterParty.monster(mbc.monsterPlaceIndex);
                        FightWindowController.this.selectAllMonster(monster);
                    }
                });
            }
        }
        //
        // cancel defense
        for (int i = 0; i < fw.defenseBox.length; i++) {
            final UiBox db = fw.defenseBox[i];
            final int k = i;
            mg.setCmd(db, new Command() {

                public void execute() {
                    FightWindowController.this.cancelDefense(fw.actionBoxes[k].fighter);
                }
            });
        }
        //
        // give up
        mg.setRightCmd(fw.giveUpBox, new Command() {

            public void execute() {
                FightWindowController.this.giveUpFight();
            }
        });
        //
        // set up the window with default values and stuff
        for (int i = 0; i < fight.fighters.length; i++) {
            fw.actionBoxes[i].select(fight.fighters[i].targetMonster);
        }
        return fw;
    }

    @Override
    public boolean isLenientModal() {
        return true;
    }

}