/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.model.questlog;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.menux.MxWindow;
import com.captainsoft.spark.command.AbstractCommand;
import com.captainsoft.spark.control.key.KeyCodes;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

import java.util.ArrayList;
import java.util.List;

/**
 * The quest log window for the party.
 *
 * @author mathias fringes
 */
public final class QuestLogWindow implements WindowController {

    // fields

    private final Integer[] KeyList = new Integer[] {KeyCodes.F2, KeyCodes.Space, KeyCodes.Enter};

    // WindowController

    public UiBoxContainer createWindow(BoxCommandList mg) {

        // set up window
        PartyQuestLog questLog = GameEngine.instance().game().QuestLog();
        final QuestLogWindowBox windowBox = new QuestLogWindowBox(questLog);

        // command to get to the next page (or close the window)
        mg.setCmd(windowBox, KeyList, new AbstractCommand("quest log window click") {
            public void execute() {

                if (!windowBox.nextPage()) {
                    GameEngine.instance().closeWindows();
                } else {
                    GameEngine.instance().mainViewer().updateBox(windowBox);
                }
            }
        });

        return windowBox;
    }

    public boolean isLenientModal() {
        return true;
    }

    public void onShow() {
    }

    //
    // nested classes

    private static final class QuestLogWindowBox extends MxWindow {

        private final List<UiBoxContainer> views = new ArrayList<UiBoxContainer>();

        private int currentPage = 1;

        public QuestLogWindowBox(PartyQuestLog questLog) {
            super();
            set(30, 35, 550, 370);
            createSurface();
            title = TadRepo.inst().Trans().word("questWindow.title");

            QuestWindowTextLayout layout = null;

            for(PartyQuestLog.Item questLogItem : questLog.items()) {

                if (layout == null || layout.add(questLogItem.solved, questLogItem.questDescription.text) == false) {

                    UiBoxContainer view = new UiBoxContainer();
                    view.set(0, 0, width, height);
                    view.clickable(false);
                    add(view);
                    layout = new QuestWindowTextLayout(view);
                    views.add(view);
                    layout.add(questLogItem.solved, questLogItem.questDescription.text);
                }
            }

            for (UiBoxContainer v : views.subList(1, views.size())) {
                v.visible(false);
            }
        }

        public boolean nextPage() {
            if (currentPage < views.size()) {
                views.get(currentPage - 1).visible(false);
                views.get(currentPage).visible(true);
                currentPage++;
                return true;
            } else {
                return false;
            }
        }

    }

}