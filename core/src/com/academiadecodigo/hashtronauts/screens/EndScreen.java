package com.academiadecodigo.hashtronauts.screens;

import com.academiadecodigo.hashtronauts.EndGameStates;
import com.academiadecodigo.hashtronauts.MrDuckHunt;
import com.academiadecodigo.hashtronauts.components.GameStrings;
import com.academiadecodigo.hashtronauts.utils.Fonts;
import com.academiadecodigo.hashtronauts.utils.Utils;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class EndScreen extends ScreenAdapter {
    private MrDuckHunt game;
    private BitmapFont stateFont;
    private Batch batch;
    private Camera camera;
    private EndGameStates endState;
    private Vector2 stateTextSize;

    public EndScreen(MrDuckHunt game, EndGameStates state) {
        super();
        this.endState = state;
        this.game = game;
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        stateFont = Fonts.COLLEGE.getFont();
    }

    @Override
    public void show() {
        stateTextSize = Utils.getStringSize(stateFont, endState.getMessage());
    }

    @Override
    public void render(float delta) {
        //OpenGL Stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Our Duty
        camera.update();
        batch.begin();

        stateFont.draw(batch, endState.getMessage(), Gdx.graphics.getWidth() / 2 - stateTextSize.x / 2, Gdx.graphics.getHeight() / 2 - stateTextSize.y / 2);

        batch.end();

        //Logic
        if (Gdx.input.isTouched()) {
            switch (endState) {
                case EXITGAME:
                    Gdx.app.exit();
                    break;
                case OUTOFAMMO:
                    game.setScreen(MainScreen.getInstance());
                    Gdx.app.exit();
                    break;

            }
        }
    }
}
