/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.TADr.model;

import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.questlog.PartyQuestLog;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * A game and its state.
 *
 * @author mathias fringes
 */
public final class Game {

    // fields

    public static final int NEW_GAME_NR = -1;

    private final Party party;
    private final PartyQuestLog questLog = new PartyQuestLog();

    private int number = NEW_GAME_NR;
    private LevelMap levelMap;
    private String name;
    private String player;

    // constructor

    public Game(String playerName, Party party) {
        super();
        this.player(playerName);
        this.party = party;
    }

    // accessors

    public LevelMap LevelMap() {
        return levelMap;
    }

    public void LevelMap(LevelMap levelMap) {
        this.levelMap = levelMap;
    }

    public PartyQuestLog QuestLog() {
        return questLog;
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public void number(int number) {
        this.number = number;
    }

    public int number() {
        return number;
    }

    public boolean isNewGame() {
        return (number == NEW_GAME_NR);
    }

    public Party party() {
        return party;
    }

    public void player(String player) {
        this.player = player;
    }

    public String player() {
        return player;
    }

    // overridden

    @Override
    public String toString() {
        return stringer("Game", number, name);
    }

}
