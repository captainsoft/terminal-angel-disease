/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.engine.debug;

import static com.captainsoft.spark.collect.CollectUtils.iniFileList;
import static com.captainsoft.spark.collect.CollectUtils.toBooleanMap;
import static com.captainsoft.spark.utils.Utils.stringer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.spark.files.LineFile;
import com.captainsoft.spark.utils.Log;

/**
 * Basic debugger, with settings for development.
 *
 * @author mathias fringes
 */
public final class Debugger {
	
	// static
		
	public static final Debugger Inst = new Debugger();

	// fields
	
	public boolean godMode = false;
	public boolean godMonsters = false;
	public boolean loadTilesDirect = false;
	public boolean noClipping = false;
	public boolean noMonsters = false;
	public boolean on = false;
	public boolean tileInfo = false;
		
	// constructors
	
	private Debugger() {
		super();		
	}

	// public

    public void dump() {
        System.out.println("Debugger settings: ");
        System.out.println("godMode: " + godMode);
        System.out.println("godMonsters: " + godMonsters);
        System.out.println("loadTilesDirect: " + loadTilesDirect);
        System.out.println("noClipping: " + noClipping);
        System.out.println("noMonsters: " + noMonsters);
        System.out.println("tileInfo: " + tileInfo);
    }
	
	public void switchOn() {
        on = true;
        //
        String filename = TadLang.file("sav/props.txt");
		if (new File(filename).exists()) {
			try {
				List<String> lines = LineFile.load(filename);
				Map<String, Boolean> mp = toBooleanMap(iniFileList(lines));
				//
				godMode = getOrFalse(mp, "godMode");
				godMonsters = getOrFalse(mp, "godMonsters");
				loadTilesDirect = getOrFalse(mp, "loadTilesDirect");
				noClipping = getOrFalse(mp, "noClipping");
				noMonsters = getOrFalse(mp, "noMonsters");
				tileInfo = getOrFalse(mp, "tileInfo");
				//
				Log.log("Loaded from debugger file!");
			} catch (IOException e) {
				Log.error("Error loading debugger:", e);				
			}

		} else {
			Log.log("no debugger file " + filename + " found!");
		}
	}

    // private
	
	private boolean getOrFalse(Map<String, Boolean> map, String name) {
		return (map.containsKey(name) ? map.get(name) : false);
	}

    // overridden

    @Override
    public String toString() {
        return stringer("Debugger", on);
    }

}