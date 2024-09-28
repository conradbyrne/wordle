package edu.virginia.cs.hw2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private GameState testGame;

    @Test
    public void testConstructorWithIllegalAnswer() {
        assertThrows(IllegalWordException.class,
                () -> new GameState("QZXYX"));
    }

    @Test
    public void gameOver() {
        DefaultDictionaryFactory factory = new DefaultDictionaryFactory();
        WordleDictionary answersDictionary = factory.getDefaultAnswersDictionary();
        GameState gameState = new GameState("HAPPY", answersDictionary, 0, GameState.GameStatus.PLAYING);

        gameState.submitGuess("HELLO"); gameState.submitGuess("GRASS"); gameState.submitGuess("TIGER");
        gameState.submitGuess("CLASS"); gameState.submitGuess("DRONE"); gameState.submitGuess("AUDIO");

        assertTrue(gameState.isGameOver());
    }

    @Test
    public void gameWon() {
        DefaultDictionaryFactory factory = new DefaultDictionaryFactory();
        WordleDictionary answersDictionary = factory.getDefaultAnswersDictionary();
        GameState gameState = new GameState("HAPPY", answersDictionary, 0, GameState.GameStatus.PLAYING);

        gameState.submitGuess("HELLO"); gameState.submitGuess("HAPPY"); gameState.submitGuess("TIGER");
        gameState.submitGuess("CLASS"); gameState.submitGuess("DRONE"); gameState.submitGuess("GLARE");

        assertTrue(gameState.isWin());
    }

    @Test
    public void gameLost() {
        DefaultDictionaryFactory factory = new DefaultDictionaryFactory();
        WordleDictionary answersDictionary = factory.getDefaultAnswersDictionary();
        GameState gameState = new GameState("HAPPY", answersDictionary, 0, GameState.GameStatus.PLAYING);

        gameState.submitGuess("HELLO"); gameState.submitGuess("GRASS"); gameState.submitGuess("TIGER");
        gameState.submitGuess("CLASS"); gameState.submitGuess("DRONE"); gameState.submitGuess("STARE");

        assertTrue(gameState.isLoss());
    }

    @Test
    public void invalidGuess() {
        DefaultDictionaryFactory factory = new DefaultDictionaryFactory();
        WordleDictionary answersDictionary = factory.getDefaultAnswersDictionary();
        GameState gameState = new GameState("HAPPY", answersDictionary, 0, GameState.GameStatus.PLAYING);

        assertThrows(IllegalWordException.class, () -> gameState.submitGuess("ddd"));
    }

    @Test
    public void gameAlreadyOver() {
        DefaultDictionaryFactory factory = new DefaultDictionaryFactory();
        WordleDictionary answersDictionary = factory.getDefaultAnswersDictionary();
        GameState gameState = new GameState("HAPPY", answersDictionary, 0, GameState.GameStatus.PLAYING);

        gameState.submitGuess("HELLO"); gameState.submitGuess("GRASS"); gameState.submitGuess("TIGER");
        gameState.submitGuess("CLASS"); gameState.submitGuess("DRONE"); gameState.submitGuess("STARE");
        gameState.submitGuess("CLAIM");

        assertThrows(GameAlreadyOverException.class, () -> gameState.submitGuess("FLAKE"));
    }


}