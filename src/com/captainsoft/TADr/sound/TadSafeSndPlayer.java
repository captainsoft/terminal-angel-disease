/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.
 */
package com.captainsoft.TADr.sound;

import com.captainsoft.spark.utils.Log;

/**
 * Decorator for a sound player. Logs exceptions if the
 * sound cannot be played.
 *
 * @author mathias fringes
 */
public final class TadSafeSndPlayer implements SndPlayer {

    // fields

    private SndPlayer sndPlayer;

    // constructors

    public TadSafeSndPlayer(SndPlayer sndPlayer) {
        super();
        this.sndPlayer = sndPlayer;
    }

    // SndPlayer

    public void playSound(String type, int id) {
        try {
            sndPlayer.playSound(type, id);
        } catch (Exception e) {
            Log.error("Cannot play sound (" + type + "," + id + ")", e);
        }
    }

    public void enabled(boolean enabled) {
        sndPlayer.enabled(enabled);
    }

}