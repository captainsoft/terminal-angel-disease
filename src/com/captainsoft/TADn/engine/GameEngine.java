/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.engine;
 
import java.util.List;

import com.captainsoft.TADn.Debugger;
import com.captainsoft.TADn.Settings;
import com.captainsoft.TADn.TadExceptionHandler;
import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn._render.RenderLoop;
import com.captainsoft.TADn._render.RenderThread;
import com.captainsoft.TADn.engine.commands.QuickSaveCommand;
import com.captainsoft.TADn.engine.commands.ShowDiskCommand;
import com.captainsoft.TADn.engine.path.PathAlgo;
import com.captainsoft.TADn.fight.Fight;
import com.captainsoft.TADn.fight.FightWindowController;
import com.captainsoft.TADn.gui.MainViewer;
import com.captainsoft.TADn.loader.MapLoader;
import com.captainsoft.TADn.loader.vb.VbMapLoader;
import com.captainsoft.TADn.model.Direction;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.model.TadTranslator;
import com.captainsoft.TADn.party.Game;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.ItemPosition;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.Party;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.TADn.party.Tile;
import com.captainsoft.TADn.puzzle.PuzzleEngine;
import com.captainsoft.TADn.puzzle.TileUpdate;
import com.captainsoft.TADn.sound.SndFacade;
import com.captainsoft.spark.collect.Clist;
import com.captainsoft.spark.control.Command;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.utils.Log;

/**
 * Game engine responsible for handling the game state and 
 * party movement, tile action etc.
 *
 * @author mathias fringes
 */
public final class GameEngine {
			
	// fields
			
	private final WalkCommandDispatcher dispatcher;
	private final ItemEngine itemEngine;
	private final MainViewer mainViewer;	
	private final MapLoader mapLoader;	
	private final PuzzleEngine puzzleEngine;
	private final Settings settings;		
	private final TadTranslator trans;	
	private final TileEngine tileEngine;
		
	private Game game;
	private GameState state = GameState.OTHER;
	private LevelMap levelMap;
	private Party party;
	private RenderLoop renderLoop;
	private RenderThread renderThread;
	
	// constructors
	
	public GameEngine() {
		super();		
		this.mainViewer = new MainViewer(this);					
		this.mapLoader = new VbMapLoader();
		this.puzzleEngine = new PuzzleEngine(this);	
		this.settings = TadRepo.inst().Settings();		
		this.trans = TadRepo.inst().Trans();
		this.dispatcher = new WalkCommandDispatcher(this);	
		this.itemEngine = new ItemEngine(this);
		this.tileEngine = new TileEngine(this);	
	}
	
	//
	// game state methods
	
	public synchronized GameState getState() {
		return state;
	}
	
	public synchronized void setState(GameState state) {
		this.state = state;
	}
	
	public void startGame(Game game) {
		Log.info("Starting game " + game);
		this.game = game;
		this.party = this.game.party();			
		this.levelMap = game.LevelMap();
				
		Debugger.inst.startGameCheat(party, levelMap);	
		closeWindows();
		
		if (this.renderThread != null) {
			this.renderThread.kill();
		}
		
		mainViewer.showWindow();
		if (game.isNewGame()) {
			mainViewer.showIntro(game);					
		} else {
			doStart();
		}	
	}
	
	public void doStart() {		
		mainViewer.switchGame(game);		
		if (game.isNewGame()) {
			movePartyInternal(party.position(), Direction.Here);
		} else {
			mainViewer.moveParty(Direction.Here, party.position());
		}
		setState(GameState.PLAYING);
		
		
		this.renderLoop = new RenderLoop();
		this.renderThread = new RenderThread(this.renderLoop);
		
		sayRnd("gameload.welcome");	
		Log.end("startGame");		
	}
	
	public void sayRnd(String trKey, String ... data) {		
		int nr = party.randomMemberNr();
		mainViewer.scroll(nr, trans.word(trKey + "." + nr, (Object[])data));
	}
	
	public void reset() {
		game = null;	
		party = null;
		levelMap = null;
		setState(GameState.OTHER);
	}	
	
	public void close() {
		reset();
		mainViewer.close();
	}
	
	public void settingsHaveChanged() {
		if (game != null) {
			mainViewer.updatePartyName(game.party().name());
		}
	}		
	
	//
	// movement		
	
	public void goPartyGo(Direction d) {
		Position p = this.party.position().apply(d);
		goPartyGo(p);		
	}
	
	public synchronized void goPartyGo(Position position) {
		if (mainViewer.windowShown()) {
			return;
		}
		stopParty();	
		if (party.position().equals(position)) {				
			dispatcher.go(new Clist<Position>(position));			
		} else {		
			List<Position> path = new PathAlgo(levelMap, party.position(), position).find();
			if (path.size() == 0) {				
				dispatcher.go(null);
			} else {
				dispatcher.go(path);
			}
		}
	}
		
	public synchronized boolean scrollParty(Position p) {			
		Direction d = party.position().findDir(p);		
		party.position(p);		
		mainViewer.scrollParty(d, p);	
		if (d == Direction.Here) {
			return false;
		} else {
			return tileEngine.doTileAction();
		}
	}	
		
	public void stopParty() {
		dispatcher.stop();
	}
	
	public synchronized void teleportParty(int mapNr, Position position) {	
		stopParty();
		mainViewer.removeParty();
		if (mapNr == party.mapNumber() || mapNr == 0) {
			Log.info("moving party on same map: " + position);
			movePartyNoScroll(position);
			return;		
		}
		Log.info("moving party to new map: " + mapNr);
		mainViewer.vorhangzu();
		mapLoader.save(levelMap);		
		party.mapNumber(mapNr);		
		levelMap = mapLoader.load(party.mapNumber());
		//		
		game.LevelMap(levelMap);		
		mainViewer.setLevelMap(levelMap);		
		movePartyNoScroll(position);		
	}
	
	private boolean movePartyNoScroll(Position position) {
		try {
			Direction d = Direction.Here;			
			return movePartyInternal(position, d);
		} catch(Exception e) {
			TadExceptionHandler.errorMessageAndMenu("Fatal error", e);
			return false;
		}
	}
				
	public void shakeHead() {
		mainViewer.scrollParty(Direction.Nowhere, null);
	}
	
	public void takeItem(Item item) {
		if (item != null) {
			if (item.isMoney()) {
				addCoins(item.value());
				mainViewer.itemCursor(null);	
			} else {						
				itemEngine.sayItemTakeString(item);				
				mainViewer.itemCursor(item);
			}
		} else {
			mainViewer.itemCursor(item);			
		}
	}
		
	//
	// party handling methods
	
	public void addCoins(int coins) {	
        SndFacade.coinsSound();
		party.addCoins(coins);
		mainViewer.updateCoins();
	}
	
	public void deleteItem(Item item) {
		Item cursorItem = mainViewer.itemInHand();
		if (item.equals(cursorItem)) {
			takeItem(null);
		} else {
			ItemPosition ip = party.inventory().get(item.id());
			deleteItem(ip);
		}
	}
	
	public void deleteItem(ItemPosition itemPosition) {
		if (itemPosition != null) {
			party.inventory().delete(itemPosition);			
			mainViewer.updateItemBox(itemPosition, null);			
		}			
	}	
	
	public void reexecuteTileAction() {
		// for instance with active puzzles (events)
		tileEngine.doTileAction();
	}
	
	//
	// view methods
	
	public void updateTile(TileUpdate tileUpdate) {
		 Tile tile = levelMap().tile(tileUpdate.position());
		 if (tileUpdate.values().applyTo(tile)) {
			 updateTile(tileUpdate.position());
		 }
	}
	
	public void updateTile(Position position) {
		 mainViewer.updatePosition(position);
	}
	
	public void showWindow(WindowController wc) {			
		mainViewer.showWindow(wc);
	}	
	
	public void closeWindows() {		
		mainViewer.closeWindows();
	}	
	
	public void say(String key, Object ... data) {
		say(currentMember(), key, data);
	}
	
	public void say(PartyMember member, String key, Object ... data) {
		String text = trans.word(new TrKey(key, data).variant(member.nr()));
		mainViewer.scroll(member.nr(), text);		
	}	
	
	public void sayAnonym(String key, Object ... data) {
		String text = trans.word(new TrKey(key, data));
		mainViewer.scroll(text);
	}
	
	public void sayAsIs(String text) {
		mainViewer.scroll(text);
	}
	
	public void sayCurrent(String key) {
		String text = trans.word(key);
		mainViewer.scroll(currentMember().nr(), text);
	}	
	
	public void showOutro() {		
		mainViewer.showOutro(game);			
	}
					
	//
	//
	//
	// private
	
	private boolean movePartyInternal(Position position, Direction d) {
		party.position(position);		
		mainViewer.moveParty(d, position);		
		return tileEngine.doTileAction();
	}
	
	void fight(Integer[] mo, Position poition) {
		FightWindowController fw = new FightWindowController(this, new Fight(party, mo[0],mo[1],mo[2],mo[3],mo[4]));
		showWindow(fw);		
		fw.beginFight();
	}

	private PartyMember currentMember() {
		return mainViewer.currentMember();
	}	
	
	// public dirty accessors
	
	public MainViewer mainViewer() {
		return mainViewer;
	}
	
	public Party party() {
		return party;
	}			
	
	public LevelMap levelMap() {
		return levelMap;
	}

	/**
	 * Gets the current game.
	 * 
	 * @return
	 */
	public Game game() {
		return game;
	}

	public PuzzleEngine puzzleEngine() {
		return puzzleEngine;		
	}
	
	public TileEngine tileEngine() {
		return tileEngine;		
	}
	
	public ItemEngine itemEngine() {
		return itemEngine;
	}
	
	public Item itemInHand() {	
		return mainViewer.itemInHand();
	}

	public Settings settings() {
		return settings;
	}

	public void showDiskMenu() {
		eventCommand(new ShowDiskCommand(this));
	}
	
	public void eventCommand(Command command) {
		dispatcher.addNext(command);
	}

	public void handleException(Exception e) {
		TadExceptionHandler.errorMessageAndMenu("Fatal error", e);		
	}

	public void quickSave() {		
		eventCommand(new QuickSaveCommand(this));
	}
	
}