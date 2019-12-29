package com.epicgamers.obesitygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.epicgamers.obesitygame.scenes.TitleScreen;
import com.epicgamers.obesitygame.scenes.CreditsScreen;
import com.epicgamers.obesitygame.scenes.Game;
import com.epicgamers.obesitygame.scenes.PauseScreen;
import com.epicgamers.obesitygame.scenes.SceneEnum.Scene;

public class MainGame extends ApplicationAdapter {
	
	private Scene scene;
	private TitleScreen titleScreen;
	private Game game;
	private PauseScreen pause;
	private CreditsScreen credits;
	
	public OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shape;
	
	@Override
	public void create () {
		titleScreen = new TitleScreen();
		game = new Game();
		pause = new PauseScreen();
		credits = new CreditsScreen();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
		scene = Scene.TITLE;
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined);
		shape.setProjectionMatrix(camera.combined);
		batch.begin();

		switch(scene) {
		case TITLE:
			
			scene = titleScreen.render(batch, camera);
			
			break;
		case GAME:
			
			scene = game.render(batch, camera, shape);
			
			break;
		case PAUSE:
			
			scene = pause.render(batch, camera);
			
			break;
		case CREDITS:
			
			scene = credits.render(batch);
		}	
		
		
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		
	}

}
