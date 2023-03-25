package com.wordle.wordlebackend.repositories;

import com.wordle.wordlebackend.models.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {
}
