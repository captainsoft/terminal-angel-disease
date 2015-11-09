/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.commands;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemPosition;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.Party;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.TADn.puzzle.TileValues;
import com.captainsoft.TADn.puzzle.book.BookController;
import com.captainsoft.TADn.puzzle.teleporter.Teleporter;
import com.captainsoft.TADn.puzzle.teleporter.TeleporterPuzzle;
import com.captainsoft.TADn.sound.SndPlayer;
import com.captainsoft.spark.control.ParamCommand;
import com.captainsoft.spark.utils.Utils;

/**
 * Uses an item from the inventory (not in fights).
 * 
 * @author mathias fringes
 */
public final class UseInventoryItemCommand implements ParamCommand<ItemBox> {

	// fields
	
	private final SndPlayer sndPlayer;	
	private final GameEngine gameEngine;	
	
	// constructors
	
	public UseInventoryItemCommand(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;	
		this.sndPlayer = TadRepo.inst().SndPlayer();
	}

	// overridden
	
	@Override
	public void execute(ItemBox box) {
		
		if (gameEngine.itemInHand() != null) {
			return;
		}
		
		final Item item = box.item();
		if (item == null) {
			return;
		}		
			
		if (item.isFightItem()) {
			gameEngine.say("item.use.fightOnly");
			return;
		}
		
		boolean killItem = true;
		Party party = gameEngine.party();
		PartyMember member = gameEngine.mainViewer().currentMember();
		
		switch(item.type()) {
			case Book:			
				BookController wc = new BookController(gameEngine, item.value());
				gameEngine.showWindow(wc);								
				killItem = false;
				break;
			case CurrentFunPoints:	
				if (member.fun.isFull()) {
					gameEngine.say("fitPoints.eat.full");
					killItem = false;
				} else {
					member.fun.addCur(item.value() + (int) (Math.random() * (item.value() / 2)));
					gameEngine.mainViewer().updateFunBars(member);				
					gameEngine.say("fitPoints.eat.thankyou");
				}
				break;
			case Teleporter:
				if (gameEngine.mainViewer().windowShown()) {
					killItem = false;
					break;
				}
				int mapNr = gameEngine.game().LevelMap().nr();
				if (mapNr != 4 && mapNr != 5 && mapNr < 18) {
					Teleporter tp = createPrismaTeleporter();			
					new TeleporterPuzzle(gameEngine, tp).execute();
					gameEngine.say("telep.prisma.funny");
				} else {		
					gameEngine.sayCurrent("teleporter.misuse." + mapNr);
					killItem = false;
				}
				break;
			case Chili:
				gameEngine.say("chiliStuff.dontEat");
				killItem = false;
				break;
			case FunPointsMax:											
				sndPlayer.playSound("sifc", 23);
				int dval = item.value() + (int) (Math.random() * (item.value() / 2));
			    member.fun.addMax(dval);			    			  
			    gameEngine.mainViewer().updateFunBars(member);
				gameEngine.say("item.use.funPointsMax");
				break;
			case FitPointsMax:
				sndPlayer.playSound("sifc", 23);
				member.setPtsFit(member.getPtsFit() + 1);
				gameEngine.mainViewer().updateSkillView(member); 
				gameEngine.say("item.use.fitPointsMax");
				break;
			case FoxPointsMax:
				sndPlayer.playSound("sifc", 23);
				member.setPtsFox(member.getPtsFox() + 1);
				if (member == party.detective()) {
					member.special4.learn(Utils.random(5) + 9);
				}
				gameEngine.mainViewer().updateSkillView(member);
				if (member == party.blob()) { 
					int i = member.getPtsFox() +1;
					gameEngine.say("item.use.foxPointsMax", (i * i), i);					
				} else {
					gameEngine.say("item.use.foxPointsMax");	
				}							
				break;
			default:
				//
				//  puzzle item
				if (item.isPuzzleItem()) {
					LevelMap map = gameEngine.levelMap();
					Position pos = gameEngine.party().position();
					Position pz = map.isPuzzleAround(pos);
					if (pz != null) {
						gameEngine.puzzleEngine().doItemPuzzle(item, pz);
					}						
					return;
				} else {					
					gameEngine.say("item.use.cannot");
					killItem = false;
				}
				break;
		}		
		//
		if (killItem) {
			gameEngine.deleteItem(new ItemPosition(member, box.inventoryIndex));
			return;
		}		
	}
	
	// private
	
	private Teleporter createPrismaTeleporter() {
		Teleporter tp = new Teleporter();
		tp.mapNr = 2;
		tp.playSound = true;
		tp.position = new Position(51, 44);
		tp.tileValue = new TileValues();	
		return tp;
	}

}
