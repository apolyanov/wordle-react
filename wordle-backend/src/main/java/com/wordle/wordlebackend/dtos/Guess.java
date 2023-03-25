package com.wordle.wordlebackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Guess {
    private String gameId;
    private String guessWord;
    private String matchedWord;
    private LocalDateTime madeOn;
}
