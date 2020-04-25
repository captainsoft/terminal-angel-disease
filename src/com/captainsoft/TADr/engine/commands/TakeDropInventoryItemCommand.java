/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.commands;

import static com.captainsoft.spark.utils.Truth.is;
import static com.captainsoft.spark.utils.Truth.not;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.ItemEngine;
import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.TADr.gui.boxes.common.ItemBox;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.party.Inventory;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.command.ParamCommand;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.i18n.Translator;

/**
 * Executes take drop actions for inventory item boxes.
 *
 * @author mathias fringes
 */
public final class TakeDropInventoryItemCommand implements ParamCommand<ItemBox> {

    // fields

    private final ItemEngine itemEngine;
    private final MainViewer mainViewer;
    private final GameEngine gameEngine;
    private final Translator translator;

    // constructors

    public TakeDropInventoryItemCommand(Translator trans) {
        super();
        this.gameEngine = GameEngine.instance();
        this.translator = trans;
        this.itemEngine = gameEngine.itemEngine();
        this.mainViewer = gameEngine.mainViewer();
    }

    // Command

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
