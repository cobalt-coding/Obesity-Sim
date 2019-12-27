package com.epicgamers.obesitygame.scenes;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.epicgamers.obesitygame.entities.Edible;
import com.epicgamers.obesitygame.entities.Player;
import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class Game {

	//make texture variables
	Player player;
	Edible foodTest;
	private Array<Edible> food;
	BitmapFont font;
	
	public Game() {
		//initialize texture
		player = new Player(100, 100);
		food = new Array<Edible>();
		
		food.add(new Edible(400, 600, 56, 33, "table.png", 1, false, 1));
		food.add(new Edible(200, 300, 42, 32, "turkey.png", 1, false, 0));
		
		/*
		for (int i = 0 ; i < 5 ; i++) {
			food.add(new Edible((float)Math.random()*1280, (float)Math.random()*720, 20, 20, "food.png", 10));
		}
		*/
		font = new BitmapFont();
	}
	
	public Scene render(Batch batch) {
		
		//Regular use for rendering (no need to worry about batch.begin() or batch.end)
		Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		player.render(batch);
		
		for (Edible edible : food) {
			if(!edible.isEaten) {
				edible.render(batch);
				
				//Collision code...
				if(player.foodEaten >= edible.foodValReq) {
					if (player.isColliding(edible)) {
						player.foodEaten+=edible.foodValue;
						//Destroy food after eaten
						edible.isEaten = true;
					}
				}
			}
		}
		
//		for (Iterator<Edible> iter = food.iterator() ; iter.hasNext();) {
//			Edible edible = iter.next();
//			//System.out.println(player.colliding(edible.getRect()));
//			edible.render(batch);
//			if (player.colliding(edible.getRect())) {
//				System.out.println("wowee");
//			}
//		}
		
		font.draw(batch, Integer.toString(player.foodEaten), 20, 720-20);
		
		//Conditional returning Scene to change
		if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
			return Scene.PAUSE;
		} else {
			return Scene.GAME;
		}
	}
}
