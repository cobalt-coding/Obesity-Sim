package com.epicgamers.obesitygame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class TitleScreen {

	Texture title;
	
	public TitleScreen() {
		//initializing textures
		title = new Texture(Gdx.files.internal("title.png"));
	}
	
	public Scene render(Batch batch, OrthographicCamera cam) {
		
		Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.draw(title, 0, 0, 1280, 720);
		
		//return false if any button is pressed
		if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
			return Scene.GAME;
		}else {
			return Scene.TITLE;
		}
	}
}
