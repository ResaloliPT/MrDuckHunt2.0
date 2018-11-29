package com.academiadecodigo.hashtronauts.screens;

import com.academiadecodigo.hashtronauts.MrDuckHunt;
import com.academiadecodigo.hashtronauts.components.GameStrings;
import com.academiadecodigo.hashtronauts.utils.Fonts;
import com.academiadecodigo.hashtronauts.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MainScreen extends ScreenAdapter {

    private final MrDuckHunt game;
    private final SpriteBatch batch;
    private OrthographicCamera camera;

    //Screen Texts
    private BitmapFont welcomeText;
    private BitmapFont pressToStart;
    private Vector2 welcomeTextSize;
    private Vector2 pressToStartSize;

    //Screen Components
    private Sound startSound;
    private Texture bgImage;

    public MainScreen(MrDuckHunt game) {
        this.game = game;
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        welcomeText = Fonts.COLLEGE.getFont();
        pressToStart = Fonts.COLLEGE.getFont();
        startSound = Gdx.audio.newSound(Gdx.files.internal("sounds/MRDUCKHUNT.wav"));
        bgImage = new Texture("StartMenuBG.png");
    }

    @Override
    public void show() {
        Gdx.audio.newSound(Gdx.files.internal("sounds/MRDUCKHUNT.wav")).play();

        welcomeTextSize = Utils.getStringSize(welcomeText, GameStrings.WELCOME_TEXT);
        pressToStartSize = Utils.getStringSize(pressToStart, GameStrings.PRESS_TO_START);
        startSound.play();
    }

    @Override
    public void render(float delta) {
        //OpenGL Stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Our Duty
        camera.update();
        batch.begin();

        batch.draw(bgImage, 0, 0);

        welcomeText.draw(batch, GameStrings.WELCOME_TEXT, Gdx.graphics.getWidth()/2 - welcomeTextSize.x/2,Gdx.graphics.getHeight()/2 - welcomeTextSize.y/2);
        pressToStart.draw(batch, GameStrings.PRESS_TO_START, Gdx.graphics.getWidth()/2 - pressToStartSize.x/2,Gdx.graphics.getHeight()/2 - pressToStartSize.y/2 - (welcomeTextSize.y + 10));

        batch.end();

        // Screen Logic
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

    }

    @Override
    public void dispose() {
        welcomeText.dispose();
        pressToStart.dispose();
        startSound.dispose();
        bgImage.dispose();
    }
}
