package com.academiadecodigo.hashtronauts.components;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Crosshair {

    private Texture crossHairImg;
    private Rectangle crosshairRect;

    public Crosshair(int startX, int startY) {
        crossHairImg = new Texture("crosshair.png");
        crosshairRect = new Rectangle(startX, startY, crossHairImg.getWidth(), crossHairImg.getHeight());
        crosshairRect.setCenter(startX, startY);
    }


    /**
     * Moves the crosshair to the designed position
     *
     * @param camera the camera to unproject
     * @param touchPos the crosshair coordinates
     */
    public void move(Camera camera, Vector3 touchPos) {
        camera.unproject(touchPos);
        crosshairRect.setCenter(touchPos.x, touchPos.y);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(crossHairImg, crosshairRect.getX(), crosshairRect.getY());
    }

    public void dispose() {
        crossHairImg.dispose();
    }

    public Integer getX() {
        return (int) crosshairRect.getX();
    }

    public Integer getY() {
        return (int) crosshairRect.getY();
    }
}
