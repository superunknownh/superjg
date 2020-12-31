package com.example.superjg.game.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Audio {

	public Audio(String filePath) {
		try {
			AudioInputStream dais = AudioSystem.getAudioInputStream(new File(filePath));
			clip = AudioSystem.getClip();
			clip.open(dais);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void play() {
		if (clip == null) {
			return;
		}
		stop();
		clip.setFramePosition(0);
		clip.start();
	}

	public void loop() {
		if (clip == null) {
			return;
		}
		stop();
		clip.setFramePosition(0);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		if (clip.isRunning()) {
			clip.stop();
		}
	}

	public void close() {
		stop();
		clip.close();
	}

	public void setVolume(float volume) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(20f * (float) Math.log10(volume));
	}

	public float getVolume() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}

	private Clip clip;

}
