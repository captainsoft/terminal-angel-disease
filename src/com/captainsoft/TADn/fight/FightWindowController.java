/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight;

import java.util.ArrayList;
import java.util.Collections;

import com.captainsoft.TADn.Debugger;
import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.fight.PartyFighter.Mode;
import com.captainsoft.TADn.fight.attack.Attack;
import com.captainsoft.TADn.fight.attack.AttackBash;
import com.captainsoft.TADn.fight.attack.DefaultMonsterAttack;
import com.captainsoft.TADn.fight.attack.MonsterAttackResult;
import com.captainsoft.TADn.fight.attack.impl.MagicItemAttack;
import com.captainsoft.TADn.fight.attack.impl.SlimeBombAttack;
import com.captainsoft.TADn.fight.attack.impl.WeaponAttack;
import com.captainsoft.TADn.fight.ui.ActionBox;
import com.captainsoft.TADn.fight.ui.FightWindow;
import com.captainsoft.TADn.fight.ui.MonsterBox;
import com.captainsoft.TADn.fight.ui.MonsterSelectBox;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.TADn.menu.MenuController;
import com.captainsoft.TADn.model.TadTranslator;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemType;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.TADn.sound.SndPlayer;
import com.captainsoft.spark.CommandThread;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;
import com.captainsoft.spark.utils.Messenger;
import com.captainsoft.spark.utils.SyncUtils;
import com.captainsoft.spark.utils.Utils;

/**
 * The fight controller.
 * 
 * @author mathias fringes
 */
public final class FightWindowController extends BaseWindowController {

	// fields
		
	private final Fight fight;
	private final GameEngine gameEngine;

	private CommandThread monsterAttackThread;
	private CommandThread partyCounterThread;
	private DirtyUpdater lu;
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
		this.lu = new DirtyUpdater(gameEngine.mainViewer());
		this.fightImages = FightImages.inst();
		this.sndPlayer = TadRepo.inst().SndPlayer();
		this.tr = TadRepo.inst().Trans();				
	}

	// TODO !!!! hier unbedingt nochmal das überflüssige paint / synchronized checken!
	
	
	// start/stop methods
	
	public void beginFight() {
		sndPlayer.playSound("sifc", 12);		 
		SyncUtils.async(new Command() {		
			public void execute() {
				showIntroduction();
				startFightThreads();
			}			
		});		
	}
	
	private void showIntroduction() {
		TrKey introText = new TrKey("fight.title.against", gameEngine.party().name());
		drawMonsterText(tr.word(introText));
		lu.dirtyUpdate();
		Utils.sleepySecs(1);
		//
		ArrayList<Monster> ms = new ArrayList<Monster>(fight.monsterParty.allMonsters);
		Collections.shuffle(ms);
		for (Monster m : ms) {			
			fw.showMonsterBox(m.fightIndex);
			drawMonsterText(m.name);
			lu.dirtyUpdate(fw.monsterBK);
			
			sndPlayer.playSound("smpr", m.speech == 0 ? 99 : m.speech );		      
			
			Utils.sleepyMillis(1000);
		}
		Utils.sleepySecs(1);
		sndPlayer.playSound("sifc", 2);
		drawMonsterText("");
		fw.showMonsterBarBoxes();
	}
	
	private void startFightThreads() {
		fw.showActionPanels();
		fw.showGiveUpBox();
		lu.dirtyUpdate(fw);
		partyCounterThread = new CommandThread(100, new Command() {			
			public void execute() {
				fighterTick();
			}
		});
		partyCounterThread.start();
		monsterAttackThread = new CommandThread(312, new Command() {
			public void execute() {				 
				monsterTick();
			}
		});
		monsterAttackThread.waitBeforeMillis = 1000;
		monsterAttackThread.start();
	}
	
	private void stopFight() {
		fight.stop();
		partyCounterThread.kill();
		monsterAttackThread.kill();				
		fw.hideActionPanels();			
		lu.dirtyUpdate(fw);
	}
	
	private void giveUpFight() {
		stopFight();
		gameEngine.reset();
		MenuController mc = new MenuController(gameEngine);
		mc.show();
	}
	
	// ticks

	private synchronized void fighterTick() {
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
					fw.attackBox[index].visible(false);
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
								
				lu.dirty(fw.actionPanels[index]);
			}
		}
		if (partyChange) {
			lu.dirtyUpdate();
		}
	}

	private synchronized void monsterTick() {
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
				lu.dirtyUpdate(fw.monsterBK);							
			}		
		}
	}	

	// private methods
	
	private void executeMonsterAttack(Monster monster) {
		if (!fight.isActive()) {
			return;
		}
		sndPlayer.playSound("smat", ((monster.speech > 0) ? monster.speech: 99));
		
		MonsterAttackResult monstarAttack = new DefaultMonsterAttack(monster, fight).attack();
	    monster.toAttackState();		
		drawMonsterAttack(monster, monstarAttack);			
				
		// 
		if (monstarAttack.hit) {
			monstarAttack.partyVictim.hit();
			PartyMember member = monstarAttack.partyVictim.member;			
			member.fun.addCur(-monstarAttack.funLost);			
			if (Debugger.inst.godMode) {
				member.fun.addCur(monstarAttack.funLost);
			}
			gameEngine.mainViewer().updateFunBars(member);

			if (member.fun.isSad()) {
				sndPlayer.playSound("sifc", 17);
				stopFight();
				
				// TODO IMPLEMENT show a special picture for each member!							
				Messenger.msg(member.name() + " left the party!");				
				gameEngine.reset();
				MenuController mc = new MenuController(gameEngine);
				mc.show();				
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
		
		boolean anyMonsterDead = false;
		for (AttackBash ar : attack.hitList()) {			
			ar.monster.hit(ar.points, attack.monsterWaitFactor);
			if (ar.critical) {
				ar.monster.kill();
			}			
			if (Debugger.inst.godMonsters) {
				ar.monster.hitPoints = 1;
			}
			if (ar.monster.isDead()) {
				anyMonsterDead = true;
				fight.monsterParty.remove(ar.monster);
				boolean action = fight.removeAttackTarget(ar.monster);
				if (action) {					
					for (ActionBox ab : fw.actionBoxes) {
						ab.removeMonster(ar.monster);
						ab.select(ab.fighter.targetMonster);
					}
					lu.dirty(fw.actionPanels);	
				}
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
			SyncUtils.async(new Command() {				
				public void execute() {					
					Utils.sleepyMillis(2000);				
					WinWindowController wc = new WinWindowController(fight, gameEngine);		
					gameEngine.showWindow(wc);				
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
		lu.dirtyUpdate(ab);
	}

	private void selectAllMonster(Monster monster) {
		fight.allAttackMonster(monster);
		for (ActionBox ab : fw.actionBoxes) {
			ab.select(monster);
		}
		lu.dirtyUpdate(fw.actionPanels);
	}

	private void toDefenseState(PartyFighter fighter) {
		fighter.mode = Mode.Defense;
		int index = fighter.index;
		fw.toDefense(index);	
		lu.dirtyUpdate(fw.actionPanels[index]);
	}

	private void cancelDefense(PartyFighter fighter) {
		fighter.mode = Mode.Ready;
		int index = fighter.index;		
		fw.cancelDefense(index);
		lu.dirtyUpdate(fw.actionPanels[index]);
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
			lu.dirty(ib);
			executePartyAttack(fighter, attack);
		}
	}	
		
	//
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
		lu.dirty(fw.monsterBK);
		drawMonsterText(ar.attackText);
		lu.dirtyUpdate();
	}
	
	private void drawPartyAttack(PartyFighter fighter, Attack attack) {
		drawPartyText(tr.word(attack.text));
		int time = attack.restingTime();
		if (time == 0) {
			return;
		}
		int index = fighter.index;
		fw.actionBoxes[index].visible(false);
		fw.attackBox[index].visible(true);
		fw.barBox[index].max = time;
		fw.barBox[index].cur = time;		
		lu.dirty(fw.actionPanels[index]);			
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
				fw.monsterPointsBox[idx].visible(true);
				fw.monsterPointsBox[idx].text = points + "";
			}
			if (monster.isDead()) {
				fw.monsterBarBox[idx].visible(false);
				fw.monsterBox[idx].visible(false);
			}
		}	
		lu.dirtyUpdate(fw.monsterBK);		
	}

	private void drawPartyText(String text) {
		fw.partyText.text = text;
		lu.dirty(fw.partyText);		
	}

	private void drawMonsterText(String text) {
		fw.monsterText.text = text;		
		lu.dirty(fw.monsterText);
	}

	//
	// implementation of WindowController

	@Override
	public UiBoxContainer createWindow(BoxCommandList mg) {
		fw = new FightWindow(fight, gameEngine.levelMap().fightBackgroundNr());
		fw.pos(40, 20);
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