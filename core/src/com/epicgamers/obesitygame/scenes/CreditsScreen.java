package com.epicgamers.obesitygame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class CreditsScreen {

	Texture credits;
	
	public CreditsScreen() {
		//initializing textures
		credits = new Texture(Gdx.files.internal("credits.png"));
	}
	
	public Scene render(Batch batch) {
		
		Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.draw(credits, 0, 0, 1280, 720);
		
		//return false if any button is pressed
		if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			return Scene.PAUSE;
		}else {
			return Scene.CREDITS;
		}
	}
}
