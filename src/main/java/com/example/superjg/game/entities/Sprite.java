package com.example.superjg.game.entities;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import com.example.superjg.game.Game;
import com.example.superjg.game.act.Updateable;
import com.example.superjg.game.graphics.Collideable;
import com.example.superjg.game.graphics.Rendereable;

public abstract class Sprite extends Entity
		implements Updateable, Rendereable, MouseListener, KeyListener, Collideable {

	public Sprite(int id, String name, int posx, int posy, int speedx, int speedy, Game game) {
		super(id, name);
		this.posx = posx;
		this.posy = posy;
		this.speedx = speedx;
		this.speedy = speedy;
		this.game = game;
		init();
	}

	/**
	 * This method is called at the end of {@link #Sprite} and is supposed to be
	 * overrided in order to initialize the specific-class fields.
	 */
	public abstract void init();

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public int getSpeedx() {
		return speedx;
	}

	public void setSpeedx(int speedx) {
		this.speedx = speedx;
	}

	public int getSpeedy() {
		return speedy;
	}

	public void setSpeedy(int speedy) {
		this.speedy = speedy;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return String.format("%s {id:%d, name=%s, posx=%d, posy=%d, speedx=%d, speedy=%d, speed=%d}",
				getClass().getSimpleName(), id, name, posx, posy, speedx, speedy, speed);
	}

	protected int posx;
	protected int posy;
	protected int speedx;
	protected int speedy;
	protected final Game game;
	protected int speed;

}
