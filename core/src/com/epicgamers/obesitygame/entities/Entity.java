package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Entity {
	
	//change as we make more sprites
	private static int cols, rows;
	
	Array<Animation<TextureRegion>> animations = new Array<Animation<TextureRegion>>(0);
	TextureRegion idle;
	protected Rectangle rectangle;
	private Texture spriteSheet;
	
	public Entity(float x, float y, float width, float height, String src, int columns, int rows, float timeBetweenFrames, int[] walkFrames) {
		rectangle = new Rectangle(x, y, width, height);
		spriteSheet = new Texture(Gdx.files.internal(src));
		
		cols = columns;
		this.rows = rows;
		
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, 
				spriteSheet.getWidth() / cols,
				spriteSheet.getHeight() / rows);
		
		for(int k = 0; k < walkFrames.length; k++) {
			int eos = 0;
			
			for(int temp = k - 1; temp != -1; temp--)
				eos += walkFrames[temp];
			
			TextureRegion[] spriteArray = new TextureRegion[walkFrames[k]];
			int index = 0;
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if(index >= walkFrames[k])
						break;
					//ignores the idle animation
					if((i == 0 && j != 0) || i + 1 + j + 1 < eos) {
						
					}
					else	
						spriteArray[index++] = tmp[i][j];
					
				}
			}
			animations.add(new Animation<TextureRegion>(timeBetweenFrames, spriteArray));
		}
		
		idle = tmp[0][0];
	}
	
	public Rectangle getRect() {
		return rectangle;
	}
	
	public boolean colliding(Rectangle r) {
		return rectangle.overlaps(r);
	}
	
}
