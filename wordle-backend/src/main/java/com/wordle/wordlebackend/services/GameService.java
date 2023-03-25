package com.wordle.wordlebackend.services;

import com.wordle.wordlebackend.dtos.CreateGameRequest;
import com.wordle.wordlebackend.dtos.Guess;
import com.wordle.wordlebackend.exceptions.MaxGuessesExceededException;
import com.wordle.wordlebackend.exceptions.NoWordsFoundException;
import com.wordle.wordlebackend.exceptions.PlayerAlreadyExistsException;
import com.wordle.wordlebackend.exceptions.PlayerDoesNotExistException;
import com.wordle.wordlebackend.models.ActiveGame;
import com.wordle.wordlebackend.models.Game;
import com.wordle.wordlebackend.repositories.GameRepository;
import com.wordle.wordlebackend.services.utils.GuessHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepository gameRepository;
    private WordService wordService;
    private PlayerService playerService;
    private GuessHelper guessHelper;
    private HashMap<String, ActiveGame> activeGames;

    public Game createGame(CreateGameRequest createGameRequest) throws NoWordsFoundException, PlayerDoesNotExistException {
        Game newGame = new Game();

        String gameId = UUID.randomUUID().toString();
        String wordToGuess;

        try {
            wordToGuess = getRandomWord();
        } catch (NoWordsFoundException e) {
            throw new NoWordsFoundException(e.getMessage());
        }

        newGame.setGameId(gameId);
        newGame.setWordToGuess(wordToGuess);
        newGame.setActive(true);

        try {
            playerService.addPlayerToGame(createGameRequest.getPlayerToken(), gameId);
        } catch (PlayerDoesNotExistException e) {
            throw new PlayerDoesNotExistException(e.getMessage());
        }

        return gameRepository.save(newGame);
    }

    public String makeGuess(Guess guess) throws MaxGuessesExceededException {
        if (activeGames.get(guess.getGameId()).getGuessList().size() >= 5) {
            throw new MaxGuessesExceededException("Maximum number of guesses exceeded!");
        }
        activeGames.get(guess.getGameId()).getGuessList().add(guess);
        Guess newGuess = guessHelper.checkGuess(guess.getGuessWord(), activeGames.get(guess.getGameId()).getGame().getWordToGuess(), guess.getGameId());

        return newGuess.getMatchedWord();
    }

    private String getRandomWord() throws NoWordsFoundException {
        if (wordService.getAllWords().size() > 0) {
            return wordService.getAllWords().get(new Random().nextInt(0, wordService.getAllWords().size())).getValue();
        } else {
            throw new NoWordsFoundException("There are no words in the database!");
        }
    }
}
