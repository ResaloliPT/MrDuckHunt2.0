package com.academiadecodigo.hashtronauts.components.GameObjects.targets;

public interface Target {

    /**
     * Hits this target for {@param damage}
     *
     * @param damage the damage to hit this target
     * @return true if killed
     */
    boolean hit(double damage);
}
