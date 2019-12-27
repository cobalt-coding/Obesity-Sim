package com.epicgamers.obesitygame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class PauseScreen {
	//make texture variables
	Texture pause;
	
		public PauseScreen() {
			//initialize texture
			pause = new Texture(Gdx.files.internal("badlogic.jpg"));
		}
		
		public Scene render(Batch batch) {
			
			//Regular use for rendering (no need to worry about batch.begin() or batch.end)
			Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			batch.draw(pause, 0, 0, 1280, 720);
			
			//Conditional returning scene to change
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
				return Scene.GAME;
			} else {
				return Scene.PAUSE;
			}
		}
}
