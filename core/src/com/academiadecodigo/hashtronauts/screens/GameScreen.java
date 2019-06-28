package com.academiadecodigo.hashtronauts.screens;

import com.academiadecodigo.hashtronauts.EndGameStates;
import com.academiadecodigo.hashtronauts.MrDuckHunt;
import com.academiadecodigo.hashtronauts.components.Crosshair;
import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.components.Score;
import com.academiadecodigo.hashtronauts.components.weapons.Shotgun;
import com.academiadecodigo.hashtronauts.components.weapons.Weapon;
import com.academiadecodigo.hashtronauts.exceptions.MissedShoot;
import com.academiadecodigo.hashtronauts.exceptions.NotEnoughAmmo;
import com.academiadecodigo.hashtronauts.helpers.EnemyHelper;
import com.academiadecodigo.hashtronauts.helpers.GameHelpers;
import com.academiadecodigo.hashtronauts.helpers.PlayerHelper;
import com.academiadecodigo.hashtronauts.inputProcessors.GameInputProcessor;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameScreen extends ScreenAdapter {

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    //Background
    private Music bgMusic;
    private Texture bgImage = new Texture("Landscape.png");

    //Game Helpers
    private EnemyHelper enemyHelper = GameHelpers.getEnemyHelper();

    private PlayerHelper playerHelper = GameHelpers.getPlayerHelper();

    private MrDuckHunt game = MrDuckHunt.getInstance();

    private boolean isPaused = false;

    private static GameScreen instance;

    private GameScreen() {
        this.batch = game.getBatch();
        this.camera = game.getCamera();

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/MrDuckSoundTrack.wav"));
        bgMusic.setLooping(true);
        bgMusic.play();

        enemyHelper.spawnRandomEnemy().setInitialPos(50, 50);
    }

    private double currTime = 0.0;
    private double lastDebounceTime = 0.0;
    private double debounceDelay = 1.5;

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

        playerHelper.render(batch, camera);

        enemyHelper.drawTargets(batch);

        batch.end();
        //Game Logic
        enemyHelper.moveTargets();

        currTime += delta;
        if ((currTime - lastDebounceTime) > debounceDelay) {
            enemyHelper.spawnRandomEnemy().setInitialPos(50, (int) Math.round(Math.random() * (((Gdx.graphics.getHeight() -50) - (Gdx.graphics.getHeight() /2)) + (Gdx.graphics.getHeight() /2))));
            lastDebounceTime = currTime;
        }

        if(playerHelper.isWeaponEmpty())
            endGame(EndGameStates.OUTOFAMMO);
    }

    @Override
    public void dispose() {
        bgImage.dispose();
        bgMusic.stop();
        bgMusic.dispose();
        enemyHelper.disposeTargets();
        playerHelper.dispose();
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void pauseGame(){
        isPaused = true;
    }

    public void unpauseGame(){
        isPaused = false;
    }

    public static GameScreen getInstance() {
        if(instance == null)
            instance = new GameScreen();

        return instance;
    }

    private void endGame(EndGameStates state) {
        game.setScreen(new EndScreen(game, state));
    }

    public static void reset(){
        instance.dispose();
        instance = new GameScreen();
    }
}
