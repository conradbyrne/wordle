package edu.virginia.cs.hw2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultDictionaryFactoryTest {
    DefaultDictionaryFactory testFactory;

    @BeforeEach
    public void setupTestFactory() {
        testFactory = new DefaultDictionaryFactory();
    }

    @Test
    public void testGetDefaultGuessesDictionary() {
        WordleDictionary defaultGuessesDictionary = testFactory.getDefaultGuessesDictionary();
        assertNotNull(defaultGuessesDictionary);
        assertEquals(15920, defaultGuessesDictionary.getDictionarySize());
    }
    @Test
    public void testGetDefaultAnswerDictionary() {
        WordleDictionary defaultAnswersDictionary = testFactory.getDefaultAnswersDictionary();
        assertNotNull(defaultAnswersDictionary);
        assertEquals(2315, defaultAnswersDictionary.getDictionarySize());
    }
}
