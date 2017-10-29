package ua.kotlin.demo.tries

import java.util.*
import kotlin.coroutines.experimental.buildIterator

const val ALPHABET_SIZE = 26

class RWayTrie : Trie {

    var size = 0
        private set
    var root = Node()
        private set

    class Node(var value: Int? = null, var nodes: Array<Node?> = Array(ALPHABET_SIZE, { null }))

    override fun add(word: Pair<String, Int?>) {
        val key = word.first
        var x = root
        key.toCharArray().map(this::toIndex).forEach {
            if (x.nodes[it] == null)
                x.nodes[it] = Node()

            x = x.nodes[it] as Node
        }
        x.value = word.second

        size++
    }

    override fun contains(word: String): Boolean {
        var x = root
        word.toCharArray().map(this::toIndex).forEach {
            if (x.nodes[it] == null)
                return false

            x = x.nodes[it] as Node
        }
        return x.value != null
    }

    private fun toIndex(it: Char) = it - 'a'

    override fun delete(word: String) {
        root = delete(root, word, 0) ?: Node()
    }

    private fun delete(x: Node?, key: String, d: Int): Node? {
        x ?: return null
        if (d == key.length) {
            if (x.value != null)
                size--
            x.value = null
        } else {
            val index = toIndex(key[d])
            x.nodes[index] = delete(x.nodes[index], key, d + 1)
        }

        if (x.value != null)
            return x

        return if ((0 until ALPHABET_SIZE).any { x.nodes[it] != null }) x else null
    }

    override fun words(): Iterable<String> {
        val result = mutableListOf<String>()
        val toVisit = ArrayDeque<BfsNode>()

        return Iterable { bfs(root, -1, toVisit, "", result) }
    }

    override fun wordsWithPrefix(pref: String): Iterable<String> {
        var x = root
        var index = -1
        pref.forEach { c ->
            if (x.nodes[toIndex(c)] != null) {
                index = toIndex(c)
                x = x.nodes[index] as Node
            } else
                return emptyList()
        }
        val result = mutableListOf<String>()
        val toVisit = ArrayDeque<BfsNode>()

//        bfs(x, index, toVisit, pref.dropLast(1), result)
        return Iterable { bfs(x, index, toVisit, pref.dropLast(1), result) }
    }

    private data class BfsNode(val node: Node, val index: Int, val prefix: String)

    private fun bfs(node: Node, index: Int, toVisit: Queue<BfsNode>, prefix: String, words: MutableList<String>) = buildIterator {
        var word = if (index == -1) "" else prefix + ('a' + index)
        if (node.value != null) {
            yield(word)
            words.add(word)
        }

        (0 until ALPHABET_SIZE)
                .filter { node.nodes[it] != null }
                .forEach{ toVisit.offer(BfsNode(node.nodes[it]!!, it, word)) }

        while (toVisit.isNotEmpty()) {
            val (curNode, curIndex, curPrefix) = toVisit.poll()

            word = if (curIndex == -1) "" else curPrefix + ('a' + curIndex)
            if (curNode.value != null) {
                yield(word)
                words.add(word)
            }

            (0 until ALPHABET_SIZE)
                    .filter { curNode.nodes[it] != null }
                    .forEach{ toVisit.offer(BfsNode(curNode.nodes[it]!!, it, word)) }
        }
    }

}