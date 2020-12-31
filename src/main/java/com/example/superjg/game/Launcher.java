package com.example.superjg.game;

import com.example.superjg.utils.ConfigurationManager;

public class Launcher {

	public static void main(String[] args) throws InterruptedException {
		var gameConfiguration = ConfigurationManager.getGameConfiguration(args);
		System.out.printf("info: loaded %s%n", gameConfiguration);
		new GameWindow(gameConfiguration).start();
	}

}
