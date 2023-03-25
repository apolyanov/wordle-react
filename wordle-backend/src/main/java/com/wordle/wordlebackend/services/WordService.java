package com.wordle.wordlebackend.services;

import com.wordle.wordlebackend.models.Word;
import com.wordle.wordlebackend.repositories.WordRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class WordService {

    private WordRepository wordRepository;
    private List<Word> wordSet;

    public List<Word> getAllWords() {
        return wordSet;
    }

    public Iterable<Word> saveAllWords(@RequestBody Iterable<Word> words) {
        wordSet.addAll(StreamSupport.stream(words.spliterator(), false).collect(Collectors.toSet()));
        return wordRepository.saveAll(words);
    }

    @PostConstruct
    public void init() {
        wordSet = StreamSupport.stream(wordRepository.findAll().spliterator(), false).toList();
    }
}
