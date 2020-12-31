package com.example.superjg.cfg;

public class AudioCfg {

	public float getMusicLevel() {
		return musicLevel;
	}

	public void setMusicLevel(float musicLevel) {
		this.musicLevel = musicLevel;
	}

	public float getSfxLevel() {
		return sfxLevel;
	}

	public void setSfxLevel(float sfxLevel) {
		this.sfxLevel = sfxLevel;
	}

	@Override
	public String toString() {
		return String.format("%s {musicLevel:%f, sfxLevel:%f}", getClass().getSimpleName(), musicLevel, sfxLevel);
	}

	private float musicLevel;
	private float sfxLevel;

}
