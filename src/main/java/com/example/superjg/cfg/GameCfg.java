package com.example.superjg.cfg;

public class GameCfg {

	public GameCfg() {
		this.difficulty = Difficulty.NORMAL;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public int getUPS() {
		return ups;
	}

	public void setUPS(int ups) {
		this.ups = ups;
	}

	@Override
	public String toString() {
		return String.format("%s {difficulty:%s, ups:%d}", getClass().getSimpleName(), difficulty, ups);
	}

	private Difficulty difficulty;
	private int ups;

}
