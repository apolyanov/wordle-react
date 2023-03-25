package com.wordle.wordlebackend.controllers;

import com.wordle.wordlebackend.dtos.CreateGameRequest;
import com.wordle.wordlebackend.dtos.Guess;
import com.wordle.wordlebackend.dtos.Response;
import com.wordle.wordlebackend.exceptions.MaxGuessesExceededException;
import com.wordle.wordlebackend.exceptions.NoWordsFoundException;
import com.wordle.wordlebackend.exceptions.PlayerDoesNotExistException;
import com.wordle.wordlebackend.models.Game;
import com.wordle.wordlebackend.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GameController {

    private GameService gameService;

    @PostMapping("/game/create")
    public ResponseEntity<Response<Game>> createGame(@RequestBody CreateGameRequest createGameRequest) {
        try {
            return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "Game created successfully", gameService.createGame(createGameRequest)), HttpStatus.OK);
        } catch (PlayerDoesNotExistException | NoWordsFoundException e) {
            return new ResponseEntity<>(new Response<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/game/make-guess")
    public ResponseEntity<Response<String>> makeGuess(@RequestBody Guess guess) {
        try {
            return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "Your guess was recorded!", gameService.makeGuess(guess)), HttpStatus.OK);
        } catch (MaxGuessesExceededException e) {
            return new ResponseEntity<>(new Response<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
