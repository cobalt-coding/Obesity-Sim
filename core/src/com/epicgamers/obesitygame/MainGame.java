package com.epicgamers.obesitygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends ApplicationAdapter {
	
	private Texture title;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		title = new Texture(Gdx.files.internal("title.png"));
		//title.setScale(3);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(210/255f, 248/255f, 252/255f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(title, 0, 0, 1280, 720);
		batch.end();
		
	}
	
	@Override
	public void dispose () {
		
	}
}
