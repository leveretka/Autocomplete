package ua.kotlin.demo.tries

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class NodeTest{

    @Test
    fun shouldCreateEmptyNode() {
        val node = RWayTrie.Node()
        assertNull(node.value)
        assertEquals(26, node.nodes.size)
        node.nodes.forEach { assertNull(it) }
    }

}