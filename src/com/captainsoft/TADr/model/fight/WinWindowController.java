/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight;

import java.awt.Font;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.puzzle.chest.Chest;
import com.captainsoft.TADr.model.puzzle.chest.ChestWindowController;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * Window controller for the party winning screen!
 *
 * @author mathias fringes
 */
final class WinWindowController implements WindowController {

    // fields

    private Fight fight;
    private GameEngine gameEngine;

    // constructors

    public WinWindowController(Fight fight, GameEngine gameEngine) {
        super();
        this.fight = fight;
        this.gameEngine = gameEngine;
    }

    // private

    private void afterFight() {
        final Command closeCommand = new Command() {

            public void execute() {
                fight.executeAfterFight();
            }
        };
        //
        Item item = fight.rewardItem;
        if (item != null) {
            Chest chest = new Chest(item);
            ChestWindowController cwc = new ChestWindowController(chest);
            gameEngine.showWindow(cwc);
            cwc.setCloseCommand(closeCommand);
        } else {
            closeCommand.execute();
        }
    }

    // WindowController

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

    public boolean isLenientModal() {
        return true;
    }

    public void onShow() {
        gameEngine.addCoins(fight.monsterParty.allChips);
    }

}
