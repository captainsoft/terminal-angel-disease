/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.loader.vb;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.engine.excp.GameDataIoException;
import com.captainsoft.TADr.loader.MonsterLoader;
import com.captainsoft.TADr.loader.vbFile.VbFile;
import com.captainsoft.TADr.model.fight.Monster;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.utils.ExcUtils;

/**
 * Mosnter loader for the old VB file format.
 *
 * @author mathias fringes
 */
public final class VbMonsterLoader implements MonsterLoader {

    // constructors

    public VbMonsterLoader() {
        super();
    }

    // public

    public VbFile createVbFile(String type) throws FileNotFoundException {
        VbFile file = new VbFile(TadLang.file("dat/mondata.dat"), type);
        file.setRecordSize(108);
        file.setChunkSize(1);
        return file;
    }

    public void saveMonster(Monster monster) {
        ExcUtils.argPlusPositive("monster.id", monster.id);
        //
        VbFile file = null;
        try {
            file = createVbFile(VbFile.RW);
            file.seekRecord(1, monster.id);
            //
            file.writeString(monster.name, 25);
            file.writeShort(monster.hitPoints);
            file.writeByte(monster.strength);
            file.writeByte(monster.agressive);
            file.writeByte(monster.item);
            file.writeShort(monster.speed);
            file.writeByte(monster.resist);
            file.writeByte(monster.nonresist);
            file.writeShort(monster.chips);
            file.writeString(monster.attackString, 70);
            file.writeByte(monster.speech);
            file.writeByte(monster.image);
        } catch (Exception e) {
            throw new GameDataIoException("error saving: " + file, e);
        } finally {
            FileUtils.close(file);
        }
    }

    // MonsterLoader

    @Override
    public Monster loadMonster(int id) {
        Monster monster = null;
        VbFile file = null;
        try {
            file = createVbFile(VbFile.R);
            file.seekRecord(1, id);
            //
            monster = new Monster(id);
            monster.name = file.readString(25);
            monster.hitPoints = file.readShort();
            monster.maxHitPoints = monster.hitPoints;
            monster.strength = file.readByte();
            monster.agressive = file.readByte();
            monster.item = file.readByte();
            monster.speed = file.readShort();
            monster.resist = file.readByte();
            monster.nonresist = file.readByte();
            monster.chips = file.readShort();
            monster.attackString = file.readString(70);
            monster.speech = file.readByte();
            monster.image = file.readByte();
        } catch (Exception e) {
            throw new GameDataIoException("error loading: " + file, e);
        } finally {
            FileUtils.close(file);
        }
        return monster;
    }

    @Override
    public Integer[] loadMonsterParty(int map, int id) {
        VbFile file = null;
        try {
            file = new VbFile(TadLang.file("dat/monpart.dat"), VbFile.R);
            file.setRecordSize(5);
            file.setChunkSize(99);
            file.seekRecord(map, id);
            //
            List<Integer> l = new ArrayList<Integer>(5);
            for (int i = 0; i < 5; i++) {
                l.add(file.readByte());
            }
            Collections.shuffle(l);
            Integer[] j = new Integer[5];
            l.toArray(j);
            return j;
        } catch (Exception e) {
            throw new GameDataIoException("error loading monster party.", e);
        } finally {
            FileUtils.close(file);
        }
    }

}
