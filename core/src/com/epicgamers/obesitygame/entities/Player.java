package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {
	
	//change as we change sprites, also make sure frames are even, otherwise you will have blank frames
	//walkframes does not include the idle right now, can change later if unsatisfactory
	static int cols = 3, rows = 3, walkFrames = 6;
	static float width = 34*2, height = 51*2;
	static String src = "Epic dude2.png";
	
	
	float time;
	float x = 50, y = 50;
	
	float velX = 0, velY = 0;
	final float SPEED = 60, FRICTION = 1.1f;
	
	public int foodEaten = 0;
	
	boolean moving = false;
	
	public Player(float x, float y) {
		//the constructor should be self-explanatory if you look at the parameter names
		super(x, y, width, height, src, cols, rows, 0.2f, walkFrames);
		this.x = x;
		this.y = y;
		//time is used to determine which frame to display
		time = 0f;
	}
	
	public boolean isColliding(Entity e) {
		return colliding(e.getRect());
	}
	
	
	public void render(Batch batch) {
		//no actual movement code
		
		time += Gdx.graphics.getDeltaTime(); 
		
		TextureRegion currentFrame = walkCycle.getKeyFrame(time, true);
		
		//Conflicting directional input or no input
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {
			
			time += Gdx.graphics.getDeltaTime(); 
			if(currentFrame.isFlipX()) {
				currentFrame.flip(true, false);
			}
			moving = true;
			
			velX+=SPEED*Gdx.graphics.getDeltaTime();
		
		//going left
		} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			
			time += Gdx.graphics.getDeltaTime(); 
			if(!currentFrame.isFlipX()) {
				currentFrame.flip(true, false);
			}
			moving = true;
			
			velX-=SPEED*Gdx.graphics.getDeltaTime();
			
		}else {
			moving = false;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			
			time += Gdx.graphics.getDeltaTime();
			moving = true;
			
			velY+=SPEED*Gdx.graphics.getDeltaTime();
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && !Gdx.input.isKeyPressed(Input.Keys.UP)) {
			
			time += Gdx.graphics.getDeltaTime();
			moving = true;
			
			velY-=SPEED*Gdx.graphics.getDeltaTime();
			
		} else if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			moving = false;
		}
		
		if(moving)
			batch.draw(currentFrame, x, y, width, height);
		else
			batch.draw(idle, x, y, width, height);
		
		x+=velX;
		y+=velY;
		
		rectangle.x = x;
		rectangle.y = y;
		
		velX/=FRICTION;
		velY/=FRICTION;
		
	}
	
}
