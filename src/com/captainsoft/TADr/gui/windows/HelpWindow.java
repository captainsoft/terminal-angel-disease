/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui.windows;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.menux.MxWindow;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.ui.Fonts;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.TextBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

import java.awt.*;

/**
 * Help screen!
 *
 * @author mathias fringes
 */
public final class HelpWindow implements WindowController {

    // WindowController

    public UiBoxContainer createWindow(BoxCommandList mg) {

        final HelpWindowBox w = new HelpWindowBox();

        mg.setCmd(w, new Integer[]{KeyCodes.F1, KeyCodes.Space, KeyCodes.Enter}, new Command() {
            int page = 1;

            public void execute() {
                page++;

                if (page == 3) {
                    TadRepo.inst().GameEngine().closeWindows();
                } else {
                    w.b.text = TadRepo.inst().Trans().word("helpWindow." + page);
                    GameEngine.instance().mainViewer().updateBox(w);
                }
            }
        });

        return w;
    }

    public boolean isLenientModal() {
        return true;
    }

    public void onShow() {
    }

    //
    // nested classes

    private final class HelpWindowBox extends MxWindow {

        TextBox b;

        public HelpWindowBox() {
            super();
            title = TadRepo.inst().Trans().word("helpWindow.title");
            set(30, 35, 550, 370);
            createSurface();

            b = new TextBox();
            b.font = new Font(Fonts.Verdana, Font.PLAIN, 12);

            b.alignLeft();
            b.pos(10, 30);
            b.size(width - 20, height - 40);
            add(b);

            b.text = TadRepo.inst().Trans().word("helpWindow.1");
        }

    }

}