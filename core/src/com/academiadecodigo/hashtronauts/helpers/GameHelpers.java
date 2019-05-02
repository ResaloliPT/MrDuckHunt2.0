package com.academiadecodigo.hashtronauts.helpers;

import com.academiadecodigo.hashtronauts.GameState;

public class GameHelpers {

    private static EnemyHelper enemyHelper = EnemyHelper.getInstance();

    private static PlayerHelper playerHelper = PlayerHelper.getInstance();

    private static ControlHelper controlHelper = ControlHelper.getInstance();

    private static ScreenHelper screenHelper = ScreenHelper.getInstance();

    public static EnemyHelper getEnemyHelper() {
        return enemyHelper;
    }

    public static PlayerHelper getPlayerHelper() {return playerHelper;}

    public static ControlHelper getControlHelper() {return controlHelper;}

    public static ScreenHelper getScreenHelper() {return  screenHelper;}

    private static GameState gameState;

    public static GameState getGameState(){
        return gameState;
    }

    public static void setGameState(GameState gameStateIn){
        gameState = gameStateIn;
    }
}
