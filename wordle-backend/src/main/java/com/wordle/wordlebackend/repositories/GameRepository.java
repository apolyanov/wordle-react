package com.wordle.wordlebackend.repositories;

import com.wordle.wordlebackend.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
