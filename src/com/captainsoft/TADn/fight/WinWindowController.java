/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.fight;

import java.awt.Font;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.puzzle.chest.Chest;
import com.captainsoft.TADn.puzzle.chest.ChestWindowController;
import com.captainsoft.spark.TextBox;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * 
 * 
 * @author mathias fringes
 */
final class WinWindowController implements WindowController {

	//
	
	private Fight fight;
	private GameEngine gameEngine;

	//

	public WinWindowController(Fight fight, GameEngine gameEngine) {
		super();
		this.fight = fight;
		this.gameEngine = gameEngine;
	}
	
	// private 

	private void afterFight() {
		final Command closeCommand = new Command() {
			@Override
			public void execute() {
				fight.executeAfterFightCommand();			
			}		
		};
		//
		Item item = fight.rewardItem;
		if (item != null) {
			Chest chest = new Chest(item);
			ChestWindowController cwc = new ChestWindowController(gameEngine, chest);
			gameEngine.showWindow(cwc);
			cwc.setCloseCommand(closeCommand);
		} else {
			closeCommand.execute();
		}
	}

	// implementation of WindowController

	@Override
	public UiBoxContainer createWindow(BoxCommandList mg) {
		Surface s = TadRepo.inst().ImageLoader().load("ifc", 32);

		UiBoxContainer winBox = new ImageBox(s);		
		winBox.pos(225, 95);		

		TextBox tx = new TextBox();
		tx.font = new Font(Fonts.Times, Font.BOLD, 20);
		tx.size(210, 160);		

		TrKey winText = new TrKey("fight.win", gameEngine.game().party().name(), fight.monsterParty.allChips);		
		tx.text = TadRepo.inst().Trans().word(winText);

		winBox.add(tx);
		tx.center();

		mg.setCmd(winBox, new Command() {
			public void execute() {
				gameEngine.closeWindows();
				afterFight();
			}
		});

		return winBox;
	}

	@Override
	public boolean isLenientModal() {
		return true;
	}

	@Override
	public void onShow() {
		gameEngine.addCoins(fight.monsterParty.allChips);
	}

}
