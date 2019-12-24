package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
	
	private Rectangle rectangle;
	private Texture img;
	
	public Entity(float x, float y, float width, float height, String src) {
		rectangle = new Rectangle(x, y, width, height);
		img = new Texture(Gdx.files.internal(src));
	}
	
	public Texture getImg() {
		return img;
	}
	
	public Rectangle getRect() {
		return rectangle;
	}
	
}
