package ua.kotlin.demo.tries

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
        key.toCharArray().map { it - 'a' }.forEach {
            if (x.nodes[it] == null)
                x.nodes[it] = Node()

            x = x.nodes[it] as Node
        }
        x.value = word.second

        size++
    }

    override fun contains(word: String): Boolean {
        var x = root
        word.toCharArray().map { it - 'a' }.forEach {
            if (x.nodes[it] == null)
                return false

            x = x.nodes[it] as Node
        }
        return x.value != null
    }

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
            val index = key[d] - 'a'
            x.nodes[index] = delete(x.nodes[index], key, d + 1)
        }

        if (x.value != null)
            return x

        return if ((0..ALPHABET_SIZE - 1).any { x.nodes[it] != null }) x else null
    }

    override fun words(): MutableIterable<String> {
        val result = mutableListOf<String>()
        words(root, -1, "", result)
        return result
    }

    override fun wordsWithPrefix(pref: String): MutableIterable<String> {
        var x = root
        var index = -1
        pref.forEach { c ->
            if (x.nodes[c - 'a'] != null) {
                index = c - 'a'
                x = x.nodes[index] as Node
            } else
                return mutableListOf()
        }
        val result = mutableListOf<String>()
        words(x, index, pref.dropLast(1), result)
        return result
    }

    private fun words(node: Node, index: Int, prefix: String, result: MutableList<String>) {
        val word = if (index == -1) "" else prefix + ('a' + index)
        if (node.value != null)
            result.add(word)
        (0..ALPHABET_SIZE - 1)
                .filter { node.nodes[it] != null }
                .forEach { words(node.nodes[it] as Node, it, word, result) }
    }

}