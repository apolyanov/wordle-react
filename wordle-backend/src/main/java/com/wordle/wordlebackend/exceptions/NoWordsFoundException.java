package com.wordle.wordlebackend.exceptions;

public class NoWordsFoundException extends Exception {
    public NoWordsFoundException(String message) {
        super(message);
    }
}
