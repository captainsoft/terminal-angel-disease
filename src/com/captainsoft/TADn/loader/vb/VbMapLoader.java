/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.loader.vb;

import com.captainsoft.TADn.TadLang;
import com.captainsoft.TADn.engine.excp.GameDataIoException;
import com.captainsoft.TADn.loader.MapLoader;
import com.captainsoft.TADn.loader.vbFile.VbFile;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.Tile;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.utils.Log;

/**
 * Loads and saves maps from the old VisualBasic format.
 * 
 * @author mathias fringes
 */
public final class VbMapLoader implements MapLoader {
	
	// static
	
	private static final int MapFileRecordSize = 99 * 99 * 4;
	private static final String ToggleMapFileName = "sav/tadmap.tgl";
	
	// constructors
	
	public VbMapLoader() {
		super();
	}
	
	// private
	
	private void applyFixes(LevelMap map) {
		if (map.nr() == 17) {
			// fix coffee automaton/sweets automaton mix-up
			map.tile(5,1).set(4, 50);
			map.tile(6,1).set(4, 56);
		}
	}
	
	// public
		
	public LevelMap loadMap(String filename, int number) { 
		Log.info("loading map number: " + number);
		//
		LevelMap levelMap = new LevelMap(number);
		VbFile file = null;		
		try {			
			file = new VbFile(filename, VbFile.R);
			file.setRecordSize(MapFileRecordSize);
			file.setChunkSize(1);
			file.seekRecord(1, number);	      
	        //
	        for (int y = 1; y < 100; y++) {
	        	for (int x = 1; x < 100; x++) {	        	
	        		// TODO 2 REFACTOR check if this can be loaded faster!? Also check all other vb loaders.
	        		int v1 = file.readByte();
	                int v2 = file.readByte();
	                int v3 = file.readByte();
	                int v4 = file.readByte();
	                //
	                Tile t = new Tile(v1, v2, v3, v4);                  	                
	                levelMap.setTileAt(x, y, t);   	               	                           
	        	}               
	        }	            	       	        	        
		} catch (Exception e) {
			throw new GameDataIoException("Cannot load from map file: " + file, e);
		} finally {
			FileUtils.close(file);			
		}
		//
		levelMap.setSecondOverlayStart(levelMap.tile(2, 1).value(4));
	    levelMap.setTilesetNr(levelMap.tile(1, 1).value(4));
	    applyFixes(levelMap);
	    //
	    return levelMap;
	}
	
	// MapLoader
	
	@Override
	public LevelMap load(int number) {		
		return loadMap(TadLang.file(ToggleMapFileName), number);
	}	

	@Override
	public void save(LevelMap map) {
		Log.info("saving map number: " + map.nr());
		//
		VbFile file = null;
		try {
            file = new VbFile(TadLang.file(ToggleMapFileName), VbFile.RW);
            file.setRecordSize(MapFileRecordSize);
			file.setChunkSize(1);
			file.seekRecord(1, map.nr());	   
            //
            int x, y, z;
            for (y = 1; y < 100; y++) {
                for (x = 1; x < 100; x++) {
                    for (z = 1; z < 5; z++) {                        
                        int value = map.tile(x, y).value(z);
                        file.writeByte(value);                        
                    }
                }
            }
            //
        } catch (Exception e) {
        	throw new GameDataIoException("Error saving map: " + file, e);
        } finally {
        	FileUtils.close(file);        	
        }
	}
	
}
