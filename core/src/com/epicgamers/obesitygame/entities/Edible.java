package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Edible extends Entity {
	
	public int foodValue;
	public int foodValReq;
	float scalar;
	static int[] frames = {0};
	
	public Edible(float x, float y, float width, float height, float scalar, String src, int foodValue, int foodValReq) {
		super(x, y, width*scalar, height*scalar, src, 1, 1, 0, frames);
		this.foodValue = foodValue;
		this.foodValReq = foodValReq;
		this.scalar = scalar;
	}
	
	public void render(Batch batch) {
		
		batch.draw(idle, this.getRect().x, this.getRect().y, getRect().width * scalar, getRect().height * scalar);
		
	}
	
}
