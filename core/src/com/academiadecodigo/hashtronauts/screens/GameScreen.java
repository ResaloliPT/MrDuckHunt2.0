package com.academiadecodigo.hashtronauts.screens;

import com.academiadecodigo.hashtronauts.MrDuckHunt;
import com.academiadecodigo.hashtronauts.components.Crosshair;
import com.academiadecodigo.hashtronauts.components.Score;
import com.academiadecodigo.hashtronauts.utils.Fonts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class GameScreen extends ScreenAdapter {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private MrDuckHunt game;

    //Game Components
    private Crosshair crosshair;
    private Score score;
    private Music bgMusic;
    private Texture bgImage;

    public GameScreen(MrDuckHunt game) {
        this.game = game;
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        crosshair = new Crosshair(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        score = new Score();

        bgImage = new Texture("Landscape.png");
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/MrDuckSoundTrack.wav"));
        bgMusic.setLooping(true);
        bgMusic.play();
    }

    @Override
    public void render(float delta) {
        //OpenGL Stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Our Duty
        camera.update();
        batch.begin();
        //Render Space

        batch.draw(bgImage, 0, 0);

        score.draw(batch);
        crosshair.draw(batch);

        batch.end();
        //Game Logic
        //Move Crosshair
        crosshair.move(camera, new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }

    @Override
    public void dispose() {
        crosshair.dispose();
        score.dispose();
        bgImage.dispose();
        bgMusic.stop();
        bgMusic.dispose();
    }
}
