package com.wordle.wordlebackend.services;

import com.wordle.wordlebackend.dtos.CreatePlayerRequest;
import com.wordle.wordlebackend.dtos.PlayerLoginRequest;
import com.wordle.wordlebackend.exceptions.PlayerAlreadyExistsException;
import com.wordle.wordlebackend.exceptions.PlayerDoesNotExistException;
import com.wordle.wordlebackend.models.Player;
import com.wordle.wordlebackend.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepository;

    public Player createPlayer(CreatePlayerRequest createPlayerRequest) throws PlayerAlreadyExistsException {
        if (!playerExist(createPlayerRequest.getUsername())) {
            String playerUuid = UUID.randomUUID().toString();
            Player newPlayer = new Player();
            newPlayer.setUsername(createPlayerRequest.getUsername());
            newPlayer.setToken(playerUuid);

            return playerRepository.save(newPlayer);
        } else {
            throw new PlayerAlreadyExistsException("Player already exists!");
        }
    }

    public Player loginPlayer(PlayerLoginRequest playerLoginRequest) throws PlayerDoesNotExistException {
        Optional<Player> optionalPlayer = playerRepository.findByUsername(playerLoginRequest.getUsername());

        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        } else {
            throw new PlayerDoesNotExistException("Player does not exist!");
        }
    }

    public Player addPlayerToGame(String playerToken, String gameId) throws PlayerDoesNotExistException {
        Optional<Player> optionalPlayer = playerRepository.findByToken(playerToken);

        if (optionalPlayer.isPresent()) {
            optionalPlayer.get().setGameId(gameId);

            return playerRepository.save(optionalPlayer.get());
        } else {
            throw new PlayerDoesNotExistException("Player does not exist");
        }
    }

    private boolean playerExist(String username) {
        return playerRepository.existsByUsername(username);
    }
}
