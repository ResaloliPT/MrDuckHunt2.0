package com.academiadecodigo.hashtronauts.components.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private Texture bulletImg;
    private Rectangle bulletRect;

    private final double damage;

    public Bullet(double bulletDmg) {
        this.damage = bulletDmg;
    }

    public void initDraw() {
        bulletImg = new Texture("weapons/Bullet.png");
        bulletRect = new Rectangle();
    }

    public void draw(SpriteBatch batch, int offset) {

        if (bulletImg == null) {
            initDraw();
        }

        int startX = 15;
        int startY = 10;
        bulletRect.set(startX + ((10 + 30) * offset), startY , 30, 100);
        batch.draw(bulletImg, bulletRect.getX(), bulletRect.getY(), bulletRect.width, bulletRect.height);
    }

    public void dispose() {
        bulletImg.dispose();
    }

    public double getDamage() {
        return damage;
    }
}
