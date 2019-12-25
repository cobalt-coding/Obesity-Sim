package com.epicgamers.obesitygame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class PauseScreen {
	//make texture variables
	
	
		public PauseScreen() {
			//initialize texture
			
		}
		
		public Scene render(Batch batch) {
			
			//Regular use for rendering (no need to worry about batch.begin() or batch.end)
			
			
			//Conditional returning scene to change
			
			return Scene.PAUSE;
		}
}
