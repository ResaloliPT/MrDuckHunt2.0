package com.academiadecodigo.hashtronauts.helpers;

import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.factories.TargetFactory;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.HashSet;
import java.util.Set;

public class EnemyHelper {

    private static EnemyHelper instance = new EnemyHelper();
    private Set<Target> targets;

    private EnemyHelper() {
        targets = new HashSet<Target>();
    }

    static EnemyHelper getInstance() {
        return instance;
    }

    public void moveTargets() {
        for (Target target : targets) {
            target.move();
        }
    }

    public void drawTargets(SpriteBatch batch) {
        for (Target target : targets) {
            target.draw(batch);
        }
    }

    public void disposeTargets() {
        for (Target target : targets) {
            target.dispose();
        }
    }

    public Target spawnRandomEnemy() {
        Target newTarget = TargetFactory.createEnemy();

        targets.add(newTarget);

        return newTarget;
    }

    /**
     * Checks if mouse was clicked on a target
     *
     * @param position the position of the click
     * @return target clicked at
     */
    public Target checkMouseClick(Vector2 position) {
        for (Target target : targets) {
            if (isInsideBoundaries(target, position)) {
                return target;
            }
        }

        return null;
    }

    private boolean isInsideBoundaries(Target target, Vector2 position) {
        Rectangle targetRectangle = target.getRectangle();

        return targetRectangle.contains(position);
    }

    public void destroyTarget(Target targetHit) {
        targets.remove(targetHit);

        targetHit.dispose();
    }
}
