/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.cuts;

import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.loader.ImageLoader;
import com.captainsoft.spark.cuts.Scene;
import com.captainsoft.spark.cuts.SceneDSL;
import com.captainsoft.spark.i18n.PrefixDecorator;
import com.captainsoft.spark.i18n.Translator;
import com.captainsoft.spark.render.Animation;
import com.captainsoft.spark.ui.Updater;
import com.captainsoft.spark.ui.box.UiBoxContainer;
import com.captainsoft.spark.ui.drawing.Surface;

/**
 * Base class for cut scenes.
 *
 * @author mathias fringes
 */
public abstract class CutScene {
	
	// fields
	
	protected final ImageLoader imageLoader;
	protected final UiBoxContainer backgroundBox;
	protected final Updater updater;
	protected final Translator trans;	

	// constructors
	
	public CutScene(Updater updater, UiBoxContainer backgroundBox, String translatorPrefix) {
		super();
		this.updater = updater;
		this.backgroundBox = backgroundBox;
		this.imageLoader = TadRepo.inst().ImageLoader();
		this.trans = new PrefixDecorator(TadRepo.inst().Trans(), translatorPrefix);
	}
	
	// protected

	protected final SceneDSL createNewScene() {
		return createNewScene("");
	}

	protected SceneDSL createNewScene(String name) {
		Scene scene = new Scene(updater, backgroundBox);
		scene.name = name;
		return scene.createDsl();
	}

	protected final Surface image(int index) {
		return new Surface(imageLoader.load(imageLoaderIdentifier(), index));	
	}

	protected final String word(String key) {
		return trans.word(key);
	}
	
	protected final String[] wordList(int count, String code) {
		String[] texts = new String[4];
		for (int i = 0; i < count; i++) {
			texts[i] = word(code + "." + (i+1));
		}			
		return texts;
	}
	
	// abstract
	
	public abstract Animation createAnimation(int index);	
	
	public abstract String imageLoaderIdentifier();

}