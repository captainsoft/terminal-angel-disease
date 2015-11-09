/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.Set;

import com.captainsoft.TADr.engine.GameEngine;
import com.captainsoft.TADr.engine.GameEngineCommandExecutor;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.engine.commands.ShowCutSceneCommand;
import com.captainsoft.TADr.engine.commands.ShowIntroCommand;
import com.captainsoft.TADr.engine.commands.ShowOutroCommand;
import com.captainsoft.TADr.engine.controller.ActionWorldKeyInputController;
import com.captainsoft.TADr.engine.controller.BossKeyInputController;
import com.captainsoft.TADr.engine.controller.CutSceneKeyInputController;
import com.captainsoft.TADr.gui.boxes.main.ScrollBox;
import com.captainsoft.TADr.gui.swing.ItemCursor;
import com.captainsoft.TADr.gui.swing.MainViewerSwingBoxCreator;
import com.captainsoft.TADr.gui.swing.MainWindowSwing;
import com.captainsoft.TADr.gui.controller.BackpanelWindowController;
import com.captainsoft.TADr.gui.controller.MapWindowController;
import com.captainsoft.TADr.gui.controller.PartyWindowController;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.TADr.menu.swing.TADGuiToolkit;
import com.captainsoft.TADr.model.Direction;
import com.captainsoft.TADr.model.Game;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.ScrollText;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.TADr.painting.GameLevelMapDrawer;
import com.captainsoft.TADr.painting.animations.PartyMoveAnimationCreator;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.control.key.KeyInputRespChain;
import com.captainsoft.TADr.engine.controller.MovementKeyState;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.SingleBoxUpdater;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.mouse.BoxCommandList;
import com.captainsoft.spark.ui.mouse.BoxCommandManager;
import com.captainsoft.spark.ui.mouse.BoxMouseClickListener;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.ui_swing.SwingBoxPanel;
import com.captainsoft.spark.utils.Log;

import static com.captainsoft.spark.utils.Truth.*;

/**
 * The main viewer. Handles the main GUI.
 * 
 * @author mathias fringes
 */
public final class MainViewer {

	// fields
	
	public final GameLevelMapDrawer mapDrawer;
    public final MovementKeyState keyState = new MovementKeyState();

	private final GameEngine gameEngine;
	private final ImageLoader imageLoader;
	private final MainWindowSwing mw;	
	private final ScrollText scrollText;

    public BackpanelWindowController backpanelWindowController;

	private boolean setup = false;
	private BoxCommandManager backgroundBoxCommandManager;
	private BoxCommandManager overviewBoxCommanderManager;
	private BoxCommandManager mapBoxCommanderManager;
	private Game game;
	private ItemCursor itemCursor;
    private KeyInputRespChain keyInputChain;
	private MainViewerWindowManager mainWindowManager;
	private PartyWindowController partyWindowController;
	private PartyMember displayedPartyMember;
	private PartyMoveAnimationCreator partyAnimationCreator;
	private ScrollBox scrollBox;	
	private SwingBoxPanel backgroundPanel;
	private SwingBoxUpdater boxUpdateManager;
	private UiBoxContainer backpanelBox;
	private UiBoxContainer mapBox;
	private UiBoxContainer partyViewBox;
    private Updater mapBoxUpdater;

    // constructors

	public MainViewer(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
        this.keyInputChain = new KeyInputRespChain();
        //
        KeyInputRespChain surroundKeyInput = new KeyInputRespChain();
        surroundKeyInput.add(new BossKeyInputController());
        surroundKeyInput.add(keyInputChain);
		this.mw = new MainWindowSwing(surroundKeyInput, keyState);
		//
		this.imageLoader = TadRepo.inst().ImageLoader();						
		this.scrollText = new ScrollText();	
		this.mapDrawer = new GameLevelMapDrawer(TadRepo.inst().ItemRepo());
	}

	// accessors

	public PartyMember currentMember() {
		return displayedPartyMember;
	}

	public void setLevelMap(LevelMap map) {
		mapDrawer.setLevelMap(map);
	}

	public Animation vorhangzu() {
		return mapDrawer.vorhangZu(new SingleBoxUpdater(mapBox, boxUpdateManager));		
	}
	
	public Set<Animation> currentAnimations() {
		return (mapDrawer.getCurrentMapAnimation());		
	}

    public Updater MapUpdater() {
        return mapBoxUpdater;
    }
		
	// public

	public void close() {
		mw.setVisible(false);
	}
	
	public void switchGame(Game game) {		
		Log.begin("MainViewer:switchGame " + game);

		this.game = game;
		setLevelMap(game.LevelMap());

		setup();

		if (game.isNewGame() || displayedPartyMember == null) {
			displayedPartyMember = game.party().blob();
		} 

		partyWindowController.setParty(game.party());
		
		updateCoins();
		scrollText.clear();
		updateBox(scrollBox);

		partyWindowController.showPartyView();

		backpanelBox.update();
		mw.repaint();
		keyInputChain.set(new ActionWorldKeyInputController(gameEngine));
		
		Log.end("MainViewer:switchGame " + game);
	}

	private void setup() {			
		mw.setMainComponent(backgroundPanel);
		if (setup) {
			return;
		}
		Log.info("setting up main viewer.");

		boxUpdateManager = new SwingBoxUpdater();
		setUpBoxes();	

		// swing final box stuff...
		
		MainViewerSwingBoxCreator gb = new MainViewerSwingBoxCreator();
		backgroundPanel = gb.createSwingPanel(backpanelBox, scrollBox, mapBox, partyViewBox);
		backgroundPanel.setBackground(Color.BLACK);
		gb.registerCommands(backgroundBoxCommandManager, mapBoxCommanderManager, overviewBoxCommanderManager);
		gb.registerUpdateManager(boxUpdateManager);

		itemCursor = new ItemCursor(backgroundPanel, TadRepo.inst().ItemRepo());
		mw.setMainComponent(backgroundPanel);
		mainWindowManager = new MainViewerWindowManager(boxUpdateManager, mapBox, mapBoxCommanderManager, keyInputChain);
		setup = true;
	}

	private void addDefaultDebugClickListener(BoxCommandManager bd) {
		bd.addBoxClickObserver(new BoxMouseClickListener() {

			public void mouseClick(UiBox box, int x, int y, MouseButton button) {
				Log.trace("DEBUGCLICK: " + box + " | " + x + " " + y);
			}
		});
	}

	// ____________________________________________________________________________________________________
	// private build up boxes!	

	/**
	 * Creates the UiBoxes.
	 */
	private void setUpBoxes() {
		//
		// background
		final Surface interfaceBackgroundSurface = imageLoader.load("ifc", 28);
		// 
		BoxCommandList backCommandList = new BoxCommandList();
		backpanelWindowController = new BackpanelWindowController(boxUpdateManager, interfaceBackgroundSurface);
		backpanelBox = backpanelWindowController.createWindow(backCommandList);		
		backgroundBoxCommandManager = new BoxCommandManager(backpanelBox, new GameEngineCommandExecutor(), backCommandList);
		addDefaultDebugClickListener(backgroundBoxCommandManager);		
		
		//
		// overview interface
		
		partyWindowController = new PartyWindowController(gameEngine, interfaceBackgroundSurface);
		BoxCommandList pcl = new BoxCommandList(); 
		partyViewBox = partyWindowController.createWindow(pcl);
		
		overviewBoxCommanderManager = new BoxCommandManager(partyViewBox, new GameEngineCommandExecutor(), pcl);
		addDefaultDebugClickListener(overviewBoxCommanderManager);
		
		//
		// map		
		BoxCommandList mapCommandList = new BoxCommandList();
		MapWindowController mw = new MapWindowController(gameEngine, mapDrawer);				
		mapBox = mw.createWindow(mapCommandList);
		mapBoxCommanderManager = new BoxCommandManager(mapBox, new GameEngineCommandExecutor(), mapCommandList);
		addDefaultDebugClickListener(mapBoxCommanderManager);

        mapBoxUpdater = new SingleBoxUpdater(mapBox, boxUpdateManager);
		partyAnimationCreator = new PartyMoveAnimationCreator(gameEngine, mapDrawer, mapBoxUpdater);
		//
		// text scroller
		Surface scrollBackImage = interfaceBackgroundSurface.stamp(15, 512, 681, 91);
		scrollBox = new ScrollBox(scrollBackImage, scrollText);		
	}

    // other private methods

    private void showCutScene(ShowCutSceneCommand cmd) {
        keyInputChain.set(new CutSceneKeyInputController(cmd));
        cmd.execute();
    }

    // __________________________________________________________________________________________________
	// update actions

	public void showWindow() {
		if (!(mw.isVisible())) {
			TADGuiToolkit.centerFrame(mw);
			mw.setVisible(true);
		}
	}

	public void addItem(int id) {
		Item item = TadRepo.inst().ItemRepo().item(id);
		itemCursor.add(item);
	}
	
	public void itemCursor(Item item) {
		if (itemCursor == null) {
			return;
		}
		itemCursor.item(item);	
	}

	public void toInventory(ItemPosition pos, Item item) {
		partyWindowController.toInventory(pos, item);
	}

	public void switchMember(int memberNo) {
		switchMember(game.party().member(memberNo));
	}

	public void switchMember(PartyMember member) {
		if (mainWindowManager.lenientShown()) {
			return;
		}
		if (partyWindowController.inventoryShownFor(member)) {
			return;
		}

		Log.call("switchMember");
		displayedPartyMember = member;
		partyWindowController.displayPartyMember(displayedPartyMember);
		//
		// switch member on map
		mapDrawer.paintingInfo.setPartyMemberPic(member.nr());
		mapDrawer.retileParty();
		boxUpdateManager.update(mapBox);
		Log.end("switchMember");
	}

	public void setPartyTo(Direction d, Position position) {
		mapDrawer.paintingInfo.setFaceDirection(d);
		mapDrawer.setTo(position);
		updateBox(mapBox);
	}

	public Animation scrollParty(Position p) {
		return partyAnimationCreator.createScrollAnimation(p);
	}
	
	public Animation createCannotWalkAnimation() {
		return partyAnimationCreator.createCannotWalkAnimation();		
	}

	public void scrollCurrent(String text) {
		scroll(displayedPartyMember.nr(), text);		
	}
	
	public void scroll(Object text) {
		scroll(0, text);
	}	

	public void scroll(int type, Object text) {
		scrollText.scroll(type, text.toString());
		updateBox(scrollBox);
	}

	public void updateCoins() {
		this.backpanelWindowController.coins(game.party().coins());
	}

	public void updateWeight() {
		this.partyWindowController.updateWeight();
	}

	public void updateProtect() {		
		partyWindowController.updateProtect();
	}

	public void showWindow(WindowController wc) {
		if (wc.isLenientModal()) {
			partyWindowController.showPartyView();
		}
		mainWindowManager.showWindow(wc);
	}

	public void closeWindows() {
		if (mw.isVisible() && is(mainWindowManager)) {
			mainWindowManager.closeWindows();
		}
	}

	public boolean windowShown() {
		return mainWindowManager.windowShown();		
	}

	public void updatePartyName(String name) {
		partyWindowController.updatePartyName(name);
	}

	public void updateBox(UiBox b) {
		boxUpdateManager.updateBoxDrawing(b);
	}

	public void updateBox(UiBox ... boxes) {
		boxUpdateManager.updateBoxDrawing(boxes);
	}

	public Item itemInHand() {
		return itemCursor.item();
	}

	public boolean hasItemInHand() {
		return itemCursor.hasItem();
	}
	
	public void updatePosition(Position ... positions) {					
		boolean didUpdate = mapDrawer.retile(positions);		
		if (didUpdate) {
			boxUpdateManager.updateBoxDrawing(mapBox);
		}
	}

	public void updateFunBars(PartyMember member) {
		partyWindowController.updateFunBars(member);
	}

	public void updateSkillView(PartyMember member) {
		partyWindowController.updateSkillView(member);
	}

	public void updateItemBox(ItemPosition itemPosition, Item item) {
		partyWindowController.updateItemBox(itemPosition, item);
	}
	
	public void updateFightTile(Position position) {
		mapDrawer.removeDanger(position);		
	}

	public void removeParty() {
		this.mapDrawer.centerPartyPosition(null);
	}

	public void showIntro() {
		showCutScene(new ShowIntroCommand(this));
	}
	
	public void showOutro() {
		showCutScene(new ShowOutroCommand(this));
	}

	public void setBackgroundPanel(Component c) {
		mw.setMainComponent(c);
	}

}