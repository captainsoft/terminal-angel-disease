/*
 * Copyright Captainsoft 2010 - 2014. 
 * All rights reserved.  
 */
package com.captainsoft.TADn.party;

import static com.captainsoft.spark.utils.Utils.stringer;

/**
 * A game and its state.
 *
 * @author mathias fringes
 */
public final class Game {
	
	// fields
	
	private static final int NEW_GAME_NR = -1;
	
	private final Party party;
	
	private int number = NEW_GAME_NR;
	private LevelMap levelMap;
	private String name;
	private String player;
	
	// constructor
	
	public Game(String playerName, Party party) {
		super();
		this.party = party;
		this.playerName(playerName);
	}

	// accessors
	
	public LevelMap LevelMap() {		
		return levelMap;
	}
	
	public void LevelMap(LevelMap levelMap) {		
		this.levelMap = levelMap;
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

	public void playerName(String player) {
		this.player = player;
	}

	public String player() {
		return player;
	}
	
	// overridden methods
	
	@Override
	public String toString() {	
		return stringer("Game", number, name);
	}

}
