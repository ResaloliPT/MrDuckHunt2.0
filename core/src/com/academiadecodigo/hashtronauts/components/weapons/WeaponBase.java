package com.academiadecodigo.hashtronauts.components.weapons;

import com.academiadecodigo.hashtronauts.components.Crosshair;
import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.exceptions.MissedShoot;
import com.academiadecodigo.hashtronauts.exceptions.NotEnoughAmmo;
import com.academiadecodigo.hashtronauts.utils.Fonts;
import com.academiadecodigo.hashtronauts.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public abstract class WeaponBase implements Weapon {

    private final Sound missSound;
    private Texture sideWeapon;
    private BitmapFont weaponTitle;
    private BitmapFont clipsLeft;
    private BitmapFont ammoBar;

    private final WeaponType weaponType;

    private Crosshair crosshair;

    //Weapon properties
    private volatile Array<Bullet> bullets;
    private final float missRate;
    private int clips;


    protected WeaponBase(WeaponType weaponType, String sideWeaponImg) {
        this.missRate = weaponType.getMissRate();
        this.weaponType = weaponType;
        this.clips = weaponType.getClips();
        this.bullets = createBullets();

        crosshair = new Crosshair(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        this.missSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapons/weaponMiss.mp3"));

        weaponTitle = Fonts.getCustomFont("fonts/college.ttf", 20);
        clipsLeft = Fonts.getCustomFont("fonts/college.ttf", 50);
        ammoBar = Fonts.getCustomFont("fonts/college.ttf", 100);

        sideWeapon = new Texture(sideWeaponImg);
    }

    private Array<Bullet> createBullets() {
        Array<Bullet> bullets = new Array<Bullet>();

        for (int i = 0; i < weaponType.getAmmoPerClip(); i++) {
            bullets.add(new Bullet(weaponType.getDamage()));
        }

        return bullets;
    }

    @Override
    public boolean shoot(Target target) throws MissedShoot, NotEnoughAmmo {
        if (bullets.size == 0) {
            throw new NotEnoughAmmo();
        }

        Bullet usedBullet;
        try {
            usedBullet = bullets.pop();
        } catch (IllegalStateException noAmmo) {
            throw new NotEnoughAmmo();
        }

        if (Math.random() < missRate || target == null) {
            missSound.play();
            throw new MissedShoot();
        }

        boolean dead = target.hit(usedBullet.getDamage());

        usedBullet.dispose();

        return dead;
    }

    @Override
    public int getBulletCount() {
        return bullets.size;
    }

    @Override
    public int getClipCount() {
        return clips;
    }

    public void dispose() {
        crosshair.dispose();
        missSound.dispose();
        weaponTitle.dispose();
        sideWeapon.dispose();
        clipsLeft.dispose();

        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
    }

    @Override
    public void renderWeapon(SpriteBatch batch, Camera camera) {

        crosshair.draw(batch);

        Vector2 titleSize = Utils.getStringSize(weaponTitle, weaponType.getTitle());

        weaponTitle.draw(batch, weaponType.getTitle(),
                Gdx.graphics.getWidth() - (titleSize.x + 60), Gdx.graphics.getHeight() - 10);

        ammoBar.draw(batch, "i", 55, 80);

        clipsLeft.draw(batch, clips + "", 30, 50);

        batch.draw(sideWeapon, Gdx.graphics.getWidth() - (150 + 10), Gdx.graphics.getHeight() - (sideWeapon.getHeight() - 40), 150, 80);

        drawBullets(batch);

        //Logic
        crosshair.move(camera, new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }

    private void drawBullets(SpriteBatch batch) {
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).draw(batch, i);
        }
    }

    @Override
    public boolean reload() throws NotEnoughAmmo {
        if (getBulletCount() > 0)
            return false;

        if (clips <= 0)
            throw new NotEnoughAmmo();

        this.bullets = createBullets();
        clips--;

        return true;
    }
}
