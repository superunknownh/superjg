package com.example.superjg.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.example.superjg.game.Game;

public class Racquet extends Sprite {

	public Racquet(String name, int posx, int posy, int speedx, int speedy, Game game) {
		super(1, name, posx, posy, speedx, speedy, game);
	}

	public Racquet(String name, int posx, int posy, Game game) {
		this(name, posx, posy, 1, 1, game);
	}

	public Racquet(String name, int posx, int posy, Game game, int speed) {
		this(name, posx, posy, 1, 1, game);
		this.speed = speed;
	}

	@Override
	public void init() {
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.speed = DEFAULT_SPEED;
		this.speedx = 0;
		this.speedy = 0;
	}

	@Override
	public void update() {
		if (posx + speedx > 0 && posx + speedx < game.getWidth() - width) {
			posx += speedx;
		}
	}

	@Override
	public void render() {
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.fillRect(posx, posy, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(posx, posy, width, height);
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
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			speedx = -speed;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			speedx = speed;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		speedx = 0;
	}

	private int width;
	private int height;

	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 25;
	public static final int DEFAULT_SPEED = 1;

}
