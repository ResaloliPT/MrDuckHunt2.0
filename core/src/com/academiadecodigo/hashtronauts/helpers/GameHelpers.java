package com.academiadecodigo.hashtronauts.helpers;

public class GameHelpers {

    private static EnemyHelper enemyHelper = EnemyHelper.getInstance();

    public static EnemyHelper getEnemyHelper() {
        return enemyHelper;
    }
}
