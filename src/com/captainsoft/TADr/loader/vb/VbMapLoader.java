/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.loader.vb;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.engine.excp.GameDataIoException;
import com.captainsoft.TADr.loader.MapLoader;
import com.captainsoft.TADr.loader.vbFile.VbFile;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.map.Tile;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.utils.Log;

import java.io.File;

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
        switch (map.nr()) {
            case 2:
                map.tile(6, 28).set(1, 21);
                break;
            case 3:
                // TODO maybe save the fixed in the maps via the editor to fix this!?
                // free the justin chamberlain statue from the woods.
                /*map.tile(62, 55).set(1, 22).set(4, 0);
                map.tile(63, 55).set(1, 21).set(4, 0);
                map.tile(63, 54).set(1, 22).set(4, 0);
                map.tile(64, 54).set(1, 21).set(4, 0);*/
                break;
            case 4:
                // add an additional instant coffee machine in the no-monster-area
                map.tile(11, 21).values(32, 0, 0, 251);
                map.tile(3, 1).set(4, 32);
                break;
            case 7:
                // there is a black tile at that position. put some grass over it.
                map.tile(46, 25).set(1, 100);
                break;
            case 9:
                map.tile(16, 18).set(1, 32);
                map.tile(88, 34).set(1, 21);
                break;
            case 10:
                map.tile(72, 8).set(1, 173);
                map.tile(55, 80).set(3, 0);
                map.tile(56, 81).set(3, 0);
                break;
            case 11:
                map.tile(4, 67).set(1, 160);
                break;
            case 17:
                // fix coffee automaton/sweets automaton mix-up
                map.tile(5, 1).set(4, 50);
                map.tile(6, 1).set(4, 56);
                break;
            default:
                break;
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
        levelMap.tileset(levelMap.tile(1, 1).value(4));
        //
        applyFixes(levelMap);
        //
        return levelMap;
    }

    public void save(LevelMap map, String filename) {
        Log.info("saving map number: " + map.nr());
        //
        VbFile file = null;
        try {
            file = new VbFile(TadLang.file(filename), VbFile.RW);
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

    // MapLoader

    public LevelMap load(int number) {
        return loadMap(TadLang.file(ToggleMapFileName), number);
    }

    public void save(LevelMap map) {
        save(map, ToggleMapFileName);
    }

}
