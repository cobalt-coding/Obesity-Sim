package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Edible extends Entity {
	
	public int foodValue;
	public boolean isEaten;
	public int foodValReq;
	
	public Edible(float x, float y, float width, float height, String src, int foodValue, boolean isEaten, int foodValReq) {
		super(x, y, width, height, src, 1, 1, 0, 0);
		this.foodValue = foodValue;
		this.foodValReq = foodValReq;
		this.isEaten = isEaten;
	}
	
	public void render(Batch batch) {
		
		batch.draw(idle, this.getRect().x, this.getRect().y);
		
	}
	
}
