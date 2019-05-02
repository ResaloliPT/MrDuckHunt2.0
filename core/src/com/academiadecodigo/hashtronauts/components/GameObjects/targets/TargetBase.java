package com.academiadecodigo.hashtronauts.components.GameObjects.targets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class TargetBase implements Target {

    public Texture targetImage;

    public double health;
    private Rectangle targetRect;

    private TargetType targetType;

    public TargetBase(TargetType targetType) {
        this.health = targetType.getHealth();
        this.targetImage = targetType.getImgLeft();
        this.targetRect = new Rectangle();
        this.targetType = targetType;
    }

    public void setInitialPos(int x, int y) {
        targetRect.set(x, y, targetImage.getWidth(), targetImage.getHeight());
    }

    @Override
    public boolean hit(double damage) {

        health -= damage;

        if (health <= 0) {
            health = 0;
            return true;
        }

        return false;
    }

    @Override
    public void move() {
        int units = (int) (targetType.getVelocity() * Gdx.graphics.getDeltaTime());

        targetRect.set(targetRect.x + units, targetRect.y, targetImage.getWidth(), targetImage.getHeight());
    }

    @Override
    public void draw(SpriteBatch batch) {

        batch.draw(targetImage, targetRect.x, targetRect.y, targetRect.width, targetRect.height);
    }

    @Override
    public void dispose() {
    }

    @Override
    public Rectangle getRectangle() {
        return targetRect;
    }
}
