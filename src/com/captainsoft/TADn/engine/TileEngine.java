/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine;

import static com.captainsoft.TADn.party.ItemInstance.Sweets1;
import static com.captainsoft.TADn.party.ItemInstance.Sweets2;
import static com.captainsoft.TADn.party.ItemInstance.Sweets3;
import static com.captainsoft.TADn.party.ItemInstance.Sweets4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.captainsoft.TADn.TadExceptionHandler;
import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.loader.ItemRepository;
import com.captainsoft.TADn.loader.MonsterLoader;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemInstance;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.Party;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.TADn.puzzle.PuzzleEngine;
import com.captainsoft.TADn.puzzle.TileUpdate;
import com.captainsoft.TADn.puzzle.TileValues;
import com.captainsoft.TADn.sound.SndPlayer;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.Utils;

/**
 * 
 * @author mathias fringes
 */
public final class TileEngine {
	
	// statics
	
	private static List<ItemInstance> sweets = new ArrayList<ItemInstance>();
	
	static {							
		Collections.addAll(sweets, Sweets1, Sweets2, Sweets3, Sweets4);
		sweets = Collections.unmodifiableList(sweets);	
	}
	
	// fields
	
	private final FightEngine fightEngine;
	private final GameEngine gameEngine;
	private final ItemRepository itemRepository;
	private final SndPlayer sndPlayer;
	private final StatusInfoEngine statusInfoEngine;	
	
	// constructors
	
	public TileEngine(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
		this.sndPlayer = TadRepo.inst().SndPlayer();
		this.itemRepository = TadRepo.inst().ItemRepo();
		this.fightEngine = new FightEngine();
		this.statusInfoEngine = new StatusInfoEngine(gameEngine);
	}
	
	// public
	
	public boolean doTileAction() {
		try {
			Party party = gameEngine.party();
			PuzzleEngine puzzleEngine = gameEngine.puzzleEngine();  
			Position p = party.position();
			LevelMap levelMap = gameEngine.levelMap();
			boolean exe = false;
			if (puzzleEngine.hasActivePuzzle(p)) {
				gameEngine.stopParty();
				puzzleEngine.executeActivePuzzle(p);
				exe = true;
			} else {
				Log.trace("do tile action " + p);
				if (!exe) {
					Position fightPosition = fightEngine.shouldFight(levelMap, p);
					if (fightPosition != null) {						
						int monsterPartyId = levelMap.rndMonsterPartyId();
						if (monsterPartyId > 0) {
							gameEngine.stopParty();
							Log.info("A random fight happens with monster party: " + monsterPartyId + " pos: " + fightPosition);
							MonsterLoader monsterLoader = TadRepo.inst().MonsterLoader();
							Integer[] mo = monsterLoader.loadMonsterParty(levelMap.nr(), monsterPartyId);
							gameEngine.fight(mo, fightPosition);
							levelMap.tile(fightPosition).redanger();
							exe = true;
						}
					}					
				}	
			}				 	
			statusInfoEngine.statusInfor();
			return exe;
		} catch (Exception e) {
			TadExceptionHandler.errorMessageAndMenu("Fatal error", e);
			return true;
		}
	}
	
	public boolean extraTileClickAction(LevelMap map, Position position, Position partyPosition) {
		if (map.isCoffeeAutomaton(position)) {
			drinkFromCoffeeAutomaton();
			return true;
		} 
		else if (map.isAutomatonLimo(position)) {
			useAutomaton(itemRepository.item(ItemInstance.Limo));
			return true;
		} 
		else if(map.isAutomatonSweets(position)) {
			useAutomaton(itemRepository.item(rndSweets()));
			return true;
		} 
		else if(map.isAutomatonInstant(position)) {
			useAutomaton(itemRepository.item(ItemInstance.InstantCoffee));
			return true;
		}
		
		// item doubbler
		if ((map.nr() == 11) && (position.equals(16, 49))) {			
			if (map.tile(16, 49).value(1) == 49 && map.tile(17, 49).value(2) != 0) {				
				TileValues tv1 = new TileValues(104, map.tile(17, 49).value(2), TileValues.NOV, 250);
				TileValues tv2 = new TileValues(1, 93); 
				gameEngine.updateTile(new TileUpdate(new Position(15, 49), tv1));
				gameEngine.updateTile(new TileUpdate(new Position(16, 49), tv2));
			}
		}
			
		// 2. kaffeepot in level 19
		if ((map.nr() == 19) && (position.equals(56, 25))) {
			if (map.tile(position).value(1) == 47) {
				if (map.tile(54,26).value(1) != 46) {
					map.tile(partyPosition).set(3, 166);					
					gameEngine.reexecuteTileAction();
				} else if (map.tile(54, 27).value(2) == 0) {
					map.tile(partyPosition).set(3, 168);
					gameEngine.reexecuteTileAction();
				} else if (map.tile(54, 27).value(2) != 179) {
					map.tile(partyPosition).set(3, 167);
					gameEngine.reexecuteTileAction();
				} else {
					map.tile(56, 25).set(3, 16);
					gameEngine.reexecuteTileAction();
				}
			}
		}
		
		return false;		
	}
	
	// private methods
	
	private void drinkFromCoffeeAutomaton() {				
		for (PartyMember pm : gameEngine.party().members) {
			pm.fun.addCur(45 + Utils.random(10));
			gameEngine.mainViewer().updateFunBars(pm);
		}		
		gameEngine.sayRnd("tile.coffeeAutomaton");		
		sndPlayer.playSound("sifc", 22);
	}
	
	private void useAutomaton(Item item) {
		int coins = item.coins();
		if (gameEngine.party().isAffordable(coins)) {
			gameEngine.takeItem(item);
			gameEngine.party().addCoins(-coins);
			sndPlayer.playSound("sifc", 16);								
		} else {
			gameEngine.sayRnd("tile.useAutomation.noChips", coins + "");			
		}
	}
		
	private ItemInstance rndSweets() {
		return Utils.randomSelect(sweets);
	}
	
}
