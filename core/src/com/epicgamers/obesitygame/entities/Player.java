package com.epicgamers.obesitygame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Entity {
	
	//change as we change sprites, also make sure frames are even, otherwise you will have blank frames
	//walkframes does not include the idle right now, can change later if unsatisfactory
	static int cols = 4, rows = 3;
	static int[] animationsFrames = {3, 7};
	public static float width = 34*2;
	public static float height = 51*2;
	static String src = "Epic dude.png";
	
	
	public float time;
	float x = 50, y = 50;
	
	float velX = 0, velY = 0;
	float oldVelX = 0, oldVelY = 0;
	final float SPEED = 60, FRICTION = 1.1f;
	
	public int foodEaten = 0;
	public boolean eating = false;
	
	boolean movingHorizontal = false;
	boolean movingVertical = false;
	boolean movingRightLast = true;
	boolean spriteRight = true;
	
	int leftLimit = 0;
	int rightLimit = 1280;
	int downLimit = 0;
	int upLimit = 720;
	
	public Player(float x, float y) {
		//the constructor should be self-explanatory if you look at the parameter names
		super(x, y, width, height, src, cols, rows, 0.2f, animationsFrames);
		this.x = x;
		this.y = y;
		//time is used to determine which frame to display
		time = 0f;
	}
	
	public boolean isColliding(Entity e) {
		return colliding(e.getRect());
	}
	
	
	public void render(Batch batch) {
		
		time += Gdx.graphics.getDeltaTime(); 
		
		Animation<TextureRegion> walkCycle = animations.get(1);
		Animation<TextureRegion> eat = animations.get(0);
		
		TextureRegion currentFrame = walkCycle.getKeyFrame(time, true);
		
		oldVelX = velX;
		oldVelY = velY;
		
		//Conflicting directional input or no input
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT) ) {


			time += Gdx.graphics.getDeltaTime(); 
			if(currentFrame.isFlipX()) {
				currentFrame.flip(true, false);
				spriteRight = true;
			}
			movingHorizontal = true;
			movingRightLast = true;

			velX+=SPEED*Gdx.graphics.getDeltaTime();

			//going left
		} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

			time += Gdx.graphics.getDeltaTime(); 
			if(!currentFrame.isFlipX()) {
				currentFrame.flip(true, false);
				spriteRight = false;
			}
			movingHorizontal = true;
			movingRightLast = false;

			velX-=SPEED*Gdx.graphics.getDeltaTime();

		}else if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { //If neither are pressed
			movingHorizontal = false;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

			time += Gdx.graphics.getDeltaTime();
			movingVertical = true;

			velY+=SPEED*Gdx.graphics.getDeltaTime();

		} else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && !Gdx.input.isKeyPressed(Input.Keys.UP)) {

			time += Gdx.graphics.getDeltaTime();
			movingVertical = true;

			velY-=SPEED*Gdx.graphics.getDeltaTime();

		} else if (!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			movingVertical = false;

		}

		//If two opposite direction buttons are being pressed, stop movement
		if((Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) || (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT))) {
			velX = oldVelX;
			velY = oldVelY;
			movingHorizontal = false;
			movingVertical = false;
		}

		if(movingHorizontal || movingVertical) { //If there is movement
			batch.draw(currentFrame, x, y, width, height);
		}else if(!spriteRight && !movingRightLast){
			idle.flip(true, false);
			batch.draw(idle, x, y, width, height);
			idle.flip(true, false);
		}else if(spriteRight && movingRightLast) {
			batch.draw(idle, x, y, width, height);
		}else {
			batch.draw(idle,  x,  y,  width,  height);
			//System.out.println("Error with loading sprites in the Player.java class");
		}
		
		if(x + velX <= rightLimit - width && x + velX >= leftLimit)
			x+=velX;	
		
		if(y + velY <= upLimit - height && y + velY >= downLimit )
			y+=velY;
			
		rectangle.x = x;
		rectangle.y = y;
		
		velX/=FRICTION;
		velY/=FRICTION;
		
	}
	
}
