package ua.kotlin.demo.tries

import java.util.*

const val ALPHABET_SIZE = 26

class RWayTrie : Trie {

    var size = 0
        private set
    var root = Node()
        private set

    class Node(var value: Int? = null, var nodes: Array<Node?> = Array<Node?>(ALPHABET_SIZE, { null }))

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

        return if ((0..ALPHABET_SIZE - 1).any { x.nodes[it] != null }) x else null
    }

    override fun words(): MutableIterable<String> {
        val result = mutableListOf<String>()
        val toVisit = ArrayDeque<BfsNode>()

        bfs(root, -1, toVisit, "", result)
        return result
    }

    override fun wordsWithPrefix(pref: String): MutableIterable<String> {
        var x = root
        var index = -1
        pref.forEach { c ->
            if (x.nodes[toIndex(c)] != null) {
                index = toIndex(c)
                x = x.nodes[index] as Node
            } else
                return mutableListOf()
        }
        val result = mutableListOf<String>()
        val toVisit = ArrayDeque<BfsNode>()

        bfs(x, index, toVisit, pref.dropLast(1), result)
        return result
    }

    private class BfsNode(val node: Node, val index: Int, val prefix: String)

    private fun bfs(node: Node, index: Int, toVisit:Queue<BfsNode>, prefix: String, words:MutableList<String>) {
        val word = if (index == -1) "" else prefix + ('a' + index)
        if (node.value != null)
            words.add(word)

        (0..ALPHABET_SIZE - 1)
                .filter { node.nodes[it] != null }
                .forEach{ toVisit.offer(BfsNode(node.nodes[it]!!, it, word)) }

        val bfsNode = toVisit.poll()
        if (bfsNode != null)
            bfs(bfsNode.node, bfsNode.index, toVisit, bfsNode.prefix, words)
    }

}