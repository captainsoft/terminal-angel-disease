/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.captainsoft.TADn.loader.ItemRepository;
import com.captainsoft.TADn.party.InventoryPlace;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.Party;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.files.LineFile;
import com.captainsoft.spark.utils.Log;

import static com.captainsoft.spark.collect.CollectUtils.*;

/**
 * Basic debugger, with settings for development.
 *
 * @author mathias fringes
 */
public final class Debugger {
	
	// static
	
	public static final Debugger inst = new Debugger();
	
	private static final String filename = "files/sav/props.txt";

	// fields
	
	public boolean godMode = false;
	public boolean godMonsters = false;
	public boolean loadTilesDirect = false;	
	public boolean noMonsters = false;
	public boolean on = false;	
		
	// constructors
	
	private Debugger() {
		super();
		load();
	}
	
	// private
	
	private void load() {
		if (new File(filename).exists()) {
			try {
				List<String> lines = LineFile.load(filename);
				Map<String, Boolean> mp = toBooleanMap(iniFileList(lines));
				//
				godMode = getOrFalse(mp, "godMode");
				godMonsters = getOrFalse(mp, "godMonsters");
				loadTilesDirect = getOrFalse(mp, "loadTilesDirect");
				noMonsters = getOrFalse(mp, "noMonsters");
				on = getOrFalse(mp, "on");				
				Log.log("Loaded from debugger file!");
			} catch (IOException e) {
				Log.error("Error loading debugger:", e);				
			}
		} else {
			Log.log("no debugger file " + filename + " found!");
		}
	}
	
	private boolean getOrFalse(Map<String, Boolean> map, String name) {
		return (map.containsKey(name) ? map.get(name) : false);
	}
	
	// public
	
	public void startGameCheat(Party party, LevelMap levelMap) {		
		if (!on) {
			return;
		}		
		levelMap.tile(56, 10).item(224);
		levelMap.tile(56, 8).item(242);
		levelMap.tile(13, 69).item(160);		
		levelMap.tile(15, 65).item(211);		
		party.addCoins(10000 * 100);		
		party.member(1).fun.cur(1);
		party.member(3).fun.cur(20);			
		ItemRepository ir = TadRepo.inst().ItemRepo();
		for(PartyMember pm : party.members) {
			pm.inventory().item(InventoryPlace.Weapon, ir.item(85));
			pm.inventory().item(InventoryPlace.Protection, ir.item(131));
		}
	}

}