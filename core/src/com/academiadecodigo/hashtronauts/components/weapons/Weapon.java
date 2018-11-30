package com.academiadecodigo.hashtronauts.components.weapons;

import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.exceptions.MissedShoot;
import com.academiadecodigo.hashtronauts.exceptions.NotEnoughAmmo;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Weapon {
    /**
     * Shoots the selected target
     *
     * @param target the target to shot at
     * @return true if killed
     */
    boolean shoot(Target target) throws MissedShoot, NotEnoughAmmo;

    /**
     * Returns the current amount of ammo
     *
     * @return the amount of ammo
     */
    int getBulletCount();

    /**
     * Used to dispose weapon resources (eventual images, sounds etc)
     */
    void dispose();

    /**
     * Renders the weapon
     *
     * @param batch the Sprite bach to render on
     */
    void renderWeapon(SpriteBatch batch);

    /**
     * Reloads the weapon
     *
     * @throws NotEnoughAmmo thrown if not enough ammo
     */
    void reload() throws NotEnoughAmmo;
}
