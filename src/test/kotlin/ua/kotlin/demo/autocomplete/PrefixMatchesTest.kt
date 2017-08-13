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

}