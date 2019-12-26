package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
	
	//change as we make more sprites
	private static int cols, rows;
	
	Animation<TextureRegion> walkCycle;
	TextureRegion idle;
	private Rectangle rectangle;
	private Texture spriteSheet;
	
	public Entity(float x, float y, float width, float height, String src, int columns, int rows, float timeBetweenFrames, int walkFrames) {
		rectangle = new Rectangle(x, y, width, height);
		spriteSheet = new Texture(Gdx.files.internal(src));
		
		cols = columns;
		this.rows = rows;
		
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 
				spriteSheet.getWidth() / cols,
				spriteSheet.getHeight() / rows);

		TextureRegion[] spriteArray = new TextureRegion[walkFrames];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(index >= walkFrames)
					break;
				//ignores the idle animation
				if(i != 0 || j != 0)
					spriteArray[index++] = tmp[i][j];
			}
		}
		
		idle = tmp[0][0];
		
		walkCycle = new Animation<TextureRegion>(timeBetweenFrames, spriteArray);
	}
	
	public Rectangle getRect() {
		return rectangle;
	}
	
}
