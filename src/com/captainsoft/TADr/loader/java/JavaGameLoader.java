/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.loader.java;

import java.io.File;
import java.io.RandomAccessFile;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.engine.TadRepo;
import com.captainsoft.TADr.engine.excp.GameDataIoException;
import com.captainsoft.TADr.engine.excp.GameStateException;
import com.captainsoft.TADr.loader.GameLoader;
import com.captainsoft.TADr.loader.MapLoader;
import com.captainsoft.TADr.model.Game;
import com.captainsoft.TADr.model.Position;
import com.captainsoft.TADr.model.item.Item;
import com.captainsoft.TADr.model.map.LevelMap;
import com.captainsoft.TADr.model.party.Inventory;
import com.captainsoft.TADr.model.party.Party;
import com.captainsoft.TADr.model.party.PartyMember;
import com.captainsoft.TADr.model.questlog.PartyQuestLog;
import com.captainsoft.TADr.model.questlog.QuestLogEngine;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.utils.ExcUtils;
import com.captainsoft.spark.utils.Log;

/**
 * Loads and saves TAD game files.
 * This is already a new JAVA-like format. The old Visual Basic file format is not
 * in use for game file anymore!
 *
 * Creates and saves {@link Game} objects. Also loads the current map for
 * that game.
 *
 * @author mathias fringes
 */
public class JavaGameLoader implements GameLoader {

    // fields

    private final static int QuestLogGuard = 238700;

    private MapLoader mapLoader;

    // constructors 

    public JavaGameLoader(MapLoader mapLoader) {
        super();
        this.mapLoader = mapLoader;
    }

    // GameLoader

    public Game load(int index) {
        ExcUtils.argZeroPositive("index", index);
        Log.info("Loading game " + index);
        //
        Game game = load(TadLang.folder() + "sav/tadparty." + index);
        game.number(index);
        copyMap("sav/tadmap" + game.number() + ".dat", "sav/tadmap.tgl");
        LevelMap map = mapLoader.load(game.party().mapNumber());
        game.LevelMap(map);
        //
        return game;
    }

    public Game loadNewGame() {
        Log.info("Loading new game");
        //
        Game game = load(TadLang.folder() + "dat/tadparty.dat");
        copyMap("dat/tadmap.dat", "sav/tadmap.tgl");
        LevelMap map = mapLoader.load(game.party().mapNumber());
        game.LevelMap(map);
        //
        return game;
    }

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
            FileUtils.copyFile(TadLang.folder() + inFile, TadLang.folder() + outFile);
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
            String filename = TadLang.folder() + "sav/tadparty." + game.number();
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
            // game save-id for java
            file.writeInt(312);
            //
            for (int i = 0; i < 4; i++) {
                PartyMember member = members[i];
                file.writeInt(member.fun.cur());
                file.writeInt(member.fun.max());
                file.writeInt(member.getPtsFit());
                file.writeInt(member.getPtsFox());
                file.writeInt(99); //sf.writeInt(members[i].curWgt());
                file.writeInt(99); //sf.writeInt(members[i].maxWgt());
                file.writeInt(member.protect());
                file.writeInt(member.weaponSkill.value());
                file.writeInt(member.defenseSkill.value());
                file.writeInt(member.specialAttackSkill.value());
                file.writeInt(member.specialMemberSkill.value());
                // inventory
                for (int k = 0; k < Inventory.CAPACITY; k++) {
                    Item item = member.getInventoryItem(k);
                    int index = (item == null) ? 0 : item.id();
                    file.writeInt(index);
                }
            }
            //
            file.writeLong(party.coins());
            file.writeInt(party.mapNumber());
            file.writeInt(party.position().x);
            file.writeInt(party.position().y);
            file.writeUTF(party.name());
            file.writeUTF(game.player());
            //
            // quest log
            file.writeInt(87);
            file.writeInt(91);
            for (PartyQuestLog.Item item : game.QuestLog().items()) {
                file.writeInt(item.questDescription.id);
                file.writeBoolean(item.solved);
            }
            file.writeInt(QuestLogGuard);
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
                PartyMember member = party.member(i);
                member.fun.cur(file.readInt());
                member.fun.max(file.readInt());
                member.setPtsFit(file.readInt());
                member.setPtsFox(file.readInt());
                file.readInt(); // was former current weight
                file.readInt(); // party.getMember(i).setMaxWgt(sf.readInt())
                member.protect(file.readInt());
                member.weaponSkill.value(file.readInt());
                member.defenseSkill.value(file.readInt());
                member.specialAttackSkill.value(file.readInt());
                member.specialMemberSkill.value(file.readInt());
                //
                // inventory
                for (int ix = 0; ix < Inventory.CAPACITY; ix++) {
                    int index = file.readInt();
                    Item item = TadRepo.inst().ItemRepo().item(index);
                    member.setInventoryItem(ix, item);
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
            game = new Game(playerName, party);
            //
            // quest log
            boolean hasQuestLog = (file.readInt() + "" + file.readInt()).equals("8791");
            if (hasQuestLog) {
                int key = file.readInt();
                while (key != QuestLogGuard) {
                    boolean solved = file.readBoolean();
                    game.QuestLog().append(QuestLogEngine.inst.byId(key), solved);
                    key = file.readInt();
                }
            }
            //
        } catch (Exception e) {
            throw new GameDataIoException("Error loading game ", e);
        } finally {
            FileUtils.close(file);
        }
        return game;
    }

}
