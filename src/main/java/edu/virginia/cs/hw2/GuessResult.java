package edu.virginia.cs.hw2;

import java.util.Arrays;
import java.util.HashMap;

public class GuessResult {
    public static final int GUESS_RESULT_ARRAY_SIZE = 5;
    private final LetterResult[] guessResult =
            {LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY};
    private String answer; //always uppercase
    private String guess; //always uppercase

    private LetterResult[] result;
    private char[] guessCharArray;
    private char[] answerCharArray;
    private HashMap<Character, Integer> charFrequencies;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer.toUpperCase();
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess.toUpperCase();
    }

    public LetterResult[] getGuessResult() {
        verifyAllFieldsAreInitialized();
        if (guess.equals(answer)) {
            return getCorrectAnswerArray();
        }
        setAllGreenSquares();
        setAllYellowSquares();
        return result;
    }
    private void setAllGreenSquares()
    {
        result = guessResult;
        guessCharArray = guess.toCharArray();
        answerCharArray = answer.toCharArray();
        getCharFrequencies();
        for(int i = 0; i < GUESS_RESULT_ARRAY_SIZE; i++)
        {
            if(guessCharArray[i] == answerCharArray[i])
            {
                result[i] = LetterResult.GREEN;
                decreaseFreq(answerCharArray[i]);
            }
        }
    }
    private void setAllYellowSquares()
    {
        for(int i = 0; i < GUESS_RESULT_ARRAY_SIZE; i++)
        {
            char c = guessCharArray[i];
            if (isYellow(c, i))
            {
                result[i] = LetterResult.YELLOW;
                decreaseFreq(c);
            }
        }
    }
    private void decreaseFreq(char c)
    {
        charFrequencies.put(c, charFrequencies.get(c) - 1);
    }

    private boolean isYellow(char c, int index)
    {
        return !(guessResult[index].equals(LetterResult.GREEN)) &&
                charFrequencies.containsKey(c) && charFrequencies.get(c) > 0;
    }


    private void getCharFrequencies()
    {
        charFrequencies = new HashMap<Character, Integer>();
        for (char c: answerCharArray)
        {
            if(charFrequencies.containsKey(c))
                charFrequencies.put(c, charFrequencies.get(c) + 1);
            else
                charFrequencies.put(c, 1);
        }
    }

    private void verifyAllFieldsAreInitialized() {
        if (guess == null) {
            throw new IllegalStateException("The guess field in GuessResult must be initialized before calling getGuessResult");
        }
        if (answer == null) {
            throw new IllegalStateException("The guess field in GuessResult must be initialized before calling getGuessResult");
        }
    }

    private LetterResult[] getCorrectAnswerArray() {
        fillGuessResultArrayWithOneColor(LetterResult.GREEN);
        return guessResult;
    }

    private void fillGuessResultArrayWithOneColor(LetterResult letterResult) {
        Arrays.fill(guessResult, letterResult);
    }



}
