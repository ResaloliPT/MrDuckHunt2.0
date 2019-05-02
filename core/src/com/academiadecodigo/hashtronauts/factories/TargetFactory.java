package com.academiadecodigo.hashtronauts.factories;

import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Duck;
import com.academiadecodigo.hashtronauts.components.GameObjects.targets.Target;
import com.academiadecodigo.hashtronauts.components.GameObjects.targets.TargetType;

public class TargetFactory {

    public static Target createEnemy() {

        TargetType targetType = TargetType.values()[(int) (Math.random() * TargetType.values().length)];

        Target newTarget;

        switch (targetType) {
            case DUCK:
            default:
                newTarget = new Duck();
        }

        return newTarget;
    }
}
