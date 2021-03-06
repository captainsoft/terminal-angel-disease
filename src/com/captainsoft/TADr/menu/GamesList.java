/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.menu;

import static com.captainsoft.spark.convert.Converter.blackList;
import static com.captainsoft.spark.convert.Converter.validate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.engine.excp.GameDataIoException;
import com.captainsoft.spark.collect.CollectUtils;
import com.captainsoft.spark.files.LineFile;
import com.captainsoft.spark.utils.Log;

/**
 * The list of names for savegames.
 *
 * @author mathias fringes
 */
public final class GamesList {

    // fields

    private static final String SAVEGAMES_FILENAME = TadLang.folder() + "sav/games.tad";

    // constructors

    public GamesList() {
        super();
    }

    // static

    public static boolean isGoodGameName(String name) {
        return (validate(name, 1, 50) && blackList(name, "-", "<empty>", "<frei>"));
    }

    // public

    /**
     * Loads the game lists and returns their names as a list of strings.
     *
     * @return
     */
    public List<String> load() {
        try {
            return LineFile.load(SAVEGAMES_FILENAME);
        } catch (FileNotFoundException e) {

            Log.log("Cannot find games list, creating new one.");
            return CollectUtils.createList(9, "-");
        } catch (IOException e) {

            throw new GameDataIoException("Cannot load games list", e);
        }
    }

    /**
     * Saves the game list file with the provided list of savegame names.
     *
     * @param names
     */
    public void save(String[] names) {
        try {
            LineFile.save(SAVEGAMES_FILENAME, Arrays.asList(names));
        } catch (IOException e) {
            throw new GameDataIoException("Cannot save games list", e);
        }
    }

}