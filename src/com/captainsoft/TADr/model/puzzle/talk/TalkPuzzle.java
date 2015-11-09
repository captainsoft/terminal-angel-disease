/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model.puzzle.talk;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.PuzzleLoader;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.puzzle.Puzzle;
import com.captainsoft.TADr.model.puzzle.PuzzleFileData;
import com.captainsoft.TADr.model.puzzle.TileUpdate;
import com.captainsoft.TADr.model.puzzle.TileValues;
import com.captainsoft.TADr.model.puzzle.event.EventPuzzle;
import com.captainsoft.TADr.model.questlog.QuestLogEngine;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;
import com.captainsoft.spark.utils.Log;

import static com.captainsoft.spark.utils.Truth.is;

/**
 * Puzzle for a Talk.
 *
 * @author mathias fringes
 */
public final class TalkPuzzle extends BaseWindowController implements Puzzle {

	// fields

	private GameEngine gameEngine;
	private Position position;
	private PuzzleLoader puzzleLoader;
	private Talk talk;
	private TalkPage talkPage;
	private TalkWindow tw;

	// constructors

	public TalkPuzzle(Position position, PuzzleFileData fileData) {
		super();
		this.gameEngine = GameEngine.instance();
		this.position = position;
		this.talk = new Talk(fileData);
		this.puzzleLoader = TadRepo.inst().puzzleLoader();
	}

	// private

	private void loadNextTalk() {
		Log.log("Loading talk: " + talkPage.nextTalkPageNr);
		//
		if (talkPage.itemId > 0) {
			gameEngine.mainViewer().addItem(talkPage.itemId);
		}
		//
		// last talk page		
		if ((talkPage.nextTalkPageNr == 0 || talkPage.nextTalkPageNr == 255)) {			
			gameEngine.closeWindows();			
			// 
			// events
			if (talkPage.eventId1 > 0) {
				new EventPuzzle(position).executeEvent(talkPage.eventId1);
			}
			if (talkPage.eventId2 > 0) {
				new EventPuzzle(position).executeEvent(talkPage.eventId2);
			}			
			if (talk.thirdMapValue != TileValues.NOV) {
				gameEngine.levelMap().tile(position).set(3, talk.thirdMapValue);
			}												
		} else {
			talkPage = loadTalkPage(gameEngine.levelMap().nr(), talkPage.nextTalkPageNr);
			if (talkPage != null) {
				tw.showPage(talkPage);
				gameEngine.mainViewer().updateBox(tw);
			}
		}
		//
		// if item...
		ItemPosition itemPos = gameEngine.party().inventory().get(talk.dependingItemId); 
		if (talk.dependingItemId > 0) {
			int thirdValue = 255;
			if (itemPos != null) {
				thirdValue = talk.thirdMapHasItem;
			} else {
				thirdValue = talk.thirdMapHasNoItem;
			}
			if (thirdValue != 255) {
				gameEngine.levelMap().tile(position).set(3, thirdValue);
			}
		}			
		if (itemPos != null && talk.takeItem) {
			gameEngine.deleteItem(itemPos);
		}

	}

	private TalkPage loadTalkPage(int map, int realTalkPageId) {
		if (map == 20 && realTalkPageId == 120) {
			// show the outro
			gameEngine.showOutro();
			return null;
		}
		TalkPage tp = puzzleLoader.loadTalkPage(map, realTalkPageId);
		switch(tp.portraitId) {		 
			case 1:
			case 2:
			case 3:
			case 4:			
				tp.name = gameEngine.party().member(tp.portraitId).name();
				break;
		}
		//
		if (tp.name.startsWith(">>")) {
			tp.name = tp.name.substring(2);
		}
		tp.text = tp.text.replaceAll("<NOP>", gameEngine.party().name());
		tp.text = tp.text.replaceAll("<NAM>", gameEngine.game().player());
		//
		// play sound
		if (tp.text.startsWith("<S")) {        	         
			Integer id = Integer.parseInt(tp.text.substring(2, 5));        	
			TadRepo.inst().SndPlayer().playSound("sscl", id);
			tp.text = tp.text.substring(6);
		} 
		else if (tp.text.startsWith("<M")) {
			tp.text = tp.text.substring(6);
			// here it should play midi, but not used yet.
		}               
		//
		// SPECIALS
		if ((map == 6 && realTalkPageId == 16) || (map == 6 && realTalkPageId == 36)) {
			// holla die waldfee
			gameEngine.updateTile(new TileUpdate(78, 49, new TileValues(1, 179)));
			gameEngine.updateTile(new TileUpdate(78, 50, new TileValues(1, 165)));        	         	
		}
		else if (map == 20 && realTalkPageId == 70) {
			// becomes the chili
			gameEngine.updateTile(new TileUpdate(24, 61, new TileValues(1, 13)));
			gameEngine.updateTile(new TileUpdate(24, 62, new TileValues(1, 14)));
		}		
		//
        // questDescription
        QuestLogEngine.inst.updateQuestLog(realTalkPageId);
        //
		return tp;                        
	}

	// Puzzle

	public void execute() {		
		gameEngine.showWindow(this);
	}

	// WindowController

	public UiBoxContainer createWindow(BoxCommandList mg) {	
		tw = new TalkWindow();		
		tw.pos(40, 10);
		Command nextTalkCmd = new Command() {

			public void execute() {				
				loadNextTalk();
			}			
		};

		mg.setCmd(tw, new Integer[] {KeyCodes.Space, KeyCodes.Enter}, nextTalkCmd);
		mg.setCmd(tw.portrait, nextTalkCmd);
		talkPage = loadTalkPage(gameEngine.levelMap().nr(), talk.getRealTalkPageId(gameEngine.party()));
		tw.showPage(talkPage);	
		return tw;
	}

}