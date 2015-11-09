/*
 * Copyright Captainsoft 2010 - 2014.
 * All rights reserved.
 */
package com.captainsoft.TADn.loader.java;

import java.io.File;
import java.io.RandomAccessFile;

import com.captainsoft.TADn.TadRepo;
import com.captainsoft.TADn.engine.excp.GameDataIoException;
import com.captainsoft.TADn.engine.excp.GameStateException;
import com.captainsoft.TADn.loader.GameLoader;
import com.captainsoft.TADn.loader.MapLoader;
import com.captainsoft.TADn.model.Position;
import com.captainsoft.TADn.party.Game;
import com.captainsoft.TADn.party.Inventory;
import com.captainsoft.TADn.party.Item;
import com.captainsoft.TADn.party.LevelMap;
import com.captainsoft.TADn.party.Party;
import com.captainsoft.TADn.party.PartyMember;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.utils.ExcUtils;
import com.captainsoft.spark.utils.Log;

/**
 * Loads and saves TAD game files.
 * This is already a new JAVA-like format. The old Visual Basic file format is not 
 * in use for game file anymore!
 *  
 * Creates and saves {@link Game} objects. Also loads the current map fo
 * that game.
 * 
 *
 * @author mathias fringes
 */
public class JavaGameLoader implements GameLoader {

	// fields

	private MapLoader mapLoader;

	// constructors 

	public JavaGameLoader(MapLoader mapLoader) {
		super();
		this.mapLoader = mapLoader; 
	}

	// GameLoader

	@Override
	public Game load(int index) {
		ExcUtils.argZeroPositive("index", index);
		Log.info("Loading game " + index);
		//
		Game game = load("files/sav/tadparty." + index);
		game.number(index);
		copyMap("sav/tadmap" + game.number() + ".dat", "sav/tadmap.tgl");
		LevelMap map = mapLoader.load(game.party().mapNumber());
		game.LevelMap(map);
		//
		return game;
	}	

	@Override
	public Game loadNewGame() {
		Log.info("Loading new game");
		//
		Game game = load("files/dat/tadparty.dat");		
		copyMap("dat/tadmap.dat", "sav/tadmap.tgl");				
		LevelMap map = mapLoader.load(game.party().mapNumber());
		game.LevelMap(map);
		//
		return game;
	}

	@Override
	public void save(Game game) {	
		Log.info("Saving game " + game.number());
		//
		saveParty(game);
		mapLoader.save(game.LevelMap());        
		copyMap("sav/tadmap.tgl", "sav/tadmap" + game.number() + ".dat");
		//
		Log.debug("saving game successful!");
	}

	// private

	private void copyMap(String inFile, String outFile) {
		try {						
			FileUtils.copyFile("files/" + inFile, "files/" + outFile);				
		} catch (Exception e) {
			throw new GameDataIoException("Error copying map " + inFile + " " + outFile, e);	     
		}
	}

	private void saveParty(Game game) {
		//
		Party party = game.party();
		PartyMember[] members = party.members();
		//
		RandomAccessFile file = null;
		try {
			String filename =  "files/sav/tadparty." + game.number();
			new File(filename).createNewFile();
			Log.debug("saving filename " + filename);
			file = new RandomAccessFile(filename, "rw");
			//
			// cheat bytes
			int cheatBytes = (int) (Math.random() * 50 + 50);
			file.writeInt(cheatBytes);
			for (int i = 0; i < cheatBytes; i++) {                
				file.writeInt((int) (Math.random() * 312250));
			}			
			//
			// gamesave id for java
			file.writeInt(312); 
			//
			for (int i = 0; i < 4; i++) {
				file.writeInt(members[i].fun.cur());
				file.writeInt(members[i].fun.max());
				file.writeInt(members[i].getPtsFit());
				file.writeInt(members[i].getPtsFox());
				file.writeInt(99); //sf.writeInt(members[i].curWgt());
				file.writeInt(99); //sf.writeInt(members[i].maxWgt());
				file.writeInt(members[i].protect());
				file.writeInt(members[i].weaponSkill.value());
				file.writeInt(members[i].defenseSkill.value());
				file.writeInt(members[i].specialAttackSkill.value());
				file.writeInt(members[i].special4.value());
				// inventory
				for (int k = 0; k < Inventory.CAPACITY; k++) {
					Item item = members[i].getInventoryItem(k);
					int index = (item == null) ?  0 : item.id(); 
					file.writeInt(index);
				}
			}
			//
			file.writeLong(party.coins());
			file.writeInt(party.mapNumber());
			file.writeInt(party.position().x);
			file.writeInt(party.position().y);
			file.writeUTF(party.name().toString());
			file.writeUTF(game.player());
			//
			// add cheat bytes
			cheatBytes = (int) (Math.random() * 50 + 50);
			for (int i = 0; i < cheatBytes; i++) {
				file.writeInt((int) (Math.random() * 250312));
			}
			
		} catch (Exception e) {		
			throw new GameDataIoException("Cannot load savegame " + game, e);	            
		} finally {			
			FileUtils.close(file);			
		}
	}

	private Game load(String filename) {
		//		
		RandomAccessFile file = null;             
		Game game = null;
		try {
			file = new RandomAccessFile(filename, "r");
			int cheatBytes = file.readInt();
			file.skipBytes(cheatBytes * 4);
			//
			int saveID = file.readInt();
			if (saveID != 312) {
				throw new GameStateException("No JAVA TAD ID this is not a Java savegame"); 			
			}
			//
			Party party = new Party();	
			for (int i = 1; i <= 4; i++) {
				party.member(i).fun.cur(file.readInt());
				party.member(i).fun.max(file.readInt());
				party.member(i).setPtsFit(file.readInt());
				party.member(i).setPtsFox(file.readInt());
				file.readInt(); // was former current weight;
				file.readInt(); // party.getMember(i).setMaxWgt(sf.readInt());
				party.member(i).protect(file.readInt());
				party.member(i).weaponSkill.value(file.readInt());
				party.member(i).defenseSkill.value(file.readInt());
				party.member(i).specialAttackSkill.value(file.readInt());
				party.member(i).special4.value(file.readInt());	
				//
				// inventory
				for (int ix = 0; ix < Inventory.CAPACITY; ix++) {
					int index = file.readInt();
					Item item = TadRepo.inst().ItemRepo().item(index);
					party.member(i).setInventoryItem(ix, item);
				}
			}
			//
			party.coins(file.readLong());
			party.mapNumber(file.readInt());
			//
			int xpos = file.readInt();
			int ypos = file.readInt();           
			party.position(new Position(xpos, ypos));
			//
			party.name(file.readUTF().trim());	
			String playerName = file.readUTF().trim();
			//
			game = new Game(playerName, party);
		} catch (Exception e) {
			throw new GameDataIoException("Error loading game ", e);			 	
		} finally {
			FileUtils.close(file);
		}
		return game;              
	}

}
