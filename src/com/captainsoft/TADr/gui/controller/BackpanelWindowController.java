package com.captainsoft.TADr.gui.controller;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.commands.NextCommand;
import com.captainsoft.TADr.engine.commands.ShowDiskCommand;
import com.captainsoft.TADr.engine.commands.ShowWindowCommand;
import com.captainsoft.TADr.gui.boxes.main.BackdropBox;
import com.captainsoft.TADr.gui.windows.CaptainsoftInfoWindow;
import com.captainsoft.TADr.gui.windows.HelpWindow;
import com.captainsoft.TADr.model.questlog.QuestLogWindow;
import com.captainsoft.TADr.painting.animations.QuestChickenAnimation;
import com.captainsoft.spark.ui.BaseWindowController;
import com.captainsoft.spark.ui.BoxUpdater;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.mouse.BoxCommandList;

/**
 * Overall backdrop box.
 *
 * @author mathias
 */
public final class BackpanelWindowController extends BaseWindowController {

    // fields

    private BackdropBox backdropBox;
    private BoxUpdater updater;

    // constructors

    public BackpanelWindowController(BoxUpdater updater, Surface interfaceBackgroundSurface) {
        super();
        this.updater = updater;
        backdropBox = new BackdropBox(interfaceBackgroundSurface);
    }

    // public

    public void coins(long coins) {
        backdropBox.moneyBox.text = coins + "";
        updater.update(backdropBox.moneyBox);
    }

    public void playChickenAnimation(int mode) {
        GameEngine.instance().addAnimation(
                new QuestChickenAnimation(backdropBox.questChicken, updater, mode)
        );
    }

    // WindowController

    public UiBoxContainer createWindow(BoxCommandList commandList) {

        commandList.setCmd(backdropBox.helpWindowBox, new NextCommand(new ShowWindowCommand(
                new HelpWindow()
        )));

        commandList.setCmd(backdropBox.questChicken, new NextCommand(new ShowWindowCommand(
                new QuestLogWindow()
        )));

        commandList.setCmd(backdropBox.infoWindowBox, new NextCommand(new ShowWindowCommand(
                new CaptainsoftInfoWindow()
        )));

        commandList.setCmd(backdropBox.diskBox, new NextCommand(
                new ShowDiskCommand()
        ));

        return backdropBox;
    }

}