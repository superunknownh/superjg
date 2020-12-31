package com.example.superjg.game.ia;

public class DifficultyValues {

	public DifficultyValues(int ballSpeed, int racquetSpeed) {
		this.ballSpeed = ballSpeed;
		this.racquetSpeed = racquetSpeed;

	}

	public int getBallSpeed() {
		return ballSpeed;
	}

	public int getRacquetSpeed() {
		return racquetSpeed;
	}

	@Override
	public String toString() {
		return String.format("%s {ballSpeed:%d, racquetSpeed:%d}", getClass().getSimpleName(), ballSpeed, racquetSpeed);
	}

	private final int ballSpeed;
	private final int racquetSpeed;

}
