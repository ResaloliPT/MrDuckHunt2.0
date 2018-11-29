package com.academiadecodigo.hashtronauts;

import com.academiadecodigo.hashtronauts.screens.MainScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MrDuckHunt extends Game {
	//Core Components
	private SpriteBatch batch;
	private OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		this.setScreen(new MainScreen(this));
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined);

		//Calling Screen Render
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
}
