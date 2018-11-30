package com.academiadecodigo.hashtronauts.screens;

import com.academiadecodigo.hashtronauts.MrDuckHunt;
import com.academiadecodigo.hashtronauts.components.Crosshair;
import com.academiadecodigo.hashtronauts.components.Score;
import com.academiadecodigo.hashtronauts.components.weapons.Shotgun;
import com.academiadecodigo.hashtronauts.components.weapons.Weapon;
import com.academiadecodigo.hashtronauts.components.weapons.WeaponBase;
import com.academiadecodigo.hashtronauts.components.weapons.WeaponType;
import com.academiadecodigo.hashtronauts.exceptions.MissedShoot;
import com.academiadecodigo.hashtronauts.exceptions.NotEnoughAmmo;
import com.academiadecodigo.hashtronauts.utils.Fonts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    //Background
    private Music bgMusic;
    private Texture bgImage;

    //Game Components
    private Crosshair crosshair;
    private Score score;
    private Weapon weapon;


    public GameScreen(MrDuckHunt game) {
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        crosshair = new Crosshair(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        score = new Score();

        bgImage = new Texture("Landscape.png");
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/MrDuckSoundTrack.wav"));
        bgMusic.setLooping(true);
        bgMusic.play();

        weapon = new Shotgun();
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

        weapon.renderWeapon(batch);
        score.draw(batch);
        crosshair.draw(batch);

        batch.end();
        //Game Logic
        //Move Crosshair
        crosshair.move(camera, new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            try {
                weapon.shoot(null);
            } catch (MissedShoot ignored) {} catch (NotEnoughAmmo ignored) {}
        }


    }

    @Override
    public void dispose() {
        crosshair.dispose();
        score.dispose();
        bgImage.dispose();
        bgMusic.stop();
        bgMusic.dispose();
        weapon.dispose();
    }
}
