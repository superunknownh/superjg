package com.example.superjg.cfg;

public class Configuration {

	public Configuration() {
		this.gameInfo = new GameInfo();
		this.gameCfg = new GameCfg();
		this.videoCfg = new VideoCfg();
		this.audioCfg = new AudioCfg();
	}

	public GameInfo getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}

	public GameCfg getGameCfg() {
		return gameCfg;
	}

	public void setGameCfg(GameCfg gameCfg) {
		this.gameCfg = gameCfg;
	}

	public VideoCfg getVideoCfg() {
		return videoCfg;
	}

	public void setVideoCfg(VideoCfg videoCfg) {
		this.videoCfg = videoCfg;
	}

	public AudioCfg getAudioCfg() {
		return audioCfg;
	}

	public void setAudioCfg(AudioCfg audioCfg) {
		this.audioCfg = audioCfg;
	}

	public String getCfgFilePath() {
		return cfgFilePath;
	}

	public void setCfgFilePath(String cfgFilePath) {
		this.cfgFilePath = cfgFilePath;
	}

	@Override
	public String toString() {
		return String.format("%s {gameInfo:%s, gameCfg:%s, videoCfg:%s, audioCfg:%s, cfgFilePath:%s}",
				getClass().getSimpleName(), gameInfo, gameCfg, videoCfg, audioCfg, cfgFilePath);
	}

	private GameInfo gameInfo;
	private GameCfg gameCfg;
	private VideoCfg videoCfg;
	private AudioCfg audioCfg;
	private String cfgFilePath;

}
