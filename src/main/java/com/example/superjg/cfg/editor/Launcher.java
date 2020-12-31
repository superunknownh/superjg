package com.example.superjg.cfg.editor;

import com.example.superjg.utils.ConfigurationManager;

public class Launcher {

	public static void main(String[] args) {
		var gameConfiguration = ConfigurationManager.getGameConfiguration(args);
		System.out.printf("info: loaded %s%n", gameConfiguration);
		new App(gameConfiguration).start();
	}

	public static String configurationFileName = "superjg.properties";

}
