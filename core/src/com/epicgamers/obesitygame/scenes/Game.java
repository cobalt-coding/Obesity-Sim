package com.epicgamers.obesitygame.scenes;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.epicgamers.obesitygame.MainGame;
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
	
	boolean zooming = false;
	
	float desiredZoom = 1.0f;
	float lastZoom = 1.0f;
	
	float originalPlayerWidth = player.width;
	float lastWidth = player.width;
	float desiredWidth = player.width;
	
	float originalPlayerHeight = player.height;
	float lastHeight = player.height;
	float desiredHeight = player.height;
	
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
		
		graphics = new Array<Graphic>();
		
		/*
		 * camera = new OrthographicCamera(30, 20 * (Gdx.graphics.getWidth() /
		 * Gdx.graphics.getHeight())); camera.position.set(camera.viewportWidth / 2f,
		 * camera.viewportHeight / 2f, 0); camera.update();
		 */
		
		int index = 0;
		for (String row : graphicLayout) {
			for (int i = 0 ; i < row.length() ; i++) {
				graphics.add(new Graphic(i*64, index*64, 32, 32, 2, "wood.png"));
			}
			index++;
		}
		
		food.add(new Edible(300, 200, 20, 20, 3, "turkey.png", 1, 0));
		food.add(new Edible(500, 400, 20, 20, 5, "table.png", 1, 1));
		
		font = new BitmapFont();
	}
	
	public Scene render(Batch batch, OrthographicCamera cam) {
		
		//Regular use for rendering (no need to worry about batch.begin() or batch.end)
		Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		for (Graphic graphic : graphics) {
			graphic.render(batch);
		}
		
		player.render(batch);
		
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
			edible.render(batch);
			if (player.colliding(edible.getRect())) {
				if(edible.foodValReq <= player.foodEaten) {
					player.time = 0;
					player.foodEaten+=edible.foodValue;
					player.eating = true;
					iter.remove();
				}
			}
		}
		
		font.draw(batch, Integer.toString(player.foodEaten), 20, 720-20);
		
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
			} else if(player.foodEaten >= 15) {
				desiredZoom = 5;
				desiredWidth = lastWidth*5;
				desiredHeight = lastHeight*5;
			} else if(player.foodEaten >= 2){
				desiredZoom = 2;
				desiredWidth = lastWidth*2;
				desiredHeight = lastHeight*2;
			} else {
				desiredZoom = 1;
				desiredWidth = originalPlayerWidth;
				desiredHeight = originalPlayerHeight;
			}
		}
		
		//Smoothly handles zooming and changing width and height. Don't need to mess with this if we're just adding new stages of zooming
		if(cam.zoom < desiredZoom) {
			cam.zoom += 0.01*(desiredZoom - lastZoom);
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
		
		//Conditional returning Scene to change
		if(Gdx.input.isKeyJustPressed(Input.Keys.P) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			return Scene.PAUSE;
		} else {
			return Scene.GAME;
		}
	}
}
