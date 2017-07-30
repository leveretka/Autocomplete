package ua.kotlin.demo.tries

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by leveret on 30.07.17.
 */
class RWayTrieTest {
    val rWayTrie = RWayTrie()

    @Test
    fun shouldReturnZeroSizeForEmptyTrie() {
        assertEquals(0, rWayTrie.size())
    }
}