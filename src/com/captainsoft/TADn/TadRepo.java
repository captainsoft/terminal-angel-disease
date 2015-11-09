/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn;

import java.io.IOException;

import com.captainsoft.TADn.engine.GameEngine;
import com.captainsoft.TADn.loader.GameLoader;
import com.captainsoft.TADn.loader.ImageLoader;
import com.captainsoft.TADn.loader.ItemRepository;
import com.captainsoft.TADn.loader.MapLoader;
import com.captainsoft.TADn.loader.MonsterLoader;
import com.captainsoft.TADn.loader.PuzzleLoader;
import com.captainsoft.TADn.loader.java.JavaGameLoader;
import com.captainsoft.TADn.loader.vb.VbImageLoader;
import com.captainsoft.TADn.loader.vb.VbItemRepository;
import com.captainsoft.TADn.loader.vb.VbMapLoader;
import com.captainsoft.TADn.loader.vb.VbMonsterLoader;
import com.captainsoft.TADn.loader.vb.VbPuzzleLoader;
import com.captainsoft.TADn.model.TadTranslator;
import com.captainsoft.TADn.model.i18n.EnglishMessages;
import com.captainsoft.TADn.model.i18n.GermanMessages;
import com.captainsoft.TADn.model.i18n.GuiMessages;
import com.captainsoft.TADn.sound.SndPlayer;
import com.captainsoft.TADn.sound.TadSafeSndPlayer;
import com.captainsoft.TADn.sound.VbSndPlayer;
import com.captainsoft.spark.utils.Log;

/**
 * Important low level services for the TAD. 
 *
 * @author mathias fringes
 */
public final class TadRepo {
	
	// static
	
	private static TadRepo instance;

	public static TadRepo inst() {
		if (instance == null) {
			instance = new TadRepo();
		}
		return instance;
	}
	
	// fields
	
	private GameEngine gameEngine;
	private GameLoader gl;
	private ImageLoader imageLoader;
	private MonsterLoader monsterLoader; 
	private PuzzleLoader puzzleLoader;
	private Settings settings;	
	private SndPlayer vbSndPlayer;
	private TadTranslator translator;
	private VbItemRepository itemRepo;
	private VbMapLoader mapLoader;
	
	// constructors
	
	private TadRepo() {
		super();		
	}
	
	// public methods
	
	public void setUp() throws IOException {
		settings = new Settings();		
		settings.load();
		//
		imageLoader = new VbImageLoader();
		itemRepo = new VbItemRepository(imageLoader);
		itemRepo.load();		
		puzzleLoader = new VbPuzzleLoader();
		monsterLoader = new VbMonsterLoader();
		//
		vbSndPlayer = new TadSafeSndPlayer(new VbSndPlayer());
		vbSndPlayer.enabled(settings.playSounds);
		//
		mapLoader = new VbMapLoader();
		gl = new JavaGameLoader(mapLoader);
		
		GuiMessages messages = messages();
		translator = new TadTranslator(messages);
		gameEngine = new GameEngine();				
		translator.setup(gameEngine);
	}
	
	// accessors	
	
	public GameLoader GameLoader() {
		return gl;
	}
	
	public GameEngine GameEngine() {
		return gameEngine;
	}
	
	public ItemRepository ItemRepo() {
		return itemRepo;
	}	
	
	public ImageLoader ImageLoader() {
		return imageLoader;
	}
	
	public MapLoader MapLoader() {
		return mapLoader;
	}
	
	public MonsterLoader MonsterLoader() {
		return monsterLoader;
	}
	
	public PuzzleLoader puzzleLoader() { 
		return puzzleLoader;
	}

	public Settings Settings() {
		return settings;
	}

	public SndPlayer SndPlayer() {
		return vbSndPlayer;
	}

	public TadTranslator Trans() {
		return translator;
	}	

	// private

	private GuiMessages messages() {
		String folder = TadLang.folder();
		Log.log("setting language to: " + folder);
		if (folder.contains("_en")) {
			return new EnglishMessages();
		} else {
			// default
			return new GermanMessages();	
		}
	}

}