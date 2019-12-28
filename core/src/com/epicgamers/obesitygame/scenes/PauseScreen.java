package com.epicgamers.obesitygame.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class PauseScreen {
	//make texture variables
	Texture pause;
	Texture menuImage;
	Texture fontTexture;
	SpriteBatch batch;
	BitmapFont font;
	//FreeTypeFontGenerator font;
	
		public PauseScreen() {
			//initialize texture
			pause = new Texture(Gdx.files.internal("food.png"));
			menuImage = new Texture(Gdx.files.internal("menu.png"));
			batch = new SpriteBatch();    
	        font = new BitmapFont();
	        font.setColor(Color.RED);
			//fontTexture = new Texture(Gdx.files.internal("MontserratRegular.ttf"));
	        //fontTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	        //font = new BitmapFont(Gdx.files.internal("MontserratRegular.ttf"), new TextureRegion(fontTexture), false);
		}
		
		public Scene render(Batch batch) {
			
			//Regular use for rendering (no need to worry about batch.begin() or batch.end)
			Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			//batch.draw(pause, 0, 0);
			
			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
				int mouseX = Gdx.input.getX();
				int mouseY = Gdx.input.getY();
				if(320 < mouseX && mouseX < 950 && 140 < mouseY && mouseY < 240) {
					return Scene.GAME;
				} else if(320 < mouseX && mouseX < 950 && 320 < mouseY && mouseY < 420) {
					//Options menu here
					System.out.println("Options menu will open here...?");
				} else if(320 < mouseX && mouseX < 950 && 480 < mouseY && mouseY < 580) {
					return Scene.TITLE;
				}
			}
			
			font.getData().setScale(4);
			
			batch.draw(pause, 0, 0, 1280, 720);
			batch.draw(menuImage, 217, 9, 47*18, 39*18);
			font.draw(batch, "Restart", 530, 215);
			font.draw(batch, "Options", 530, 380);
			font.draw(batch, "Resume", 530, 555);
			
			//Conditional returning scene to change
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.P) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
				return Scene.GAME;
			} else {
				return Scene.PAUSE;
			}
		}
}
