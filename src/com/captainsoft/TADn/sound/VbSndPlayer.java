/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.sound;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Clip;

import com.captainsoft.TADn.engine.excp.GameDataIoException;
import com.captainsoft.TADn.loader.vbFile.VbFile;
import com.captainsoft.spark.collect.Cache;
import com.captainsoft.spark.collect.CacheEntryCreator;
import com.captainsoft.spark.collect.CacheKey;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.sound.ClipSoundManager;
import com.captainsoft.spark.utils.Log;

/**
 * Default sound player.
 * 
 * @author mathias fringes
 */
public final class VbSndPlayer implements SndPlayer {

	// fields

	private final Cache<CacheKey, Clip> cache;
	private final ClipSoundManager clipSoundManager = new ClipSoundManager();
	
	private boolean playSound = true;

	// constructors

	public VbSndPlayer() {
		super();
		//
		cache = new Cache<CacheKey, Clip>(new ClipCacheEntryCreator());
		cache.name = "SndCache";
	}

	// private

	private Clip loadClip(String filename, int id) {
		//
		byte[] sndData = loadSoundData(filename, id);
		//
		Clip clip = null;
		try {
			clip = clipSoundManager.createClip(sndData);
		} catch (Exception e) {
			throw new GameDataIoException("error creating sound clip: " + filename + " " + id, e);
		}
		//
		return clip;
	}
	
	private byte[] loadSoundData(String filename, int id) {
		byte[] sndData = null;
		VbFile file = null;
		try {
			file = new VbFile(filename, VbFile.R);
			file.seekPosition((id * 8) - 1);			
			//
			int filePos = file.readInt();
			int fileLen = file.readInt();
			//
			file.seekPosition(filePos - 1);
			sndData = file.readBytes(fileLen);			
		} catch (Exception e) {			
			throw new GameDataIoException("error extracting: " + filename + " " + id, e);
		} finally {
			FileUtils.close(file);
		}
		return sndData;
	}

	// SndPlayer

	@Override
	public synchronized void playSound(String type, int id) {
		if (!playSound) {
			return;
		}
		//
		// special mapping
		if ((type == "smat") && (id == 1 || id == 2)) {
			id = 99;
		}
		//
		// get or create the sound in the cache and play...
		CacheKey key = new CacheKey(type, id);
		Log.info("Playing sound " + key);
		// TODO BUG in fights, the second sound is not played...!!?
		Clip clip = cache.get(key);		
		clipSoundManager.playClip(clip);
		Log.info("Start playing sound " + key);
	}

	@Override
	public void enabled(boolean enabled) {
		this.playSound = enabled;
	}

	//
	// private classes

	/**
	 * Creates cache keys for sound files.
	 * 
	 * @author mathias fringes
	 */
	private final class ClipCacheEntryCreator implements CacheEntryCreator<CacheKey, Clip> {

		private Map<String, String> files = new HashMap<String, String>();

		public ClipCacheEntryCreator() {
			files.put("sifc", "files/res/iface.srs");
			files.put("smat", "files/res/monatt.srs");
			files.put("smpr", "files/res/monprep.srs");
			files.put("sscl", "files/res/sclsnd.srs");
		}

		@Override
		public Clip create(CacheKey key) {
			String filename = files.get(key.type);
			if (filename == null) {
				throw new IllegalArgumentException("No file found for type: " + key.type);
			}
			Clip clip = VbSndPlayer.this.loadClip(filename, key.id);
			return clip;
		}

	}

}