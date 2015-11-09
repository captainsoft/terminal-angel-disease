/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.gui;

import java.awt.Color;
import java.awt.Component;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.engine.commands.ShowIntroCommand;
import com.captainsoft.TADn.engine.commands.ShowOutroCommand;
import com.captainsoft.TADn.engine.commands.SwitchMember;
import com.captainsoft.TADn.engine.commands.TakeDropInventoryItemCommand;
import com.captainsoft.TADn.engine.commands.UseInventoryItemCommand;
import com.captainsoft.TADn.engine.controller.MainInputController;
import com.captainsoft.TADn.engine.controller.ActionWorldController;
import com.captainsoft.TADn.gui.boxesCommon.ItemBox;
import com.captainsoft.TADn.gui.boxesInv.InventoryBox;
import com.captainsoft.TADn.gui.boxesInv.OverviewBox;
import com.captainsoft.TADn.gui.boxesMain.BackpanelBox;
import com.captainsoft.TADn.gui.boxesMain.ScrollBox;
import com.captainsoft.TADn.gui.boxesMain.SimpleMapView;
import com.captainsoft.TADn.gui.swing.ItemCursor;
import com.captainsoft.TADn.gui.swing.MainViewerSwingBoxCreator;
import com.captainsoft.TADn.gui.swing.MainWindowSwing;
import com.captainsoft.TADn.loader.ImageLoader;
import com.captainsoft.TADn.menu.swing.TADGuiToolkit;
import com.captainsoft.TADn.model.Direction;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.model.ScrollText;
import com.captainsoft.TADn.painting.PartyScroller;
import com.captainsoft.TADn.painting.SimpleMapDrawer;
import com.captainsoft.TADn.party.Game;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemPosition;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.control.MouseButton;
import com.captainsoft.spark.control.ParamCommandAdapter;
import com.captainsoft.spark.i18n.Translator;
import com.captainsoft.spark.ui.SingleBoxUpdater;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.ui.box.UiBox;
import com.captainsoft.spark.ui.drawing.Surface;
import com.captainsoft.spark.ui.mouse.BoxCommandManager;
import com.captainsoft.spark.ui.mouse.BoxMouseClickListener;
import com.captainsoft.spark.ui.mouse.ClickCommand;
import com.captainsoft.spark.ui.mouse.ClickCommandBox;
import com.captainsoft.spark.ui.mouse.DefaultBoxCommandExecuter;
import com.captainsoft.spark.ui.swing.SwingBoxUpdater;
import com.captainsoft.spark.ui_swing.SwingBoxPanel;
import com.captainsoft.spark.utils.Log;



/**
 * The main viewer.
 * 
 * @author mathias fringes
 */
public final class MainViewer {

	// fields

	private final ImageLoader imageLoader;

	private boolean setup = false;
	private ActionWorldController mapViewController;
	private BackpanelBox backpanelBox;
	private BoxCommandManager backgroundMouseCommander;
	private BoxCommandManager overviewBoxCommander;
	private BoxCommandManager mapMouseCommander;
	private SwingBoxUpdater boxUpdateManager;
	private Game game;
	private GameEngine gameEngine;
	private ItemCursor itemCursor;	
	private InventoryBox inventoryBox;
	private LevelMap levelMap;
	private MainViewerWindowManager mainWindowManager;
	private MainWindowSwing mw;
	private OverviewBox overviewBox;
	private PartyMember displayedPartyMember;
	private PartyScroller partyScroller;
	private ScrollBox scrollBox;
	private ScrollText scrollText;
	private SimpleMapDrawer mapDrawer;
	private SimpleMapView mapBox;
	private SwingBoxPanel backgroundPanel;
	private Translator trans;

	// constructors

	public MainViewer(GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
		MainInputController mf = new MainInputController(gameEngine);
		this.mw = new MainWindowSwing(mf);

		this.imageLoader = TadRepo.inst().ImageLoader();		
				
		this.scrollText = new ScrollText();
	
		this.trans = TadRepo.inst().Trans();

		this.mapDrawer = new SimpleMapDrawer(TadRepo.inst().ItemRepo());
		this.mapViewController = new ActionWorldController(gameEngine, this);			
	}

	// accessors

	public PartyMember currentMember() {
		return displayedPartyMember;
	}

	public void setLevelMap(LevelMap map) {
		this.levelMap = map;
		mapDrawer.setLevelMap(levelMap);
	}

	public void vorhangzu() {
		mapDrawer.vorhangZu(new SingleBoxUpdater(mapBox, boxUpdateManager));
		updateBox(mapBox);
	}
		
	// public methods

	public void close() {
		if (mw != null) {
			mw.setVisible(false);
		}
	}

	public void switchGame(Game game) {
		Log.begin("MainViewer:switchGame " + game);

		this.game = game;
		setLevelMap(game.LevelMap());

		setup();

		displayedPartyMember = game.party().detective();
		overviewBox.setParty(game.party());

		updateCoins();
		scrollText.clear();
		updateBox(scrollBox);

		showPartyView();

		backpanelBox.update();

		Log.end("MainViewer:switchGame " + game);
	}

	private void setup() {		
		mw.setMainComponent(backgroundPanel);
		if (setup == true) {
			return;
		}
		Log.info("setting up main viewer.");

		boxUpdateManager = new SwingBoxUpdater();
		setUpBoxes();
		createActions();	

		// swing final box stuff...
		overviewBox.setToTop(inventoryBox);
		MainViewerSwingBoxCreator gb = new MainViewerSwingBoxCreator();
		backgroundPanel = gb.createSwingPanel(backpanelBox, scrollBox, mapBox, overviewBox);
		backgroundPanel.setBackground(Color.BLACK);
		gb.registerCommands(backgroundMouseCommander, mapMouseCommander, overviewBoxCommander);
		gb.registerUpdateManager(boxUpdateManager);

		itemCursor = new ItemCursor(backgroundPanel, TadRepo.inst().ItemRepo());
		mw.setMainComponent(backgroundPanel);	 
		mainWindowManager = new MainViewerWindowManager(boxUpdateManager, mapBox, mapMouseCommander);		
		setup = true;
	}

	private void addDefaultDebugClickListener(BoxCommandManager bd) {
		bd.addBoxClickObserver(new BoxMouseClickListener() {
			@Override
			public void mouseClick(UiBox box, int x, int y, MouseButton button) {
				Log.trace("DEBUGCLICK: " + box + " | " + x + " " + y);
			}
		});
	}

	// ____________________________________________________________________________________________________
	// private build up actions!

	/**
	 * Creates the actions for boxes
	 */
	private void createActions() {
		//
		// background
		backgroundMouseCommander = new BoxCommandManager(backpanelBox, new DefaultBoxCommandExecuter());
		addDefaultDebugClickListener(backgroundMouseCommander);
		backgroundMouseCommander.getDefaultList().setClickCmd(backpanelBox.diskBox,
				new ClickCommandBox() {
					@Override
					public void click(UiBox box, MouseButton button) {
						gameEngine.showDiskMenu();
					}
				});
		//
		// overview interface
		overviewBoxCommander = new BoxCommandManager(overviewBox, new DefaultBoxCommandExecuter());
		addDefaultDebugClickListener(overviewBoxCommander);
		overviewBoxCommander.getDefaultList().setClickCmd(overviewBox.showInvBox,
				new ClickCommandBox() {
					@Override
					public void click(UiBox box, MouseButton button) {
						switchMember(displayedPartyMember);
					}
				});
		overviewBoxCommander.getDefaultList().setBothCmd(new UiBox(overviewBox, 18, 75, 88, 184), new SwitchMember(this, 1));
		overviewBoxCommander.getDefaultList().setBothCmd(new UiBox(overviewBox, 115, 75, 88, 184), new SwitchMember(this, 2));
		overviewBoxCommander.getDefaultList().setBothCmd(new UiBox(overviewBox, 18, 302, 88, 184), new SwitchMember(this, 3));
		overviewBoxCommander.getDefaultList().setBothCmd(new UiBox(overviewBox, 115, 302, 88, 184), new SwitchMember(this, 4));

		//
		// inventory commands
		//
		// equiq/skill toggleBox
		overviewBoxCommander.getDefaultList().setClickCmd(inventoryBox.invViewToggleBox,
				new ClickCommandBox() {
					@Override
					public void click(UiBox box, MouseButton button) {
						inventoryBox.toggleSkillEquiq();
						Log.trace("toggle equiq/skill view");
						updateBox(inventoryBox.equiqBox);
						updateBox(inventoryBox.skillBox);
					}
				});
		//
		// switch member quick boxes
		for (int i = 0; i < 4; i++) {
			overviewBoxCommander.getDefaultList().setCmd(inventoryBox.quickMemberBoxes[i], new SwitchMember(this, i + 1));
		}
		//
		// back to overview
		overviewBoxCommander.getDefaultList().setClickCmd(new UiBox(inventoryBox.inventoryCtrlBox, 116, 2, 105, 25),
				new ClickCommandBox() {
					@Override
					public void click(UiBox box, MouseButton button) {
						showPartyView();
					}
				});

		// inventory item handling
		final UseInventoryItemCommand useItemBoxCmd = new UseInventoryItemCommand(gameEngine);
		TakeDropInventoryItemCommand takeDropItemCmd = new TakeDropInventoryItemCommand(gameEngine, trans);
		for (int i = 0; i < inventoryBox.allItemBoxes.size(); i++) {
			// use item
			final ItemBox b = inventoryBox.allItemBoxes.get(i);
			overviewBoxCommander.getDefaultList().setCmd(b, new Command() {
				public void execute() {
					gameEngine.eventCommand(new ParamCommandAdapter<ItemBox>(useItemBoxCmd, b));
				}
			});
			// move (take, drop) item
			overviewBoxCommander.getDefaultList().setRightCmd(b, new ParamCommandAdapter<ItemBox>(takeDropItemCmd, b));
		}

		//
		// map
		mapMouseCommander = new BoxCommandManager(mapBox, new DefaultBoxCommandExecuter());
		addDefaultDebugClickListener(mapMouseCommander);
		mapMouseCommander.getDefaultList().setClickCmd(mapBox, new ClickCommand() {
			@Override
			public void click(final UiBox box, final int x, final int y, final MouseButton button) {
				final Position p = mapDrawer.getPosAt(x, y);
				gameEngine.eventCommand(new Command() {
					@Override
					public void execute() {
						mapViewController.clickMapPosition(p, button);
					}
				});
			}
		});

	}

	/**
	 * Creates the UiBoxes.
	 */
	private void setUpBoxes() {
		//
		// background
		final Surface interfaceBackgroundSurface = imageLoader.load("ifc", 28);
		// 
		backpanelBox = new BackpanelBox(interfaceBackgroundSurface);
		//
		// overview interface
		overviewBox = new OverviewBox(interfaceBackgroundSurface);
		//
		// inventory (member overview) Box
		inventoryBox = new InventoryBox(imageLoader, overviewBox.height);
		inventoryBox.carryString = trans.word("inventory.carry");
		overviewBox.add(inventoryBox);
		//
		// map
		mapBox = new SimpleMapView(mapDrawer);
		mapBox.createSurface();
		partyScroller = new PartyScroller(gameEngine, mapDrawer, new SingleBoxUpdater(mapBox, boxUpdateManager));
		//
		// text scroller
		Surface scrollBackImage = interfaceBackgroundSurface.stamp(15, 512, 681, 91);
		scrollBox = new ScrollBox(scrollBackImage, scrollText);		
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
		itemCursor.item(item);	
	}

	public void toInventory(ItemPosition pos, Item item) {
		if (pos.member == displayedPartyMember) {
			ItemBox box = inventoryBox.findItemBox(pos);
			box.item(item);
			if (inventoryBox.visible()) {
				updateBox(box);
				updateWeight();
				updateProtect();
			}
		}
	}

	public void switchMember(int memberNo) {
		switchMember(game.party().member(memberNo));
	}

	private void showPartyView() {
		inventoryBox.visible(false);
		updateBox(inventoryBox.parent());
	}

	private void switchMember(PartyMember member) {
		if (mainWindowManager.lenientShown()) {
			return;
		}
		if (!overviewBox.visible() && displayedPartyMember == member) {
			return;
		}
		Log.call("switchMember");
		displayedPartyMember = member;
		inventoryBox.member(displayedPartyMember);
		updateWeight();
		updateProtect();
		inventoryBox.visible(true);

		for (int i = 0; i < 4; i++) {
			inventoryBox.quickMemberBoxes[i].selected = (member.nr() - 1 == i);
		}
		updateBox(inventoryBox);
		//
		// switch member on map
		mapDrawer.setPartyMemberPic(member.nr());
		mapDrawer.retile(game.party().position().addY(-1));
		mapDrawer.retile(game.party().position());
		boxUpdateManager.updateBoxDrawing(mapBox);

		Log.end("switchMember");
	}

	public void moveParty(Direction d, Position position) {
		mapDrawer.setFaceDirection(d);
		mapDrawer.moveTo(position);
		updateBox(mapBox);
	}

	public void scrollParty(Direction d, Position p) {
		partyScroller.scrollParty(d, p);
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
		backpanelBox.moneyBox.text = game.party().coins() + "";
		updateBox(backpanelBox.moneyBox);
	}

	public void updateWeight() {
		int wgt = displayedPartyMember.inventory().weight();
		int max = displayedPartyMember.maxWgt();
		inventoryBox.updateWeigthBox(wgt, max);
		updateBox(inventoryBox.weightBox);
	}

	public void updateProtect() {
		// TODO translate
		inventoryBox.equiqBox.protectBox.text = "Schutz:  " + displayedPartyMember.protect();
		updateBox(inventoryBox.equiqBox.protectBox);
	}

	public synchronized void showWindow(WindowController wc) {
		if (wc.isLenientModal()) {
			showPartyView();
		}
		mainWindowManager.showWindow(wc);
	}

	public void closeWindows() {
		if (mw.isVisible()) {
			mainWindowManager.closeWindows();
		}
	}

	public synchronized boolean windowShown() {
		return mainWindowManager.windowShown();		
	}

	public void updatePartyName(String name) {
		overviewBox.partyName.text = name;
		updateBox(overviewBox);
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

	public void updatePosition(Position position) {
		boolean didUpdate = mapDrawer.retile(position);
		if (didUpdate) {
			boxUpdateManager.updateBoxDrawing(mapBox);
		}
	}

	public void updateFunBars(PartyMember member) {
		if (inventoryBox.visible()) {
			if (displayedPartyMember == member) {
				updateSkillView(member);
			}
		} else {
			updateBox(overviewBox.getBarBox(member.nr()));
		}
	}

	public void updateSkillView(PartyMember member) {
		if (inventoryBox.visible() && displayedPartyMember == member) {
			inventoryBox.skillBox.updateValues(member);
			updateBox(inventoryBox.skillBox);
		}
	}

	public void updateItemBox(ItemPosition itemPosition, Item item) {
		if (inventoryBox.visible() && displayedPartyMember == itemPosition.member) {
			UiBox b = inventoryBox.updateItemBox(itemPosition, item);
			updateBox(b);
			updateWeight();
		}
	}

	public void removeParty() {
		this.mapDrawer.centerPartyPosition(null);
	}

	public void showIntro(final Game game) {
		new ShowIntroCommand(this, gameEngine).execute();
	}

	public void setBackgroundPanel(Component c) {
		mw.setMainComponent(c);
	}

	public void showOutro(Game game2) {
		new ShowOutroCommand(this, gameEngine).execute();
	}

}
