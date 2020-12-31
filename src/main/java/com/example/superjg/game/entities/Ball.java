package com.example.superjg.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.example.superjg.cfg.Difficulty;
import com.example.superjg.game.Game;
import com.example.superjg.game.audio.Audio;
import com.example.superjg.game.graphics.Collideable;

public final class Ball extends Sprite implements Collideable, MouseListener {

	public Ball(String name, int posx, int posy, int speedx, int speedy, Game game) {
		super(0, name, posx, posy, speedx, speedy, game);
	}

	public Ball(String name, int posx, int posy, Game game) {
		this(name, posx, posy, 1, 1, game);
	}

	public Ball(String name, int posx, int posy, Game game, int speed) {
		this(name, posx, posy, 1, 1, game);
		this.speed = speed;
		this.speedx += speed;
		this.speedy += speed;
	}

	@Override
	public void init() {
		diameter = DEFAULT_DIAMETER;
		speed = DEFAULT_SPEED;
		hitSFX = new Audio("./res/sfx/ball.wav");
		hitSFX.setVolume(game.getConfiguration().getAudioCfg().getSfxLevel());
	}

	@Override
	public void update() {
		var playerCollision = collidesWithPlayer();
		if (playerCollision != null) {
			posy = playerCollision.getPosy() - diameter;
			System.out.printf("debug: %s%n", this);
			System.out.printf("debug: %s%n", playerCollision);
			if (game.getConfiguration().getGameCfg().getDifficulty() == Difficulty.HARD) {
				speed++;
				playerCollision.setSpeed(playerCollision.getSpeed() + 1);
			}
			speedy = -speed;
			game.getHUD().increaseScore();
			hitSFX.play();
		} else if (posx + speedx < 0) {
			speedx = speed;
		} else if (posx + speedx > game.getWidth() - diameter) {
			speedx = -speed;
		} else if (posy + speedy < 0) {
			speedy = speed;
		} else if (posy + speedy > game.getHeight() - diameter) {
			speedx = 0;
			speedy = 0;
			game.gameOver();
		}
		posx += speedx;
		posy += speedy;
	}

	@Override
	public void render() {
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.YELLOW);
		g2d.fillOval(posx, posy, diameter, diameter);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(posx, posy, diameter, diameter);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	private Sprite collidesWithPlayer() {
		for (var player : game.getPlayers()) {
			if (getBounds().intersects(player.getBounds())) {
				return player;
			}
		}
		return null;
	}

	private int diameter;
	private Audio hitSFX;

	public static final int DEFAULT_DIAMETER = 50;
	public static final int DEFAULT_SPEED = 1;

}
