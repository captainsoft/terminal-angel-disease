/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.engine.debug;

import com.captainsoft.TADr.TadLang;
import com.captainsoft.spark.utils.Log;
import org.newdawn.easyogg.OggClip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author mathias fringes
 */
public class OggPlayer {

    public OggPlayer() {

    }

    //
    public static void play() {

        String filename = TadLang.file("ACDC.ogg");
        System.out.println("filename " + filename);
        File file = new File(filename);
        System.out.println("file: (" + file.exists() + ") " + file.getName());

        OggClip ogg = null;
        try {
            FileInputStream fs = new FileInputStream(file);
            Log.log("playing jazz.ogg");
            ogg = new OggClip(fs);
            ogg.loop();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
