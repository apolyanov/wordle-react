package com.wordle.wordlebackend.controllers;

import com.wordle.wordlebackend.dtos.CreatePlayerRequest;
import com.wordle.wordlebackend.dtos.PlayerLoginRequest;
import com.wordle.wordlebackend.dtos.Response;
import com.wordle.wordlebackend.exceptions.PlayerAlreadyExistsException;
import com.wordle.wordlebackend.exceptions.PlayerDoesNotExistException;
import com.wordle.wordlebackend.models.Player;
import com.wordle.wordlebackend.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;

    @PostMapping("/player/create")
    public ResponseEntity<Response<String>> createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest) {
        try {
            return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "Player has been created successfully!", playerService.createPlayer(createPlayerRequest).getToken()), HttpStatus.OK);
        } catch (PlayerAlreadyExistsException e) {
            return new ResponseEntity<>(new Response<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/player/login")
    public ResponseEntity<Response<Player>> loginPlayer(@RequestBody PlayerLoginRequest playerLoginRequest) {
        try {
            return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "Successfully logged in!", playerService.loginPlayer(playerLoginRequest)), HttpStatus.OK);
        } catch (PlayerDoesNotExistException e) {
            return new ResponseEntity<>(new Response<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
