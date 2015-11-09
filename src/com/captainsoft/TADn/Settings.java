/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn;

import static com.captainsoft.spark.convert.Converter.fromBoolean;
import static com.captainsoft.spark.convert.Converter.fromFloat;
import static com.captainsoft.spark.convert.Converter.minLength;
import static com.captainsoft.spark.convert.Converter.quetsch;
import static com.captainsoft.spark.convert.Converter.toBoolean;
import static com.captainsoft.spark.convert.Converter.toFloat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.captainsoft.spark.files.LineFile;
import com.captainsoft.spark.utils.Log;
import com.captainsoft.spark.utils.Truth;

/**
 * Settings for the TAD.
 *
 * @author mathias fringes
 */
public final class Settings {	
	
	// static
	
	private final static String filename = "files/sav/options.dat";
	
	// fields
	
	public boolean playMusic = true;
	public boolean playSounds = true;	
	public boolean scrollMap = true;
	public boolean scrollParty = true;
	public float scrollFactor = 1;	
	private String nameOfParty = "";
	private String nameOfPlayer = "";
	
	// constructors
	
	public Settings() {
		super();
	}
	
	// accessors			

	public String nameOfPlayer() {
		return nameOfPlayer;
	}

	public void nameOfPlayer(String nameOfPlayer) {
		if (minLength(nameOfPlayer, 1)) {
			this.nameOfPlayer = quetsch(nameOfPlayer, 25);
		}
	}

	public String nameOfParty() {
		return nameOfParty;
	}

	public void nameOfParty(String nameOfParty) {
		if (minLength(nameOfPlayer, 1)) {
			this.nameOfParty = quetsch(nameOfParty, 25);
		}
	}	
	
	public boolean isNewInstall() {
		return (!(Truth.is(nameOfParty) && Truth.is(nameOfPlayer)));
	}	
	
	// input output
	
	public void save() throws IOException {
		Log.info("saving settings");		
		List<String> f = new ArrayList<String>();
		f.add(nameOfPlayer);
		f.add(nameOfParty);
		f.add(fromBoolean(playMusic));
		f.add(fromBoolean(playSounds));
		f.add(fromFloat(scrollFactor));		
		LineFile.save(filename, f);
	}
	
	public void load() throws IOException {
		Log.info("loading settings");
		List<String> f = new ArrayList<String>();
		try {
			f = LineFile.load(filename);
		} catch (IOException e) {
			Log.log("Cannot open new settings file " + filename + " " + e.getMessage());		
		}
		nameOfPlayer(easy(f, 0));
		nameOfParty(easy(f, 1));
		playMusic = toBoolean(easy(f, 2));
		playSounds = toBoolean(easy(f, 3));
		scrollFactor = toFloat(easy(f, 4), 1);		
	}
	
	// private
	
	private String easy(List<String> f, int index) {
		return (f.size() > index) ? f.get(index) : "";		
	}
	
}