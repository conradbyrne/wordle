package edu.virginia.cs.hw2;

public class GameState {

    public static final int MAX_GUESSES = 6;
    private String answer;
    private int guessCount;
    private GameStatus gameStatus;
    private WordleDictionary legalGuessDictionary;


    protected GameState(String answer, WordleDictionary dictionary, int guessCount, GameStatus status) {
        this.answer = answer.toUpperCase();
        this.legalGuessDictionary = dictionary;
        this.guessCount = guessCount;
        this.gameStatus = status;
        if (0 == legalGuessDictionary.getDictionarySize()) {
            throw new EmptyDictionaryException("Error: Cannot play Wordle with an Empty Dictionary");
        }
        if (!legalGuessDictionary.containsWord(answer)) {
            throw new IllegalWordException(
                    "Created a game with an illegal answer not found in the dictionary: " + answer);
        }
    }

    public GameState(String answer, WordleDictionary dictionary) {
        this(answer, dictionary, 0, GameStatus.PLAYING);
    }

    public GameState(String answer) {
        this(answer, getDefaultGuessesDictionary(), 0, GameStatus.PLAYING);
    }

    private static WordleDictionary getDefaultGuessesDictionary() {
        DefaultDictionaryFactory factory = new DefaultDictionaryFactory();
        return factory.getDefaultGuessesDictionary();
    }

    public GameState() {
        this(getRandomAnswerFromDefaultDictionary(), getDefaultGuessesDictionary(), 0, GameStatus.PLAYING);
    }

    private static String getRandomAnswerFromDefaultDictionary() {
        DefaultDictionaryFactory factory = new DefaultDictionaryFactory();
        WordleDictionary answersDictionary = factory.getDefaultAnswersDictionary();
        return answersDictionary.getRandomWord();
    }

    public boolean isGameOver() {
        if (getRemainingGuesses() < 1) {return true;}
        if (isWin()) {return true;}
        if (isLoss()) {return true;}

        return GameStatus.PLAYING != gameStatus;
    }

    public boolean isWin() {
        return GameStatus.WON == gameStatus;
    }

    public boolean isLoss() {
        return GameStatus.LOST == gameStatus;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public int getRemainingGuesses() {
        return MAX_GUESSES - guessCount;
    }

    public String getAnswer() {
        return answer;
    }

    public LetterResult[] submitGuess(String guess) {

        GuessResult guessResult = new GuessResult();
        guessResult.setGuess(guess);
        guessResult.setAnswer(answer);


        //TODO: Stub - Implement method with TDD - tests must go in GameTest.java

        //if someone takes a 7th guess
        if (guessCount > MAX_GUESSES) {
            throw new GameAlreadyOverException("The game is already over.");
        }

        guessCount++; //increments num of guesses

        //win game if answer is correct
       if (guess.toUpperCase().equals(answer) && gameStatus == GameStatus.PLAYING) {
        gameStatus = GameStatus.WON;}

        //lose game if 6 guesses pass and none are correct
            if (guessCount == 6 && !guess.toUpperCase().equals(answer) && gameStatus == GameStatus.PLAYING){
            gameStatus = GameStatus.LOST;
        }

        //throws exception if guess isn't in dictionary
        if (!legalGuessDictionary.containsWord(guess)) {
            guessCount--;
            throw new IllegalWordException("Invalid guess: " + guess);
        }



        return guessResult.getGuessResult();
    }


    protected enum GameStatus {
        PLAYING, WON, LOST;
    }

}
