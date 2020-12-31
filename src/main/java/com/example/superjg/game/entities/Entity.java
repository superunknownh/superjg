package com.example.superjg.game.entities;

public abstract class Entity {

	public Entity(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + "]";
	}

	protected final int id;
	protected final String name;

}
