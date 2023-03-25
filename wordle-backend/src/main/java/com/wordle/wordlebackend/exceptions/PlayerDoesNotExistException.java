package com.wordle.wordlebackend.exceptions;

public class PlayerDoesNotExistException extends Exception {
    public PlayerDoesNotExistException(String message) {
        super(message);
    }
}
