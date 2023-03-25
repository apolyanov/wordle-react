package com.wordle.wordlebackend.exceptions;

public class MaxGuessesExceededException extends Exception{
    public MaxGuessesExceededException(String message) {
        super(message);
    }
}
