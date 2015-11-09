/*
 * Copyright Captainsoft 2010 - 2015.
 * All rights reserved.  
 */
package com.captainsoft.spark.sound;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.captainsoft.spark.files.FileUtils;

/**
 * Handles sound clips from the JavaX Sound package.
 *
 * @author mathias fringes
 */
public final class ClipSoundManager {
	
	public Clip createClip(final byte[] sndData) throws
            UnsupportedAudioFileException, IOException, LineUnavailableException {

		Clip clip = null;
		AudioInputStream ais = null;
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(sndData);
			ais = AudioSystem.getAudioInputStream(is);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format, ((int) ais.getFrameLength() * format.getFrameSize()));
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);		
		} finally {
			FileUtils.close(ais);
			FileUtils.close(is);
		}
		return clip;
	}	
	
	public void playClip(final Clip clip) {	
		if (clip == null) {
			return;
		}
		clip.stop();
		clip.flush();
		clip.setFramePosition(0);
		clip.start();		
	}

}