/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.sound;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Clip;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.TADr.engine.excp.GameDataIoException;
import com.captainsoft.TADr.loader.vbFile.VbFile;
import com.captainsoft.spark.collect.cache.Cache;
import com.captainsoft.spark.collect.cache.CacheEntryCreator;
import com.captainsoft.spark.collect.cache.CacheKey;
import com.captainsoft.spark.command.ParamCommand;
import com.captainsoft.spark.files.FileUtils;
import com.captainsoft.spark.sound.ClipSoundManager;
import com.captainsoft.spark.utils.Log;

/**
 * Sound player that reads the old vb file format.
 *
 * @author mathias fringes
 */
public final class VbSndPlayer implements SndPlayer {

    // fields

    private final Cache<CacheKey, Clip> cache;
    private final ClipSoundManager clipSoundManager = new ClipSoundManager();

    private boolean playSound = true;

    // constructors

    public VbSndPlayer() {
        super();
        //
        cache = new Cache<CacheKey, Clip>(new ClipCacheEntryCreator(), 10);
        cache.name = "SndCache";
        cache.onRemove(new ParamCommand<Clip>() {

            public void execute(Clip clip) {
                clip.stop();
                clip.flush();
                clip.close();
            }
        });
    }

    // private

    private Clip loadClip(String filename, int id) {
        //
        byte[] sndData = loadSoundData(filename, id);
        //
        Clip clip = null;
        try {
            clip = clipSoundManager.createClip(sndData);
        } catch (Exception e) {
            throw new GameDataIoException("error creating sound clip: " + filename + " " + id, e);
        }
        //
        return clip;
    }

    private byte[] loadSoundData(String filename, int id) {
        byte[] sndData = null;
        VbFile file = null;
        try {
            file = new VbFile(filename, VbFile.R);
            file.seekPosition((id * 8) - 1);
            //
            int filePos = file.readInt();
            int fileLen = file.readInt();
            //
            file.seekPosition(filePos - 1);
            sndData = file.readBytes(fileLen);
        } catch (Exception e) {
            throw new GameDataIoException("error extracting: " + filename + " " + id, e);
        } finally {
            FileUtils.close(file);
        }
        return sndData;
    }

    // SndPlayer

    public synchronized void playSound(String type, int id) {
        if (!playSound) {
            return;
        }
        //
        // special mapping
        if ((type.equals("smat")) && (id == 1 || id == 2)) {
            id = 99;
        }
        //
        // get or create the sound in the cache and play...
        CacheKey key = new CacheKey(type, id);
        Log.info("Playing sound " + key);
        //
        // ATTENTION: will not play the sound a second time if already a sound is played!! (Some Java problem)
        Clip clip = cache.get(key);
        clipSoundManager.playClip(clip);
        Log.info("Start playing sound " + key);
    }

    public void enabled(boolean enabled) {
        this.playSound = enabled;
    }

    //
    // private classes

    /**
     * Creates cache keys for sound files.
     *
     * @author mathias fringes
     */
    private final class ClipCacheEntryCreator implements CacheEntryCreator<CacheKey, Clip> {

        private Map<String, String> files = new HashMap<String, String>();

        public ClipCacheEntryCreator() {
            String folder = TadLang.folder();
            files.put("sifc", folder + "res/iface.srs");
            files.put("smat", folder + "res/monatt.srs");
            files.put("smpr", folder + "res/monprep.srs");
            files.put("sscl", folder + "res/sclsnd.srs");
        }

        public Clip create(CacheKey key) {
            String filename = files.get(key.type);
            if (filename == null) {
                throw new IllegalArgumentException("No file found for type: " + key.type);
            }
            return VbSndPlayer.this.loadClip(filename, key.id);
        }

    }

}