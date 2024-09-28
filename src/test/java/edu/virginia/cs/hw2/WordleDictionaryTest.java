package edu.virginia.cs.hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class WordleDictionaryTest {
    private static final String ONE_WORD_DICTIONARY_FILENAME = "/one_word_dictionary.txt";
    private static final String TWO_WORD_DICTIONARY_FILENAME = "/two_word_dictionary.txt";
    private static final String DICTIONARY_FILENAME = "/dictionary.txt";
    private static final String test_word = "BALDY";
    WordleDictionary testDictionary;

    @BeforeEach
    public void setupTestDictionary() {
        testDictionary = new WordleDictionary();
    }

    @Test
    public void testisLegalWordleWord() {
        assertTrue(testDictionary.isLegalWordleWord(test_word));
        assertTrue(testDictionary.isLegalWordleWord(test_word.toLowerCase()));
    }

    @Test
    public void testAddWord() {
        assertThrows(IllegalWordException.class, ()->testDictionary.addWord("A"));
        assertDoesNotThrow(()->testDictionary.addWord(test_word));
        assertDoesNotThrow(()->testDictionary.addWord(test_word.toLowerCase()));
    }


    @Test
    public void testOneWordDictionary() {
        InputStream inputStream = WordleDictionaryTest.class.getResourceAsStream(ONE_WORD_DICTIONARY_FILENAME);
        testDictionary.addWordsFromInputStream(inputStream);
        assertEquals(1, testDictionary.getDictionarySize());
        assertTrue(testDictionary.containsWord(test_word));
    }

    @Test
    public void testTwoWordDictionary() {
        InputStream inputStream = WordleDictionaryTest.class.getResourceAsStream(TWO_WORD_DICTIONARY_FILENAME);
        testDictionary.addWordsFromInputStream(inputStream);
        assertEquals(2, testDictionary.getDictionarySize());
        assertTrue(testDictionary.containsWord(test_word));
        assertTrue(testDictionary.containsWord("bacon"));
        assertTrue(testDictionary.containsWord("BACON"));
        assertFalse(testDictionary.containsWord("bee"));
    }

    @Test
    public void testFullDictionary() {
        InputStream inputStream = WordleDictionaryTest.class.getResourceAsStream(DICTIONARY_FILENAME);
        testDictionary.addWordsFromInputStream(inputStream);
        assertEquals(15920, testDictionary.getDictionarySize());
        assertTrue(testDictionary.containsWord(test_word));
        assertTrue(testDictionary.containsWord("abase"));
        assertTrue(testDictionary.containsWord("ABASE"));
        assertFalse(testDictionary.containsWord("bee"));
    }


}
