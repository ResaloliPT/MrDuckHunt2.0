package com.academiadecodigo.hashtronauts;

import com.academiadecodigo.hashtronauts.components.weapons.Shotgun;
import com.academiadecodigo.hashtronauts.helpers.GameHelpers;
import com.academiadecodigo.hashtronauts.helpers.PlayerHelper;
import com.academiadecodigo.hashtronauts.helpers.ScreenHelper;
import com.academiadecodigo.hashtronauts.inputProcessors.GameInputProcessor;
import com.academiadecodigo.hashtronauts.screens.MainScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MrDuckHunt extends Game {
	//Core Components
	private SpriteBatch batch;
	private OrthographicCamera camera;

	private ScreenHelper screenHelper;

	private PlayerHelper playerHelper;


	private static MrDuckHunt instance = new MrDuckHunt();

	private MrDuckHunt(){}

	@Override
	public void create () {
		screenHelper = GameHelpers.getScreenHelper();
		playerHelper = GameHelpers.getPlayerHelper();

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		MainScreen.InitScreen(this);

		playerHelper.setWeapon(new Shotgun());

		setupEvents(this);

		this.setScreen(MainScreen.getInstance());
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(camera.combined);

		//Calling Screen Render
		super.render();
	}

	private void setupEvents(MrDuckHunt game) {
		Gdx.input.setInputProcessor(new GameInputProcessor());
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

	public static MrDuckHunt getInstance() {
		return instance;
	}
}
