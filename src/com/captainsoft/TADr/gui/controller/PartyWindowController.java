package com.captainsoft.TADr.gui.controller;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.engine.commands.*;
import com.captainsoft.TADr.engine.commands.SwitchMemberCommand;
import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.TADr.gui.boxes.inv.InventoryBox;
import com.captainsoft.TADr.gui.boxes.inv.OverviewBox;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.command.ParamCommandAdapter;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.mouse.BoxCommandList;
import com.captainsoft.spark.utils.Log;

/**
 * Window controller for the party view (overview and inventory boxes).
 * 
 * @author mathias
 */
public final class PartyWindowController extends BaseWindowController {

	// fields
		
	private InventoryBox inventoryBox;
	private MainViewer mainViewer;
	private OverviewBox overviewBox;
	private PartyMember displayedPartyMember;

	// constructors
	
	public PartyWindowController(GameEngine gameEngine, Surface interfaceBackgroundSurface) {		
		this.mainViewer = gameEngine.mainViewer();
		//
		overviewBox = new OverviewBox(interfaceBackgroundSurface);
		inventoryBox = new InventoryBox(TadRepo.inst().ImageLoader(), overviewBox.height);
		inventoryBox.carryString = TadRepo.inst().Trans().word("inventory.carry");
		overviewBox.add(inventoryBox);	
		
		displayedPartyMember = gameEngine.party().detective();	
	}
	
	// public
		
	public void updateFunBars(PartyMember member) {
		if (inventoryBox.visible()) {
			if (displayedPartyMember == member) {
				updateSkillView(member);
			}
		} else {
			mainViewer.updateBox(overviewBox.getBarBox(member.nr()));
		}		
	}

	public void updateSkillView(PartyMember member) {
		if (inventoryBox.visible() && (displayedPartyMember == member)) {
			inventoryBox.skillBox.updateValues(member);
			mainViewer.updateBox(inventoryBox.skillBox);
		}
	}

	public void updateItemBox(ItemPosition itemPosition, Item item) {
		if (inventoryBox.visible() && displayedPartyMember == itemPosition.member) {
			UiBox b = inventoryBox.updateItemBox(itemPosition, item);
			mainViewer.updateBox(b);
			updateWeight();
		}		
	}

	public void updatePartyName(String name) {
		overviewBox.partyName.text = name;
		mainViewer.updateBox(overviewBox);		
	}

	public void updateProtect() {	
		inventoryBox.equiqBox.protectBox.text = TadRepo.inst().Trans().word("party.protect") + displayedPartyMember.protect();
		mainViewer.updateBox(inventoryBox.equiqBox.protectBox);
	}

	public void showPartyView() {	
		inventoryBox.visible(false);		
		mainViewer.updateBox(inventoryBox.parent());		
	}

	public boolean inventoryShownFor(PartyMember member) {
        return (inventoryBox.visible() && (displayedPartyMember == member));
    }

	public void setParty(Party party) {
        displayPartyMember(party.member(displayedPartyMember.nr()));
		overviewBox.setParty(party);
	}

	public void toInventory(ItemPosition pos, Item item) {
		if (pos.member == displayedPartyMember) {
			ItemBox box = inventoryBox.findItemBox(pos);
			box.item(item);
			if (inventoryBox.visible()) {
				mainViewer.updateBox(box);
				updateWeight();
				updateProtect();
			}
		}
	}

	public void displayPartyMember(PartyMember member) {
		this.displayedPartyMember = member;
		inventoryBox.member(displayedPartyMember);
		updateWeight();
		updateProtect();		
		inventoryBox.visible(true);			

		for (int i = 0; i < 4; i++) {
			inventoryBox.quickMemberBoxes[i].selected = (displayedPartyMember.nr() - 1 == i);
		}
		mainViewer.updateBox(inventoryBox);		
	}	
	
	public void updateWeight() {
		int wgt = displayedPartyMember.inventory().weight();
		int max = displayedPartyMember.maxWgt();
		inventoryBox.updateWeigthBox(wgt, max);
		mainViewer.updateBox(inventoryBox.weightBox);		
	}

	// WindowController

	public UiBoxContainer createWindow(BoxCommandList cmdList) {

		//
		// party overview commands
		//
		cmdList.setCmd(overviewBox.showInvBox, new Command() {

			public void execute() {
				mainViewer.switchMember(displayedPartyMember);
			}
		});
		cmdList.setCmds(new UiBox(overviewBox, 18, 75, 88, 184), new SwitchMemberCommand(1), new DropToMemberInventoryCommand(1));
		cmdList.setCmds(new UiBox(overviewBox, 115, 75, 88, 184), new SwitchMemberCommand(2), new DropToMemberInventoryCommand(2));
		cmdList.setCmds(new UiBox(overviewBox, 18, 302, 88, 184), new SwitchMemberCommand(3), new DropToMemberInventoryCommand(3));
		cmdList.setCmds(new UiBox(overviewBox, 115, 302, 88, 184), new SwitchMemberCommand(4), new DropToMemberInventoryCommand(4));
					
		//
		// inventory commands
		//
		// equiq/skill toggleBox
		cmdList.setCmd(inventoryBox.invViewToggleBox, new Command() {

			public void execute() {
				inventoryBox.toggleSkillEquiq();
				Log.trace("toggle equiq/skill view");
				mainViewer.updateBox(inventoryBox.equiqBox, inventoryBox.skillBox);
			}
		});
		
		//
		// switch member quick boxes
		for (int i = 0; i < 4; i++) {
			cmdList.setCmd(inventoryBox.quickMemberBoxes[i], new SwitchMemberCommand(i + 1));
			cmdList.setRightCmd(inventoryBox.quickMemberBoxes[i], new DropToMemberInventoryCommand(i + 1));
		}
		//
		// back to overview
		cmdList.setCmd(new UiBox(inventoryBox.inventoryCtrlBox, 116, 2, 105, 25), new Command() {

			public void execute() {
				inventoryBox.visible(false);
				mainViewer.updateBox(inventoryBox.parent());
			}
		});
		
		//
		// inventory item boxes handling
		UseInventoryItemCommand useItemBoxCmd = new UseInventoryItemCommand();
		TakeDropInventoryItemCommand takeDropItemCmd = new TakeDropInventoryItemCommand(TadRepo.inst().Trans());
        SayItemInfoCommand sayItemIndoCms = new SayItemInfoCommand();
		for (int i = 0; i < inventoryBox.allItemBoxes.size(); i++) {

			ItemBox itemBox = inventoryBox.allItemBoxes.get(i);

            // investigate/use item
            cmdList.setCmd(itemBox, new NextCommand(new ParamCommandAdapter<ItemBox>(sayItemIndoCms, itemBox)));
            cmdList.setDoubleClickCmd(itemBox, new NextCommand(new ParamCommandAdapter<ItemBox>(useItemBoxCmd, itemBox)));

			// move (take, drop) item
			cmdList.setRightCmd(itemBox, new ParamCommandAdapter<ItemBox>(takeDropItemCmd, itemBox));
		}
				
		overviewBox.setToTop(inventoryBox);
		return overviewBox;
	}

}
