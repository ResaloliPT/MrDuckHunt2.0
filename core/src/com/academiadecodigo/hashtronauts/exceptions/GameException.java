package com.academiadecodigo.hashtronauts.exceptions;

/**
 * Game Exception for game occurrences to be handled (not errors!)
 */
public abstract class GameException extends Exception {

    public GameException(String message) {
        super(message);
    }
}
