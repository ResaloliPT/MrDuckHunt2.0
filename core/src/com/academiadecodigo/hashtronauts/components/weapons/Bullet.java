package com.academiadecodigo.hashtronauts.components.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private Texture bulletImg;
    private Rectangle bulletRect;

    public Bullet(int startX, int startY) {
        bulletImg = new Texture("weapons/Bullet.png");
        bulletRect = new Rectangle();
        bulletRect.setCenter(startX, startY);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(bulletImg, bulletRect.getX(), bulletRect.getY());
    }

    public void dispose() {
        bulletImg.dispose();
    }
}
