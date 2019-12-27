package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Edible extends Entity {
	
	public Edible(float x, float y, float width, float height, String src) {
		super(x, y, width, height, src, 1, 1, 0, 0);
	}
	
	public void render(Batch batch) {
		
		batch.draw(idle, this.getRect().x, this.getRect().y);
		
	}
	
}
