package com.wordle.wordlebackend.repositories;

import com.wordle.wordlebackend.models.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Optional<Player> findByToken(String token);
    Optional<Player> findByUsername(String username);
    boolean existsByUsername(String username);
}
