package com.epicgamers.obesitygame.scenes;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
//import com.epicgamers.obesitygame.MainGame;
import com.epicgamers.obesitygame.entities.Edible;
import com.epicgamers.obesitygame.entities.Graphic;
import com.epicgamers.obesitygame.entities.Player;
import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class Game {

	//make texture variables
	Player player;
	Edible foodTest;
	private Array<Edible> food;
	BitmapFont font;
	private Array<Graphic> graphics;
	private Sound eatSound;
	
	boolean zooming = false;
	
	float desiredZoom = 1.0f;
	float lastZoom = 1.0f;
	
	float originalPlayerWidth = player.width;
	float lastWidth = player.width;
	float desiredWidth = player.width;
	
	float originalPlayerHeight = player.height;
	float lastHeight = player.height;
	float desiredHeight = player.height;
	
	float visibleWidth = 1280;
	float visibleHeight = 720;
	
	final int centerOfScreenX = 1280/2;
	final int centerOfScreenY = 720/2;
	
	private boolean displayEat = false;
	private boolean allowEat = true;
	private float[] eatCoords = {0f, 0f};
	private static final int FRAME_COLS = 1, FRAME_ROWS = 2;
	Animation<TextureRegion> eatButton;
	Texture eatSheet;
	
	float stateTime;
	
	private String[] graphicLayout = new String[] {
		"                    ",
		"                    ",
		"                    ",
		"                    ",
		"                    ",
		"                    ",
		"                    ",
		"                    ",
		"                    ",
		"                    ",
		"                    ",
		"                    ",
	};
	
	public Game() {
		//initialize texture
		player = new Player(100, 100);
		food = new Array<Edible>();
		
		eatSheet = new Texture(Gdx.files.internal("eatsheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(eatSheet, eatSheet.getWidth()/FRAME_COLS, eatSheet.getHeight()/FRAME_ROWS);
		
		TextureRegion[] eatFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
		int index = 0;
		for (int i = 0 ; i < FRAME_ROWS ; i++) {
			for (int j = 0 ; j < FRAME_COLS ; j++) {
				eatFrames[index++] = tmp[i][j];
			}
		}
		
		eatButton = new Animation<TextureRegion>(0.5f, eatFrames);
		
		stateTime = 0f;
		
		graphics = new Array<Graphic>();
		eatSound = Gdx.audio.newSound(Gdx.files.internal("nom.mp3"));
		
		/*
		 * camera = new OrthographicCamera(30, 20 * (Gdx.graphics.getWidth() /
		 * Gdx.graphics.getHeight())); camera.position.set(camera.viewportWidth / 2f,
		 * camera.viewportHeight / 2f, 0); camera.update();
		 */
		
		int indexx = 0;
		for (String row : graphicLayout) {
			for (int i = 0 ; i < row.length() ; i++) {
				graphics.add(new Graphic(i*64, indexx*64, 32, 32, 2, "wood.png"));
			}
			indexx++;
		}
		
		//First screen
		food.add(new Edible(400, 200, 56, 33, 9, "table.png", 1, 4)); //3
		food.add(new Edible(600, 400, 42, 32, 4, "turkey.png", 1, 0)); //2
		food.add(new Edible(520, 420, 34, 29, 2.3f, "bakedPotato.png", 1, 0)); //1.5
		food.add(new Edible(430, 400, 40, 37, 2.3f, "corn.png", 1, 0)); //1.5
		food.add(new Edible(800, 430, 22, 16, 4, "rice.png", 1, 0)); //2
		
		//Second screen
		//food.add(new Edible(-768, 1008, 15, 32, 4.5f, "candyCane.png", 1, 5));
		food.add(new Edible(-896, 576, 15, 32, 20, "candyCane.png", 1, 5));
		food.add(new Edible(-1024, 144, 15, 32, 20, "candyCane.png", 1, 5));
		food.add(new Edible(-1152, -228, 15, 32, 20, "candyCane.png", 1, 5));
		food.add(new Edible(-1279, -719, 15, 32, 20, "candyCane.png", 1, 5));
		
		
		font = new BitmapFont();
	}
	
	public Scene render(Batch batch, OrthographicCamera cam, ShapeRenderer shape) {
		
		//Regular use for rendering (no need to worry about batch.begin() or batch.end)
		Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stateTime+=Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = eatButton.getKeyFrame(stateTime, true);
		
		for (Graphic graphic : graphics) {
			graphic.render(batch);
		}
		
		player.render(batch, shape);
		
//		for (Edible edible : food) {
//			edible.render(batch);
//			
//			//System.out.println(player.isColliding(edible));
//			
//			//Collision code...
//			if (player.isColliding(edible)) {
//				player.foodEaten+=edible.foodValue;
//			}
//		}
		
		for (Iterator<Edible> iter = food.iterator() ; iter.hasNext();) {
			Edible edible = iter.next();
			//System.out.println(player.colliding(edible.getRect()));
			edible.render(batch, shape);
			if (player.colliding(edible.getRect())) {
				if(edible.foodValReq <= player.foodEaten) {
					displayEat = true;
					eatCoords[0] = edible.getRect().getX();
					eatCoords[1] = edible.getRect().getY();
					if (Gdx.input.isKeyPressed(Input.Keys.Z) && allowEat) {
						eatSound.play();
						player.time = 0;
						player.foodEaten+=edible.foodValue;
						player.eating = true;
						iter.remove();
						allowEat = false;
						break;
					}
				}
			}
		}
		
		if (!Gdx.input.isKeyPressed(Input.Keys.Z))
			allowEat = true;
		
		visibleWidth = cam.viewportWidth*cam.zoom;
		visibleHeight = cam.viewportHeight*cam.zoom;
		
		font.getData().setScale(cam.zoom);
		font.draw(batch, "Food eaten: " + Integer.toString(player.foodEaten), (float)((0-((desiredZoom*1280-1280)/2))+(0.03*visibleWidth)), (float)((0-((desiredZoom*720-720)/2))+(0.95*visibleHeight)));
		
		if (displayEat) {
			batch.draw(currentFrame, eatCoords[0], eatCoords[1]);
			displayEat = false;
		}
		/*
		 * For adding more zooming states:
		 * -copy the format with desired zoom, width, and height and it will transition smoothly by itself
		 * -follow the format of if(foodEaten > x) {} else if (foodEaten > y) {} etc...
		 */
		if(!zooming) {
			if(player.foodEaten >= 20) {
				desiredZoom = 10;
				desiredWidth = lastWidth*10;
				desiredHeight = lastHeight*10;
			} else if(player.foodEaten >= 9) {
				desiredZoom = 5;
				desiredWidth = lastWidth*5;
				desiredHeight = lastHeight*5;
			} else if(player.foodEaten >= 5){
				desiredZoom = 3;
				desiredWidth = lastWidth*3;
				desiredHeight = lastHeight*3;
			} else {
				desiredZoom = 1;
				desiredWidth = originalPlayerWidth;
				desiredHeight = originalPlayerHeight;
			}
		}
		
		//Smoothly handles zooming and changing width and height. Don't need to mess with this if we're just adding new stages of zooming
		if(cam.zoom < desiredZoom) {
			cam.zoom += 0.01*(desiredZoom - lastZoom);
			if((cam.zoom + 0.01*(desiredZoom - lastZoom)) > desiredZoom) {
				cam.zoom = desiredZoom;
			}
			player.zoom += 0.01*(desiredZoom - lastZoom); //Makes the player movement scale with the camera
			zooming = true;
		} else {
			zooming = false;
		}
		
		if(player.width < desiredWidth) {
			player.width += 0.01*(desiredWidth-lastWidth);
			zooming = true;
		} else {
			zooming = false;
		}
		
		if(player.height < desiredHeight) {
			player.height += 0.01*(desiredHeight-lastHeight);
			zooming = true;
		} else {
			zooming = false;
		}
		
		cam.update();
		
		player.leftLimit = (int)(centerOfScreenX - (visibleWidth/2));
		player.rightLimit = (int)(centerOfScreenX + (visibleWidth/2));
		player.downLimit = (int)(centerOfScreenY - (visibleHeight/2));
		player.upLimit = (int)(centerOfScreenY + (visibleHeight/2));
		
		//Conditional returning Scene to change
		if(Gdx.input.isKeyJustPressed(Input.Keys.P) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			return Scene.PAUSE;
		} else {
			return Scene.GAME;
		}
	}
}
