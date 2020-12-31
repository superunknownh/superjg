package com.example.superjg.utils;

import com.example.superjg.cfg.Difficulty;
import com.example.superjg.game.ia.DifficultyValues;

public class DifficultyUtils {

	private DifficultyUtils() {
	}

	public static DifficultyValues getValues(Difficulty difficulty) {
		switch (difficulty) {
		case EASY:
			return new DifficultyValues(4, 4);
		case HARD:
			return new DifficultyValues(5, 5);
		case NORMAL:
		default:
			return new DifficultyValues(7, 5);
		}
	}

}
