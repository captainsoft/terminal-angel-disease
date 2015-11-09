/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.fight;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.menu.MenuController;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.ImageBox;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

import java.awt.*;

/**
 * @author mathias fringes
 */
final class GameOverWindowController implements WindowController {

    // fields

    private final GameEngine gameEngine;
    private final PartyMember member;

    // constructors

    GameOverWindowController(GameEngine gameEngine, PartyMember member) {
        super();
        this.gameEngine = gameEngine;
        this.member = member;
    }

    // WindowController

    public UiBoxContainer createWindow(BoxCommandList mg) {

        Surface s = TadRepo.inst().ImageLoader().load("ifc", 40);

        UiBoxContainer winBox = new ImageBox(s);
        winBox.pos(125, 95);

        TextBox tx = new TextBox();
        tx.font = new Font(Fonts.Times, Font.BOLD, 20);
        tx.size(400, 160);

        TrKey winText = new TrKey("fight.gameover", member.name());
        tx.text = TadRepo.inst().Trans().word(winText);

        winBox.add(tx);
        tx.center();
        tx.y += 30;

        mg.setCmd(winBox, new Command() {

            public void execute() {
                gameEngine.closeWindows();
                gameEngine.reset();
                MenuController mc = new MenuController(gameEngine);
                mc.show();
            }
        });

        return winBox;
    }

    public boolean isLenientModal() {
        return true;
    }

    public void onShow() {
        TadRepo.inst().SndPlayer().playSound("sifc", 17);
    }

}