package com.academiadecodigo.hashtronauts.components.weapons;


import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.exceptions.MissedShoot;
import com.academiadecodigo.hashtronauts.exceptions.NotEnoughAmmo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Shotgun extends WeaponBase {

    private Sound shotSound;
    private Sound reloadSound;
    private Sound notEnoughAmmoSound;


    public Shotgun() {
        super(WeaponType.SHOTGUN, "weapons/shotgunSide.png");
        shotSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapons/shotgunShoot.wav"));
        reloadSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapons/shotgunReloading.mp3"));
        notEnoughAmmoSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapons/shotgunEmpty.wav"));
    }

    @Override
    public boolean shoot(Target target) {
        boolean killed;

        try {
            killed = super.shoot(target);
            shotSound.play();
        } catch (MissedShoot missedShoot) {
            return false;
        } catch (NotEnoughAmmo noAmmo) {
            notEnoughAmmoSound.play();
            return false;
        }

        return killed;
    }

    @Override
    public void reload() {
        try {
            super.reload();
            reloadSound.play();
        } catch (NotEnoughAmmo notEnoughAmmo) {
            notEnoughAmmoSound.play();
        }
    }
}
