package com.academiadecodigo.hashtronauts.components.weapons;

public enum WeaponType {
    SHOTGUN(0, 3, 4, 30.0, "Shotgun");

    private float missRate;
    private int ammoPerClip;
    private int clips;
    private double damage;
    private String title;

    WeaponType(float missRate, int ammoPerClip, int clips, double damage, String title) {
        this.missRate = missRate;
        this.ammoPerClip = ammoPerClip;
        this.clips = clips;
        this.damage = damage;
        this.title = title;
    }

    public float getMissRate() {
        return missRate;
    }

    public int getAmmoPerClip() {
        return ammoPerClip;
    }

    public int getClips() {
        return clips;
    }

    public double getDamage() {
        return damage;
    }

    public String getTitle() {
        return title;
    }
}
