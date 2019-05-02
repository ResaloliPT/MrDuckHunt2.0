package com.academiadecodigo.hashtronauts.screens;

import com.academiadecodigo.hashtronauts.MrDuckHunt;
import com.academiadecodigo.hashtronauts.components.GameStrings;
import com.academiadecodigo.hashtronauts.utils.Fonts;
import com.academiadecodigo.hashtronauts.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class PauseScreen extends ScreenAdapter {

    private BitmapFont pausedFont;

    private Camera camera;
    private Batch batch;

    private MrDuckHunt game;
    private Vector2 pausedTextSize;

    private Screen previousScreen;

    public PauseScreen(MrDuckHunt game, Screen previousScreen){
        super();
        this.game = game;
        this.previousScreen = previousScreen;
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        pausedFont = Fonts.COLLEGE.getFont();
    }

    @Override
    public void show() {
        pausedTextSize = Utils.getStringSize(pausedFont, GameStrings.GAME_PAUSED);
    }

    @Override
    public void render(float delta) {
        //OpenGL Stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Our Duty
        camera.update();
        batch.begin();

        pausedFont.draw(batch, GameStrings.GAME_PAUSED, Gdx.graphics.getWidth() / 2 - pausedTextSize.x / 2, Gdx.graphics.getHeight() / 2 - pausedTextSize.y / 2);

        batch.end();

        //Logic
        if (Gdx.input.isTouched()) {
            game.setScreen(previousScreen);
            this.dispose();
            if(previousScreen instanceof GameScreen)
                ((GameScreen)previousScreen).unpauseGame();
        }
    }
}
