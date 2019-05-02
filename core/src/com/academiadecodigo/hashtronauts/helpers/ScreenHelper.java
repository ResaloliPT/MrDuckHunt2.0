package com.academiadecodigo.hashtronauts.helpers;

import com.academiadecodigo.hashtronauts.screens.GameScreen;

public class ScreenHelper {
    private static ScreenHelper instance = new ScreenHelper();

    public static ScreenHelper getInstance() {
        return instance;
    }

    private ScreenHelper() {
    }

    public GameScreen getGameScreen() {
        return GameScreen.getInstance();
    }

    public void resetGame(){
        GameScreen.reset();
    }
}
