package com.academiadecodigo.hashtronauts.helpers;

public class ControlHelper {
    public static final char PAUSE_GAME = 'p';
    public static final char FORCE_QUIT_KEY = 'q';
    public static final char SPAWN_ENEMIES = 'u';
    public static final char RELOAD_WEAPON = 'r';

    private static ControlHelper instance = new ControlHelper();

    private ControlHelper(){}

    static ControlHelper getInstance() {
        return instance;
    }
}
