package com.epicgamers.obesitygame.scenes;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.epicgamers.obesitygame.entities.Edible;
import com.epicgamers.obesitygame.entities.Player;
import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class Game {

	//make texture variables
	Player player;
	Edible foodTest;
	private Array<Edible> food;
	
	public Game() {
		//initialize texture
		player = new Player(100, 100);
		foodTest = new Edible(20, 20, 20, 20, "food.png");
		food = new Array<Edible>();
		
		for (int i = 0 ; i < 5 ; i++) {
			food.add(new Edible((float)Math.random()*1280, (float)Math.random()*720, 20, 20, "food.png"));
		}
	}
	
	public Scene render(Batch batch) {
		
		//Regular use for rendering (no need to worry about batch.begin() or batch.end)
		Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		player.render(batch);
		foodTest.render(batch);
		
		for (Edible edible : food) {
			edible.render(batch);
		}
		
		//Conditional returning Scene to change
		return Scene.GAME;
	}
}
