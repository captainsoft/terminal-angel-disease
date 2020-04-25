/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.tools;

import java.io.IOException;
import java.util.Map;

import com.captainsoft.TADr.TadLang;

import com.captainsoft.TADr.loader.vb.VbMonsterLoader;
import com.captainsoft.TADr.loader.vbFile.VbFile;
import com.captainsoft.TADr.model.fight.Monster;
import com.captainsoft.tools.patch.*;
import com.captainsoft.tools.patch.de.MonsterPatchGerman;
import com.captainsoft.tools.patch.en.MonsterPatchEnglish;

/**
 * Patches monster names and their attack strings with German and English words.
 *
 * @author mathias fringes
 */
public final class MonsterPatcher {

    // fields

    private VbMonsterLoader monsterLoader;

    // constructors

    public MonsterPatcher() {
        super();
        monsterLoader = new VbMonsterLoader();
    }

    // public

    public void displayMonsterStructure() throws IOException {
        VbFile monsterFile = monsterLoader.createVbFile(VbFile.R);
        int recordCount = monsterFile.recordCount();
        say("RECORD SIZE: " + recordCount);
        //
        for (int i = 1; i <= recordCount; i++) {
            Monster monster = monsterLoader.loadMonster(i);
            say("p.put(\"" + monster.id + "-n\", \"" + monster.name + "\");");
            say("p.put(\"" + monster.id + "-a\", \"" + monster.attackString + "\");");
        }
    }

    public void patch(PatchData patchData) throws IOException {
        Map<String, String> data = patchData.data();
        //
        VbFile monsterFile = monsterLoader.createVbFile(VbFile.RW);
        for (int i = 1; i <= monsterFile.recordCount(); i++) {
            Monster monster = monsterLoader.loadMonster(i);
            monster.name = data.get(monster.id + "-n");
            monster.attackString = data.get(monster.id + "-a");
            try {
                monsterLoader.saveMonster(monster);
            } catch (Exception e) {
                throw new RuntimeException("Cannot save " + monster.attackString, e);
            }
        }
    }

    // private 

    public void say(Object text) {
        System.out.println(text.toString());
    }

    // main 

    public static void main(String[] args) {
        MonsterPatcher mp = new MonsterPatcher();
        try {
            mp.displayMonsterStructure();

            TadLang.toGerman();
            mp.patch(new MonsterPatchGerman());

            TadLang.toEnglish();
            mp.patch(new MonsterPatchEnglish());

            mp.say("Done matching Monters! Goodbye ;)!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
