package com.academiadecodigo.hashtronauts;

public enum EndGameStates {
    OUTOFAMMO("Out of Ammo!"),
    EXITGAME("Exiting the Game.");

    private String message;

    private EndGameStates(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
