package ua.kotlin.demo.tries

import java.util.*

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

    override fun words() = findWordsWithPrefix(root, -1, "")

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
        return findWordsWithPrefix(x, index, pref.dropLast(1))
    }

    private fun findWordsWithPrefix(node: Node, index: Int, prefix: String) : Iterable<String> {
        val result = mutableListOf<String>()
        val toVisit = ArrayDeque<BfsNode>()
        toVisit.add(BfsNode(node, index, prefix))
        return Iterable { bfs(toVisit, result) }
    }

    private data class BfsNode(val node: Node, val index: Int, val prefix: String)

    private fun bfs(toVisit: Queue<BfsNode>, words: MutableList<String>) = iterator {

        while (toVisit.isNotEmpty()) {
            val (curNode, curIndex, curPrefix) = toVisit.poll()

            val word = if (curIndex == -1) "" else curPrefix + ('a' + curIndex)
            if (curNode.value != null) {
                println("Found word: $word")
                yield(word)
            }

            (0 until ALPHABET_SIZE)
                    .filter { curNode.nodes[it] != null }
                    .forEach{ toVisit.offer(BfsNode(curNode.nodes[it]!!, it, word)) }
        }
    }

}