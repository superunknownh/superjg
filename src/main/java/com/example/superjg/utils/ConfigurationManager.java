package com.example.superjg.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.example.superjg.cfg.AudioCfg;
import com.example.superjg.cfg.Configuration;
import com.example.superjg.cfg.Difficulty;
import com.example.superjg.cfg.GameCfg;
import com.example.superjg.cfg.GameInfo;
import com.example.superjg.cfg.VideoCfg;

import static com.example.superjg.utils.ParseUtils.*;

public class ConfigurationManager {

	public static Configuration getGameConfiguration(String[] args) {
		var argsMap = ArgsParser.getArgsMap(args);

		var cfgFilePath = argsMap.get("cfg");

		var defaultProperties = getDefaultConfiguration();
		var userProperties = new Properties();

		if (cfgFilePath == null) {
			cfgFilePath = getDefaultFileName(SystemUtils.getCurrentPath());
		}

		try {
			userProperties = getUserConfiguration(cfgFilePath);
			var properties = fillMissingConfiguration(defaultProperties, userProperties);
			var gameConfiguration = parseConfiguration(properties);
			gameConfiguration.setCfgFilePath(cfgFilePath);
			return gameConfiguration;
		} catch (IOException ex) {
			ex.printStackTrace();
			try {
				createDefaultConfigurationInCurrentPath();
				return getGameConfiguration(args);
			} catch (IOException exx) {
				exx.printStackTrace();
				return parseConfiguration(defaultProperties);
			}
		}
	}

	public static void saveGameConfiguration(Configuration configuration) throws IOException {
		var properties = new Properties();

		properties.put("cfg.game.difficulty", configuration.getGameCfg().getDifficulty().name());
		properties.put("cfg.game.ups", String.valueOf(configuration.getGameCfg().getUPS()));

		properties.put("cfg.video.width", String.valueOf(configuration.getVideoCfg().getWidth()));
		properties.put("cfg.video.height", String.valueOf(configuration.getVideoCfg().getHeight()));
		properties.put("cfg.video.antialising", String.valueOf(configuration.getVideoCfg().isEnabledAntialiasing()));
		properties.put("cfg.video.fps", String.valueOf(configuration.getVideoCfg().getFPS()));

		properties.put("cfg.audio.music", String.valueOf(configuration.getAudioCfg().getMusicLevel()));
		properties.put("cfg.audio.sfx", String.valueOf(configuration.getAudioCfg().getSfxLevel()));

		properties.store(new FileOutputStream(configuration.getCfgFilePath()), null);

		System.out.printf("info: Saved Configuration to %s%n", configuration.getCfgFilePath());
	}

	public static void createDefaultConfigurationInCurrentPath() throws IOException {
		var filePath = getDefaultFileName(SystemUtils.getCurrentPath());
		var defaultProperties = getDefaultConfiguration();
		var configuration = parseConfiguration(defaultProperties);
		configuration.setCfgFilePath(filePath);
		saveGameConfiguration(configuration);
		System.out.printf("info: Created default Configuration in %s%n", filePath);
	}

	private static Properties getDefaultConfiguration() {
		var properties = new Properties();
		try {
			properties
					.load(ConfigurationManager.class.getClassLoader().getResourceAsStream("cfg/defaultcfg.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(FAIL_RETURN_CODE);
		}
		return properties;
	}

	private static Properties getUserConfiguration(String filePath) throws IOException {
		var properties = new Properties();
		if (filePath != null) {
			try (InputStream inputStream = new FileInputStream(filePath)) {
				properties.load(inputStream);
			}
		}
		return properties;
	}

	private static Properties fillMissingConfiguration(Properties completeCfg, Properties incompleteCfg) {
		var properties = new Properties();
		for (var key : completeCfg.stringPropertyNames()) {
			var value = incompleteCfg.getProperty(key, completeCfg.getProperty(key));
			properties.setProperty(key, value);
		}
		return properties;
	}

	private static Configuration parseConfiguration(Properties properties) {
		var configuration = new Configuration();
		configuration.setGameInfo(getGameInfo());
		configuration.setGameCfg(getGameCfg(properties));
		configuration.setVideoCfg(getVideoCfg(properties));
		configuration.setAudioCfg(getAudioCfg(properties));
		return configuration;
	}

	private static GameInfo getGameInfo() {
		var gameInfo = new GameInfo();
		var properties = new Properties();
		try {
			properties.load(ConfigurationManager.class.getClassLoader().getResourceAsStream("project.properties"));
			gameInfo.setArtifactId(properties.getProperty("artifactId"));
			gameInfo.setName(properties.getProperty("name"));
			gameInfo.setVersion(properties.getProperty("version"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return gameInfo;
	}

	private static GameCfg getGameCfg(Properties cfg) {
		var gameCfg = new GameCfg();
		try {
			gameCfg.setDifficulty(Difficulty.valueOf(cfg.getProperty("cfg.game.difficulty")));
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		gameCfg.setUPS(str2int(cfg.getProperty("cfg.game.ups")));
		return gameCfg;
	}

	private static VideoCfg getVideoCfg(Properties cfg) {
		var videoCfg = new VideoCfg();
		videoCfg.setWidth(str2int(cfg.getProperty("cfg.video.width")));
		videoCfg.setHeight(str2int(cfg.getProperty("cfg.video.height")));
		videoCfg.setEnabledAntialiasing(str2bool(cfg.getProperty("cfg.video.antialising")));
		videoCfg.setFPS(str2int(cfg.getProperty("cfg.video.fps")));
		return videoCfg;
	}

	private static AudioCfg getAudioCfg(Properties cfg) {
		var audioCfg = new AudioCfg();
		audioCfg.setMusicLevel(str2float(cfg.getProperty("cfg.audio.music")));
		audioCfg.setSfxLevel(str2float(cfg.getProperty("cfg.audio.sfx")));
		return audioCfg;
	}

	public static String getDefaultFileName(String path) {
		return path + DEFAULT_FILE_NAME + DEFAULT_FILE_EXTENSION;
	}

	public static final int FAIL_RETURN_CODE = 1;
	public static final String DEFAULT_FILE_NAME = "superjg.cfg";
	public static final String DEFAULT_FILE_EXTENSION = ".properties";

}
