package com.academiadecodigo.hashtronauts.helpers;

import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.components.Score;
import com.academiadecodigo.hashtronauts.components.weapons.Weapon;
import com.academiadecodigo.hashtronauts.exceptions.MissedShoot;
import com.academiadecodigo.hashtronauts.exceptions.NotEnoughAmmo;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerHelper {

    private Weapon weapon;

    private Score score = new Score();

    private static PlayerHelper instance = new PlayerHelper();

    private PlayerHelper(){

    }

    static PlayerHelper getInstance() {
        return instance;
    }

    public void resetScore() {
        score.changeScore(-score.getScore());
    }

    public void addScore(int score){
        this.score.changeScore(score);
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        weapon.renderWeapon(batch, camera);
        score.draw(batch);
    }

    public void dispose() {
        score.dispose();
        weapon.dispose();
    }

    public boolean reload() throws NotEnoughAmmo {
        return weapon.reload();
    }

    public boolean shoot(Target targetHit) throws NotEnoughAmmo, MissedShoot {
        return weapon.shoot(targetHit);
    }

    public boolean isWeaponEmpty() {
        return weapon.getBulletCount() == 0 && weapon.getClipCount() == 0;
    }
}
