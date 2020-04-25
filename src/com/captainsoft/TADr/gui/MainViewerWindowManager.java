/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.gui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import com.captainsoft.spark.control.key.CommandKeyInput;
import com.captainsoft.spark.control.key.KeyInputRespChain;
import com.captainsoft.spark.ui.BoxUpdater;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;
import com.captainsoft.spark.ui.mouse.BoxCommandManager;
import com.captainsoft.spark.utils.ExcUtils;

/**
 * Window manager for the main GUI.
 *
 * @author mathias fringes
 */
public final class MainViewerWindowManager {

    // fields

    private final ArrayList<UiBox> openWindows;
    private final BoxCommandManager boxCommandManager;
    private final BoxUpdater updater;
    private final Deque<WindowController> windowControllers;
    private final KeyInputRespChain keyInputChain;
    private final UiBoxContainer backgroundBox;

    // constructors

    public MainViewerWindowManager(BoxUpdater updater, UiBoxContainer backgroundBox, BoxCommandManager boxCommandManager, KeyInputRespChain keyInputChain) {
        super();
        ExcUtils.argNotNull("updater", updater);
        ExcUtils.argNotNull("backgroundBox", backgroundBox);
        ExcUtils.argNotNull("boxCommandManager", boxCommandManager);
        ExcUtils.argNotNull("keyInputChain", keyInputChain);
        //
        this.updater = updater;
        this.backgroundBox = backgroundBox;
        this.boxCommandManager = boxCommandManager;
        this.keyInputChain = keyInputChain;
        //
        this.windowControllers = new ArrayDeque<WindowController>();
        this.openWindows = new ArrayList<UiBox>();
    }

    // public

    public void showWindow(WindowController wc) {
        BoxCommandList windowCommandList = new BoxCommandList();
        UiBox box = wc.createWindow(windowCommandList);

        boxCommandManager.addAdditionalCommandList(windowCommandList);
        if (wc.isLenientModal()) {
            keyInputChain.lenientOn(new CommandKeyInput(windowCommandList.keyCommands));
        } else {
            keyInputChain.first(new CommandKeyInput(windowCommandList.keyCommands));
        }

        windowControllers.addLast(wc);
        backgroundBox.add(box);
        openWindows.add(box);
        wc.onShow();
        updater.update(backgroundBox);
    }

    public void closeWindows() {
        if (windowShown()) {

            boxCommandManager.clearAllAditionalCommandLists();
            keyInputChain.removeFirst(windowsShownCount());

            backgroundBox.remove(openWindows);
            openWindows.clear();
            windowControllers.clear();
            updater.update(backgroundBox);
        }
    }

    public boolean windowShown() {
        return (!windowControllers.isEmpty());
    }

    public int windowsShownCount() {
        return (windowControllers.size());
    }

    public boolean lenientShown() {
        return (!windowControllers.isEmpty() && windowControllers.getLast().isLenientModal());
    }

}
