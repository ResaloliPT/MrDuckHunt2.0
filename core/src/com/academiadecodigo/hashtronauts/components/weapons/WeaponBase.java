package com.academiadecodigo.hashtronauts.components.weapons;

import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.exceptions.MissedShoot;
import com.academiadecodigo.hashtronauts.exceptions.NotEnoughAmmo;
import com.academiadecodigo.hashtronauts.utils.Fonts;
import com.academiadecodigo.hashtronauts.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class WeaponBase implements Weapon {

    private final Sound missSound;
    private Texture sideWeapon;
    private BitmapFont weaponTitle;

    private final WeaponType weaponType;
    private final String sideWeaponImg;

    //Weapon properties
    private volatile Array<Bullet> bullets;
    private final float missRate;
    private int clips;


    protected WeaponBase(WeaponType weaponType, String sideWeaponImg){
        this.sideWeaponImg = sideWeaponImg;

        this.missRate = weaponType.getMissRate();
        this.weaponType = weaponType;
        this.clips = weaponType.getClips();
        this.bullets = createBullets();

        this.missSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapons/weaponMiss.mp3"));
    }

    private Array<Bullet> createBullets() {
        Array<Bullet> bullets = new Array<Bullet>();

        for (int i = 0; i < weaponType.getAmmoPerClip() ; i++) {
            bullets.add(new Bullet(weaponType.getDamage()));
        }

        return bullets;
    }

    @Override
    public boolean shoot(Target target) throws MissedShoot, NotEnoughAmmo {
        if (bullets.size == 0) {
            throw new NotEnoughAmmo();
        }

        if (Math.random() < missRate) {
            missSound.play();
            throw new MissedShoot();
        }

        Bullet usedBullet;
        try {
            usedBullet = bullets.pop();
        } catch (IllegalStateException noAmmo){
            return false;
        }

        usedBullet.dispose();

        return target.hit(usedBullet.getDamage());
    }

    @Override
    public int getBulletCount() {
        return bullets.size;
    }

    public void dispose() {
        missSound.dispose();
        weaponTitle.dispose();
        sideWeapon.dispose();

        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
    }

    @Override
    public void renderWeapon(SpriteBatch batch) {
        if (sideWeapon == null) {
            sideWeapon = new Texture(sideWeaponImg);
        }

        if (weaponTitle == null) {
            weaponTitle = Fonts.getCustomFont("fonts/college.ttf", 20);
        }

        Vector2 titleSize = Utils.getStringSize(weaponTitle, weaponType.getTitle());
        weaponTitle.draw(batch, weaponType.getTitle(),
                Gdx.graphics.getWidth() - (titleSize.x + 60), Gdx.graphics.getHeight() - 10);
        batch.draw(sideWeapon, Gdx.graphics.getWidth() - (150 + 10), Gdx.graphics.getHeight() - (sideWeapon.getHeight() - 40), 150, 80);

        drawBullets(batch);
    }

    private void drawBullets(SpriteBatch batch) {
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).draw(batch, i);
        }
    }

    @Override
    public void reload() throws NotEnoughAmmo {
        if (bullets.size == 0 && clips == 0) {
            throw new NotEnoughAmmo();
        }

        this.bullets = createBullets();
        clips--;
    }
}
