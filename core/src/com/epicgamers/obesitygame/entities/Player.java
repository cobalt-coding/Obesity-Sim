package com.epicgamers.obesitygame.entities;

public class Player extends Entity {
	
	public Player(float x, float y, float width, float height, String src) {
		super(x, y, width, height, src);
	}
	
	public boolean isColliding(Entity e) {
		return this.getRect().contains(e.getRect());
	}
	
}
