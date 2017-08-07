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

}