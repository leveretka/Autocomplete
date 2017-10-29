package ua.kotlin.demo.autocomplete

import org.junit.Test
import ua.kotlin.demo.tries.RWayTrie
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PrefixMatchesTest {
    private val prefixMatches = PrefixMatches(RWayTrie())

    @Test
    fun shouldReturnFalseWhenSearchInEmptyDictionary() {
        assertFalse(prefixMatches.contains(""))
    }

    @Test
    fun shouldReturnTrueWhenSearchForExistentWordInDictionary() {
        prefixMatches.load("abc")
        assertTrue(prefixMatches.contains("abc"))
    }

    @Test
    fun shouldLoadSomeWordsIntoDictionary() {
        assertEquals(4, prefixMatches.load("abc", "dsag", "fdgbkdg", "hhh"))
    }

    @Test
    fun shouldDeleteExistingWordFromDictionary() {
        prefixMatches.load("abc")
        prefixMatches.delete("abc")
        assertFalse(prefixMatches.contains("abc"))
    }

    @Test
    fun shouldReturnZeroSizeForEmptyDictionary() {
        assertEquals(0, prefixMatches.getSize())
    }

    @Test
    fun shouldReturnNumberOfWordsSInDictionary() {
        prefixMatches.load("abc", "dsag", "fdgbkdg", "hhh")
        assertEquals(4, prefixMatches.getSize())
    }

    @Test
    fun shouldReturnEmptyListForEmptyDictionary() {
        assertEquals(emptyList(), prefixMatches.wordsWithPrefix("aaa"))
    }

    @Test
    fun shouldReturnEmptyListForShorterThan2SymbolsPrefix() {
        prefixMatches.load("a", "ab", "ac", "abc")
        assertEquals(emptyList(), prefixMatches.wordsWithPrefix("a"))
    }

    @Test
    fun shouldReturnAllWordsWithGivenPrefix() {
        prefixMatches.load("ab", "abc", "abcd", "abcc")
        assertEquals(mutableListOf("ab", "abc", "abcc", "abcd"), prefixMatches.wordsWithPrefix("ab").toMutableList())
    }

    @Test
    fun shouldNotReturnWordsWithoutGivenPrefix() {
        prefixMatches.load("ab", "abc", "bbbb", "abcc")
        assertEquals(mutableListOf("ab", "abc", "abcc"), prefixMatches.wordsWithPrefix("ab").toMutableList())
    }

    @Test
    fun shouldReturnEmptyListForEmptyDictionaryWhenSearchForPrefixWithK() {
        assertEquals(emptyList(), prefixMatches.wordsWithPrefix("aaa", 3))
    }

    @Test
    fun shouldReturnEmptyListForShorterThan2SymbolsPrefixWhenSearchForPrefixWithK() {
        prefixMatches.load("a", "ab", "ac", "abc")
        assertEquals(emptyList(), prefixMatches.wordsWithPrefix("a", 3))
    }

    @Test
    fun shouldReturnWordsOfKdifferentLengthWithGivenPrefixWhenSearchWithK() {
        prefixMatches.load("abc", "abcd", "abcc", "abcss")
        assertEquals(mutableListOf("abc", "abcc", "abcd", "abcss"), prefixMatches.wordsWithPrefix("abc", 3))
    }

    @Test
    fun shouldNotReturnWordsWithLenngth2WithGivenPrefixWhenSearchWithK() {
        prefixMatches.load("ab", "abc", "abcd", "abcc", "abcss")
        assertEquals(mutableListOf("abc", "abcc", "abcd", "abcss"), prefixMatches.wordsWithPrefix("ab", 3))
    }

    @Test
    fun shouldNotReturnTooMuchWordsWithGivenPrefixWhenSearchWithK() {
        prefixMatches.load("ab", "abc", "abcd", "abcc", "abcss", "abcdsagsdfasf", "abcfdgdsdgdsd")
        assertEquals(mutableListOf("abc", "abcc", "abcd", "abcss"), prefixMatches.wordsWithPrefix("ab", 3))
    }

    @Test
    fun shouldNotReturnTooMuchWordsWithGivenPrefixWhenSearchWithKWhenSomeLengthAreMissing() {
        prefixMatches.load("ab", "abc", "abcd", "abcc", "abcssss", "abcdsagsdfasf", "abcfdgdsdgdsd")
        assertEquals(mutableListOf("abc", "abcc", "abcd", "abcssss"), prefixMatches.wordsWithPrefix("ab", 3))
    }

    @Test
    fun shouldReturnWordsWithGivenPrefixWhenSearchWithK1() {
        prefixMatches.load("ab", "abc", "abd", "abcd", "abcc", "abcssss", "abcdsagsdfasf", "abcfdgdsdgdsd")
        assertEquals(mutableListOf("abc", "abd"), prefixMatches.wordsWithPrefix("ab", 1))
    }
}