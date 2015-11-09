/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine;

import com.captainsoft.TADr.engine.controller.MoveKeysInputController;
import com.captainsoft.TADr.engine.loop.RenderLoop;
import com.captainsoft.TADr.engine.loop.RenderThread;
import com.captainsoft.TADr.engine.path.PathAlgo;
import com.captainsoft.TADr.gui.MainViewer;
import com.captainsoft.TADr.loader.MapLoader;
import com.captainsoft.TADr.loader.vb.VbMapLoader;
import com.captainsoft.TADr.model.Direction;
import com.captainsoft.TADr.model.Game;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.fight.Fight;
import com.captainsoft.TADr.model.fight.FightWindowController;
import com.captainsoft.TADr.model.i18n.TadTranslator;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.Tile;
import com.captainsoft.TADr.model.party.ItemPosition;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.TADr.model.puzzle.PuzzleEngine;
import com.captainsoft.TADr.model.puzzle.TileUpdate;
import com.captainsoft.TADr.sound.SndFacade;
import com.captainsoft.spark.command.Command;
import com.captainsoft.spark.i18n.TrKey;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.render.ControlAnimation;
import com.captainsoft.spark.ui.WindowController;
import com.captainsoft.spark.utils.Log;

import java.util.List;

/**
 * Game engine responsible for handling the game state and 
 * party movement, tile action etc.
 *
 * @author mathias fringes
 */
public final class GameEngine {
			
	// fields
	
	private final ItemEngine itemEngine;
	private final MainViewer mainViewer;	
	private final MapLoader mapLoader;
    private final MoveKeysInputController mvic;
	private final PuzzleEngine puzzleEngine;		
	private final TadTranslator trans;	
	private final TileEngine tileEngine;
			
	private Game game;
	private LevelMap levelMap;
	private Party party;
	private RenderLoop renderLoop;

    // static
	
	private static GameEngine instance;
	
	public static GameEngine instance() {
		if (instance == null) {
			instance = new GameEngine();
		}
		return instance;
	}
	
	// constructors
	
	private GameEngine() {
		super();		
		this.mainViewer = new MainViewer(this);					
		this.mapLoader = new VbMapLoader();
		this.puzzleEngine = new PuzzleEngine(this);

        this.mvic = new MoveKeysInputController(this);
		
		this.trans = TadRepo.inst().Trans();		
		this.itemEngine = new ItemEngine();
		this.tileEngine = new TileEngine(this);
		
		this.renderLoop = new RenderLoop(this);		
		this.renderLoop.accept(true);
		RenderThread renderThread = new RenderThread(this.renderLoop);
		renderThread.start();		
	}
	
	//
	// game control
	
	public void startGame(Game game) {
		Log.log("Starting game " + game);
		this.game = game;
		this.party = game.party();			
		this.levelMap = game.LevelMap();
						
		closeWindows();
		 
		mainViewer.showWindow();
		if (game.isNewGame()) {
			mainViewer.showIntro();
            mainViewer().mapDrawer.paintingInfo.setPartyMemberPic(2);
		} else {
			startPlaying();
		}	
	}
	
	public void startPlaying() {	
		Log.log("Start playing....");
		mainViewer.switchGame(game);		
		if (game.isNewGame()) {			
			setPartyTo(party.position());
            sayAnonym("press.f1.forHelp");
		} else {
			mainViewer.setPartyTo(Direction.Here, party.position());
		}		
		renderLoop.setMapAnimations(mainViewer.currentAnimations());
		sayRnd("gameload.welcome");
		renderLoop.accept(true);
        Log.end("startGame");
	}
		
	public void reset() {
		game = null;	
		party = null;
		levelMap = null;	
		mainViewer.itemCursor(null);
		renderLoop.clear();
		closeWindows();
	}	
	
	public void close() {
		reset();
		mainViewer.close();
	}
	
	//
	// state
	
	public boolean canShowDiskMenu() {
		return (!mainViewer.windowShown() && (itemInHand() == null) && renderLoop.accept());
	}

	public void directCommand(Command c) {
		renderLoop.command(c);
	}
	
	/**
	 * The command will get executes as soon as the party completely moved to the next tile. Nothing will happen
	 * if there is a tile action on that tile.
	 * 
	 * @param command
	 */
	public void nextCommand(Command command) {		
		renderLoop.nextCommand(command);
	}
	
	public void settingsHaveChanged() {
		if (game != null) {
			mainViewer.updatePartyName(game.party().name());
		}
	}	
	
	//
	// movement		
	
	public void goPartyGo(Direction d) {
		Position p = party.position().apply(d);
		goPartyGo(p, false);
	}
	
	public void goPartyGo(Position position, boolean doMeeh) {
		if (mainViewer.windowShown()) {
			return;
		}
		stopParty();	
		if (!party.position().equals(position)) {
			List<Position> path = new PathAlgo(levelMap, party.position(), position).find();			
			if (path.size() == 0) {					
                if (doMeeh) {
                    meeeehParty();
                }
			} else {
				renderLoop.walkTo(path);
			}
		}
	}

    public void goPartyTryToGoThere(Position position, Command arriveCommand) {
        if (mainViewer.windowShown()) {
            return;
        }
        stopParty();
        List<Position> path = new PathAlgo(levelMap, party.position(), position).find(true);
        if (path.size() != 0) {
            renderLoop.walkToAndThen(path, arriveCommand);
        } else {
            meeeehParty();
        }
    }


    public void pollMoveKeys() {
        int keyCode = mainViewer.keyState.firstKeyCode();

        mvic.keyPress(keyCode);
    }

	public boolean arrivedAt(Position position) {
		if (position.equals(party.position())) {
			return false;
		}
		party.position(position);
		renderLoop.setMapAnimations(mainViewer.currentAnimations());
		return tileEngine.doTileAction();
	}
		
	public void stopParty() {
		renderLoop.walkTo(null);		
	}

    public void meeeehParty() {
        stopParty();
        renderLoop.playPartyAnimation(mainViewer.createCannotWalkAnimation());
    }
	
	public void teleportParty(final int mapNr, final Position position) {	
	
		renderLoop.accept(false);
		stopParty();		
		stopAllAnimations();
		
		mainViewer.removeParty();
		if (mapNr == party.mapNumber() || mapNr == 0) {
			Log.info("moving party on same map: " + position);
			setPartyTo(position);
			renderLoop.accept(true);
			return;		
		}
		Log.info("moving party to new map: " + mapNr);
				
		Animation zu = mainViewer.vorhangzu();
		Animation m = new ControlAnimation(zu) {
			@Override
			protected void after() {
				mapLoader.save(levelMap);		
				party.mapNumber(mapNr);		
				levelMap = mapLoader.load(party.mapNumber());
				//		
				game.LevelMap(levelMap);		
				mainViewer.setLevelMap(levelMap);		
				setPartyTo(position);	
				renderLoop.accept(true);
			}		
		};
		
		addAnimation(m);		
	}
	
	//
	// party handling methods
	
	public void takeItem(Item item) {
		if (item != null) {
			if (item.isMoney()) {
				addCoins(item.value());
				mainViewer.itemCursor(null);	
			} else {
				String text = itemEngine.sayItemTakeString(item); 
				mainViewer.scrollCurrent(text);							
				mainViewer.itemCursor(item);
			}
		} else {
			mainViewer.itemCursor(null);
		}
	}
			
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
	
	public void reExecuteTileAction() {
		// for instance with active puzzles (events)
		tileEngine.doTileAction();
	}
	
	//
	// view and update methods

    public void addAnimation(Animation a) {
        renderLoop.addAnimation(a);
    }

    public void stopAllAnimations() {
        renderLoop.clear();
    }

    public void updateTile(TileUpdate ... tileUpdates) {
		Position[] positions = new Position[tileUpdates.length];
		
		for (int i = 0; i < tileUpdates.length; i++) {
			TileUpdate tileUpdate = tileUpdates[i];
			Tile tile = levelMap().tile(tileUpdate.position());
			if (tileUpdate.values().applyTo(tile)) {
				 positions[i] = tileUpdate.position();
			}			 
		}
		
		updateTile(positions);
	}
	
	public void updateTile(Position ... positions) {
		 mainViewer.updatePosition(positions);
	}
	
	public void updateFightTile(Position position) {
		mainViewer.updateFightTile(position);		
		renderLoop.setMapAnimations(mainViewer.currentAnimations());
		updateTile(position, position.addY(-1), position.addY(-2));
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
		mainViewer.showOutro();			
	}
	
	public void sayRnd(String trKey, String ... data) {		
		int nr = party.randomMemberNr();
		mainViewer.scroll(nr, trans.word(trKey + "." + nr, (Object[])data));
	}
					
	//
	// private
	
	private boolean setPartyTo(Position position) {
		party.position(position);		
		mainViewer.setPartyTo(Direction.Here, position);
		renderLoop.setMapAnimations(mainViewer.currentAnimations());
		return tileEngine.doTileAction();
	}
	
	private PartyMember currentMember() {
		return mainViewer.currentMember();
	}
	
	void fight(Integer[] mo, Position fightPosition) {
		FightWindowController fw = new FightWindowController(this, new Fight(party, fightPosition, mo[0], mo[1], mo[2], mo[3], mo[4]));
		showWindow(fw);
		fw.beginFight();
	}

	// public accessors
	
	public MainViewer mainViewer() {
		return mainViewer;
	}
	
	public Party party() {
		return party;
	}			
	
	public LevelMap levelMap() {
		return levelMap;
	}
	
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
		return TadRepo.inst().Settings();
	}

}