/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine;

import java.io.IOException;

import com.captainsoft.TADr.TadExceptionHandler;
import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.loader.GameLoader;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.TADr.loader.ItemRepository;
import com.captainsoft.TADr.loader.MapLoader;
import com.captainsoft.TADr.loader.MonsterLoader;
import com.captainsoft.TADr.loader.PuzzleLoader;
import com.captainsoft.TADr.loader.java.JavaGameLoader;
import com.captainsoft.TADr.loader.vb.VbImageLoader;
import com.captainsoft.TADr.loader.vb.VbItemRepository;
import com.captainsoft.TADr.loader.vb.VbMapLoader;
import com.captainsoft.TADr.loader.vb.VbMonsterLoader;
import com.captainsoft.TADr.loader.vb.VbPuzzleLoader;
import com.captainsoft.TADr.model.i18n.TadTranslator;
import com.captainsoft.TADr.model.i18n.EnglishMessages;
import com.captainsoft.TADr.model.i18n.GermanMessages;
import com.captainsoft.TADr.model.i18n.GuiMessages;
import com.captainsoft.TADr.sound.SndPlayer;
import com.captainsoft.TADr.sound.TadSafeSndPlayer;
import com.captainsoft.TADr.sound.VbSndPlayer;

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
	
	// public
	
	public void setUp() throws IOException {		
		//
        settings = new Settings();
        try {
            settings.load();
        } catch (IOException e) {
            TadExceptionHandler.errorMessageAndMenu("Cannot load settings!", e);
        }
        //
		imageLoader = new VbImageLoader();
		itemRepo = new VbItemRepository(imageLoader);
		itemRepo.load();		
		puzzleLoader = new VbPuzzleLoader();
		monsterLoader = new VbMonsterLoader();
		vbSndPlayer = new TadSafeSndPlayer(new VbSndPlayer());
        vbSndPlayer.enabled(settings.playSounds);
		//
		mapLoader = new VbMapLoader();
		gl = new JavaGameLoader(mapLoader);
		//
		GuiMessages messages = messages();
		translator = new TadTranslator(messages);
		gameEngine = GameEngine.instance();				
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
		if (TadLang.isEnglish()) {
			return new EnglishMessages();
		} else {
			// default
			return new GermanMessages();	
		}
	}

}