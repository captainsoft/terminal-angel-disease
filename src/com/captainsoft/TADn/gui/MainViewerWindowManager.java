/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.BoxUpdater;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.mouse.BoxCommandList;
import com.captainsoft.spark.ui.mouse.BoxCommandManager;
import com.captainsoft.spark.utils.ExcUtils;

/**
 *
 * @author mathias fringes
 */
public final class MainViewerWindowManager {
	
	private final ArrayList<UiBox> openWindows;	
	private final BoxCommandManager boxCommandManager;
	private final BoxUpdater updater;
	private final Deque<WindowController> windowControllers;
	private final UiBoxContainer backgroundBox;
	
	public MainViewerWindowManager(BoxUpdater updater, UiBoxContainer backgroundBox, BoxCommandManager boxCommandManager) {
		super();
		ExcUtils.argNotNull("updater", updater);
		ExcUtils.argNotNull("backgroundBox", backgroundBox);
		ExcUtils.argNotNull("boxCommandManager", boxCommandManager);
		//
		this.updater = updater;
		this.backgroundBox = backgroundBox;
		this.boxCommandManager = boxCommandManager;
		//
		this.windowControllers = new ArrayDeque<WindowController>();
		this.openWindows = new ArrayList<UiBox>();
	}
	
	public void showWindow(WindowController wc) {
		BoxCommandList windowCommandList = new BoxCommandList();
		UiBox box = wc.createWindow(windowCommandList);
		boxCommandManager.addAdditionalCommandList(windowCommandList);

		windowControllers.addLast(wc);
		backgroundBox.add(box);
		openWindows.add(box);
		wc.onShow();
		updater.update(backgroundBox);
	}

	public void closeWindows() {
		if (windowShown()) {
			boxCommandManager.clearAllAditionalCommandListst();
			backgroundBox.remove(openWindows);
			openWindows.clear();
			windowControllers.clear();
			updater.update(backgroundBox);
		}
	}

	public boolean windowShown() {
		return (!windowControllers.isEmpty());
	}
	
	public boolean lenientShown() {
		return (!windowControllers.isEmpty() && windowControllers.getLast().isLenientModal());
	}

}
