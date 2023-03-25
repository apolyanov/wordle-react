package com.wordle.wordlebackend.controllers;

import com.wordle.wordlebackend.dtos.Response;
import com.wordle.wordlebackend.models.Word;
import com.wordle.wordlebackend.services.WordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WordController {

    private WordService wordService;

    @PostMapping("/word/save-all")
    public ResponseEntity<Response<Iterable<Word>>> saveAll(@RequestBody Iterable<Word> words) {
        try {
            return new ResponseEntity<>(new Response<>(HttpStatus.OK.value(), "Words saved successfully!", wordService.saveAllWords(words)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new Response<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
