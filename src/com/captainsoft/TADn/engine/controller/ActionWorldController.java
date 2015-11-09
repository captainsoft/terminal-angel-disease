/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.controller;

import static com.captainsoft.spark.utils.Truth.is;

import java.util.List;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.gui.MainViewer;
import com.captainsoft.TADn.loader.ItemRepository;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemInstance;
import com.captainsoft.TADn.party.ItemPosition;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.TADn.party.Tile;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.utils.Log;

/**
 * Controller handling mouse click action in the map view (also called
 * the "Action-Welt").
 *
 * @author mathias fringes
 */
public final class ActionWorldController {
	
	// fields
	
	private final ItemRepository itemRepo;
	private final GameEngine gameEngine;
	private final MainViewer mainViewer;	

	// constructors
	
	public ActionWorldController(GameEngine gameEngine, MainViewer mainViewer) {
		super();
		this.gameEngine = gameEngine;
		this.mainViewer = mainViewer;
		//
		this.itemRepo = TadRepo.inst().ItemRepo();	
	}
	
	// public
		
	public void clickMapPosition(final Position position, final MouseButton button) {	
		final LevelMap levelMap = gameEngine.levelMap();
		Log.debug("ClickPosition" + position + " " + levelMap.tile(position));
		//		
		
		gameEngine.stopParty();	
		
		//
		Position partyPosition = gameEngine.party().position();	
		switch(button) {
			// LEFT: Tile click in the Action-Welt
			// moving and executing puzzles
			case Left:
				if (partyPosition.isNextTo(position)) {
					if (isWindowShown()) {
						return;
					}
					if (!mainViewer.hasItemInHand()) {					
						if (gameEngine.puzzleEngine().executePassivePuzzle(position)) {
							return;
						}					
						if(gameEngine.tileEngine().extraTileClickAction(levelMap, position, partyPosition)) {
							return;
						}							
					}
				}							
				break;				
			case Right:
				// RIGHT MouseButton
				// item dropping and taking
				if (partyPosition.isNextTo(position)) {
					dropTakeItem(position);
				} 
				else if (partyPosition.equals(position)) {
					Item cursorItem = mainViewer.itemInHand();
					if (is(cursorItem)) {
						boolean take = takeItem(cursorItem);
						if (take) {
							gameEngine.takeItem(null);
						}						
					}
				}
				break;
			default:
				break;
		}	

		if (isWindowShown()) {
			return;
		}
		
		if (button == MouseButton.Left) {	
			gameEngine.goPartyGo(position);		
	    }		
	}
		
	// private	
	
	private boolean takeItem(Item item) {
		int startMemberNr = mainViewer.currentMember().nr();
		//
		List<PartyMember> allFrom = gameEngine.party().allFrom(startMemberNr);		
		ItemPosition pos = gameEngine.itemEngine().findSuitablePos(allFrom, item);
		//
		if (pos != null) {
			gameEngine.party().inventory().set(pos, item);			
			mainViewer.toInventory(pos, item);
			return true;
		}
		//
		return false;
	}
	
	
	private void dropTakeItem(Position position) {
		
		LevelMap levelMap = gameEngine.levelMap();
		
		Tile tile = levelMap.tile(position);
		Item cursorItem = mainViewer.itemInHand();
		
		Item tileItem = itemRepo.item(tile.item());
		if (cursorItem != null) {
			if (tile.dropable()) {					
				tile.item(cursorItem.id());
				gameEngine.takeItem(tileItem);									
				mainViewer.updatePosition(position);				
			}
			else if (levelMap.isCoffeeAutomaton(position)) {
				if (ItemInstance.CoffeeCupEmpty.match(cursorItem)) {
					gameEngine.takeItem(itemRepo.item(ItemInstance.CoffeeCupFull));
				}
			}
		} else {															
			if (tileItem != null) {
				tile.removeItem();
				gameEngine.takeItem(tileItem);
				mainViewer.updatePosition(position);				
			}
		}
	}	
	
	private boolean isWindowShown() {
		return (mainViewer.windowShown());
	}
	
}
