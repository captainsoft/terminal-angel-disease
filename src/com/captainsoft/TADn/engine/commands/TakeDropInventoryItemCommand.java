/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine.commands;

import static com.captainsoft.spark.utils.Truth.is;
import static com.captainsoft.spark.utils.Truth.not;

import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.engine.ItemEngine;
import com.captainsoft.TADn.gui.MainViewer;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.TADn.party.Inventory;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemPosition;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.control.ParamCommand;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.i18n.Translator;

/**
 * Executes take drop actions for item boxes.
 *  
 * @author mathias fringes
 */
public final class TakeDropInventoryItemCommand implements ParamCommand<ItemBox> {

	private final ItemEngine itemEngine;
	private final MainViewer mainViewer;
	private final GameEngine gameEngine;
	private final Translator translator;
	
	public TakeDropInventoryItemCommand(GameEngine gameEngine, Translator trans) {
		super();
		this.gameEngine = gameEngine;		
		this.translator = trans;
		this.itemEngine = gameEngine.itemEngine();
		this.mainViewer = gameEngine.mainViewer();
	}
	
	@Override
	public void execute(ItemBox itemBox) {
		//
		Item cursorItem = mainViewer.itemInHand();
		Item item = itemBox.item();		
		PartyMember displayedPartyMember = mainViewer.currentMember();
		//
		if (not(item) && not(cursorItem)) {
			return;
		}					
		Inventory inventory = displayedPartyMember.inventory();					
		// take					
		if (is(item) && not(cursorItem)) {						
			inventory.remove(itemBox.inventoryIndex);						
			gameEngine.takeItem(item);
			itemBox.item(null);
			mainViewer.updateBox(itemBox);
		}
		// change
		else if (is(item, cursorItem)) {
			ItemPosition ip = new ItemPosition(displayedPartyMember, itemBox.inventoryIndex);
			TrKey cannotDropItMessage = itemEngine.tryDropItem(ip, cursorItem); 
			if (is(cannotDropItMessage)) {
				mainViewer.scroll(translator.word(cannotDropItMessage));
			} else {
				inventory.item(itemBox.inventoryIndex, cursorItem);	
				itemBox.item(cursorItem);
				gameEngine.takeItem(item);
				mainViewer.updateBox(itemBox);
			}
		}
		// drop
		else if (not(item) && is(cursorItem)) {
			ItemPosition ip = new ItemPosition(displayedPartyMember, itemBox.inventoryIndex);
			TrKey cannotDropItMessage = itemEngine.tryDropItem(ip, cursorItem); 
			if (is(cannotDropItMessage)) {
				mainViewer.scroll(translator.word(cannotDropItMessage));	
			} else {
				inventory.item(itemBox.inventoryIndex, cursorItem);
				itemBox.item(cursorItem);
				gameEngine.takeItem(null);
				mainViewer.updateBox(itemBox);	
				if (cursorItem.isArmor()) {
					displayedPartyMember.calcProtection();
				}
			}				
		}
		//										
		if (is(item) && item.type().isArmor()) {
			displayedPartyMember.calcProtection();
		}
	    //
	 	mainViewer.updateWeight();
	 	mainViewer.updateProtect();
	}

}
