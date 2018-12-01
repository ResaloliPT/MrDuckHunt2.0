package com.academiadecodigo.hashtronauts.components.GameObjects.targets;

import com.badlogic.gdx.graphics.Texture;

public enum TargetType {
    DUCK(30, 200, "enemies/duck_left.png", "enemies/duck_right.png");

    private double health;
    private Texture imageLeft;
    private Texture imageRight;
    private int velocity;

    TargetType(double health, int velocity, String imageLeft, String imageRight) {
        this.health = health;
        this.imageLeft = new Texture(imageLeft);
        this.imageRight = new Texture(imageRight);
        this.velocity = velocity;
    }

    public double getHealth() {
        return health;
    }

    public Texture getImgLeft() {
        return imageLeft;
    }

    public Texture getImgRight() {
        return imageRight;
    }

    public int getVelocity() {
        return velocity;
    }
}
