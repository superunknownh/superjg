package com.example.superjg.game.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.example.superjg.cfg.Configuration;
import com.example.superjg.game.graphics.Rendereable;
import com.example.superjg.utils.GraphicUtils;

public class HUD implements Rendereable {

	public HUD(Configuration configuration, int score) {
		this.configuration = configuration;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void increaseScore(int quantity) {
		this.score += quantity;
	}

	public void increaseScore() {
		increaseScore(1);
	}

	@Override
	public void render() {
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.WHITE);
		GraphicUtils.drawLeftUpperCornerString(g2d, String.valueOf(score),
				new Rectangle(configuration.getVideoCfg().getWidth(), configuration.getVideoCfg().getHeight()),
				new Font(Font.MONOSPACED, Font.BOLD, DEFAULT_FONT_SIZE));

		GraphicUtils.drawRightUpperCornerString(g2d, configuration.getGameCfg().getDifficulty().name(),
				new Rectangle(configuration.getVideoCfg().getWidth(), configuration.getVideoCfg().getHeight()),
				new Font(Font.MONOSPACED, Font.BOLD, DEFAULT_FONT_SIZE));
	}

	private final Configuration configuration;
	private int score;

	public static final int DEFAULT_FONT_SIZE = 25;

}
