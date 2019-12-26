package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {
	
	Animation<TextureRegion> walkCycle;
	
	public Player(float x, float y, float width, float height, String src) {
		super(x, y, width, height, src);
	}
	
	public boolean isColliding(Entity e) {
		return this.getRect().contains(e.getRect());
	}
	
}
