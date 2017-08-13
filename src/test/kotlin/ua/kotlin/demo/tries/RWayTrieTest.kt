package ua.kotlin.demo.tries

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RWayTrieTest {
    val rWayTrie = RWayTrie()

    @Test
    fun shouldReturnZeroSizeForEmptyTrie() {
        assertEquals(0, rWayTrie.size)
    }

    @Test
    fun shouldIncrementSizeWhenWordAdded() {
        val size = rWayTrie.size
        rWayTrie.add("a" to 1)
        assertEquals(size + 1, rWayTrie.size)
    }

    @Test
    fun shouldFindAddedWord() {
        rWayTrie.add("a" to 1)
        assertTrue (rWayTrie.contains("a"))
    }

    @Test
    fun shouldNotFindAbsentWord() {
        rWayTrie.add("a" to 1)
        assertFalse (rWayTrie.contains("aa"))
    }

    @Test
    fun shouldAddTwoWordsAtOneLevel() {
        rWayTrie.add("a" to 1)
        rWayTrie.add("b" to 1)

        val firstWord = rWayTrie.root.nodes[0]
        assertEquals(1, firstWord?.value)

        val secondWord = rWayTrie.root.nodes[1]
        assertEquals(1, secondWord?.value)
    }

    @Test
    fun shouldAddTwoWordsAtDifferentLevels() {
        rWayTrie.add("a" to 1)
        rWayTrie.add("ab" to 2)

        val firstWord = rWayTrie.root.nodes[0]
        assertEquals(1, firstWord?.value)

        val secondWord = firstWord?.nodes?.get(1)
        assertEquals(2, secondWord?.value)
    }

    @Test
    fun shouldDeleteWord() {
        rWayTrie.add("a" to 1)
        rWayTrie.delete("a")

        assertEquals(0, rWayTrie.size)
        assertFalse(rWayTrie.contains("a"))
    }

    @Test
    fun shouldDeleteUnnecessaryNodesWhenDeleteWord() {
        rWayTrie.add("a" to 1)
        rWayTrie.add("abcde" to 5)
        rWayTrie.delete("abcde")

        val firstWord = rWayTrie.root.nodes[0]

        firstWord?.nodes?.all { it == null }?.let { assertTrue(it)}
    }

    @Test
    fun shouldNotDeleteMissingWord() {
        rWayTrie.add("a" to 1)
        rWayTrie.add("abcde" to 5)
        rWayTrie.delete("abc")

        assertEquals(2, rWayTrie.size)
        assertFalse(rWayTrie.contains("abc"))
    }

    @Test
    fun shouldReturnEmptyListWhenNothingWasAdded() {
        assertEquals(0, rWayTrie.size)
        assertEquals(mutableListOf(), rWayTrie.words())
    }

    @Test
    fun shouldReturnOneExistingWord() {
        rWayTrie.add("a" to 1)

        assertEquals(1, rWayTrie.size)
        assertEquals(mutableListOf("a"), rWayTrie.words())
    }

    @Test
    fun shouldReturnAllExistingOneCharacterWord() {
        rWayTrie.add("a" to 1)
        rWayTrie.add("b" to 1)
        rWayTrie.add("c" to 1)
        rWayTrie.add("d" to 1)
        rWayTrie.add("e" to 1)
        rWayTrie.add("f" to 1)

        assertEquals(6, rWayTrie.size)
        assertEquals(mutableListOf("a", "b", "c", "d", "e", "f"), rWayTrie.words())
    }

    @Test
    fun shouldFindMultiCharacterWords() {
        rWayTrie.add("a" to 1)
        rWayTrie.add("bdsfsd" to 6)
        rWayTrie.add("c" to 1)
        rWayTrie.add("abcdef" to 6)

        assertEquals(4, rWayTrie.size)
        assertEquals(mutableListOf("a", "abcdef", "bdsfsd", "c"), rWayTrie.words())
    }

    @Test
    fun shouldReturnEmptyListWhenSearchWithPredixInEmptyTrie() {
        assertEquals(mutableListOf(), rWayTrie.wordsWithPrefix(""))
    }

    @Test
    fun shouldReturnEmptyListWhenWordsWithPredixNotFound() {
        rWayTrie.add("a" to 1)

        assertEquals(mutableListOf(), rWayTrie.wordsWithPrefix("b"))
    }

    @Test
    fun shouldReturnAllWordsForEmptyPrefix() {
        rWayTrie.add("a" to 1)
        rWayTrie.add("bc" to 2)

        assertEquals(mutableListOf("a", "bc"), rWayTrie.wordsWithPrefix(""))
    }

    @Test
    fun shouldReturnAllWordsForGivenPrefix() {
        rWayTrie.add("ab" to 2)
        rWayTrie.add("abc" to 3)

        assertEquals(mutableListOf("ab", "abc"), rWayTrie.wordsWithPrefix("a"))
    }

    @Test
    fun shouldNotReturnWordsWithoutGivenPrefix() {
        rWayTrie.add("ab" to 2)
        rWayTrie.add("abc" to 3)
        rWayTrie.add("bcd" to 3)
        rWayTrie.add("adfc" to 4)
        rWayTrie.add("dfv" to 3)
        rWayTrie.add("abcsfdg" to 7)
        rWayTrie.add("aerferg" to 7)
        rWayTrie.add("ccccc" to 5)

        assertEquals(mutableListOf("ab", "abc", "abcsfdg"), rWayTrie.wordsWithPrefix("ab"))
    }

}