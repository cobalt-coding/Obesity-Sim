package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Graphic extends Entity {
	
	private float scaler;
	static int[] frames = {0};
	
	public Graphic(float x, float y, float width, float height, float scaler, String src) {
		super(x, y, width, height, src, 1, 1, 0, frames);
		this.scaler = scaler;
	}
	
	public void render(Batch batch) {
		
		batch.draw(idle, getRect().x, getRect().y, getRect().width*scaler, getRect().height*scaler);
		
	}

}
