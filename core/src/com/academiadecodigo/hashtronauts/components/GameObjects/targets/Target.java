package com.academiadecodigo.hashtronauts.components.GameObjects.targets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Target {

    /**
     * Hits this target for {@param damage}
     *
     * @param damage the damage to hit this target
     * @return true if killed
     */
    boolean hit(double damage);


    void move();

    void draw(SpriteBatch batch);

    /**
     * Deletes all associated resources to this target
     */
    void dispose();

    Rectangle getRectangle();

    void setInitialPos(int x, int y);
}
