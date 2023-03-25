package com.wordle.wordlebackend.services.utils;

import com.wordle.wordlebackend.dtos.Guess;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class GuessHelper {
    private final char PLACE_MATCH = 'P';
    private final char LETTER_MATCH = 'L';
    private final char NO_MATCH = 'N';
    public Guess checkGuess(String guessWord, String gameWord, String gameId) {
        char[] matchedWord = new char[guessWord.length()];
        char[] guessWordArr = guessWord.toCharArray();
        char[] gameWordArr = gameWord.toCharArray();

        for (int i = 0; i < guessWord.length(); i++) {
            if (guessWordArr[i] == gameWordArr[i]) {
                matchedWord[i] = PLACE_MATCH;
            } else if(hasLetterMatch(gameWord, guessWordArr, i)) {
                matchedWord[i] = LETTER_MATCH;
            } else {
                matchedWord[i] = NO_MATCH;
            }
        }

        return new Guess(gameId, guessWord, new String(matchedWord), LocalDateTime.now());
    }

    private boolean hasLetterMatch(String gameWord, char[] guessWordArr, int index) {
        return gameWord.chars().mapToObj(c -> (char) c).toList().contains(guessWordArr[index]);
    }
}
