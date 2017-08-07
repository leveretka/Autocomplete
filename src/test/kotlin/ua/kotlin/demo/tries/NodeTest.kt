package ua.kotlin.demo.tries

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class NodeTest{

    @Test
    fun shouldCreateEmptyNode() {
        val node = RWayTrie.Node()
        assertNull(node.value)
        assertEquals(ALPHABET_SIZE, node.nodes.size)
        assertTrue (node.nodes.all { it == null })
    }

}