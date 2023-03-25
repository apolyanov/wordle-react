package com.wordle.wordlebackend.models;

import com.wordle.wordlebackend.dtos.Guess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActiveGame {
    private Game game;
    private List<Guess> guessList;
}
