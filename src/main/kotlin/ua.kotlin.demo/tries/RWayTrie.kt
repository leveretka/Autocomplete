package ua.kotlin.demo.tries

public const val ALPHABET_SIZE = 26

class RWayTrie : Trie {

    var size = 0
        private set
    var root = Node()
        private set

    data class Node(var value: Int? = null, var nodes: Array<Node?> = Array<Node?>(ALPHABET_SIZE, {null}))

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
        root = delete(root, word, 0)?: Node()
    }

    private fun delete(x: Node?, key: String, d:Int) : Node? {
        if (x == null) return null
        if (d == key.length) {
            if (x.value != null)
                size--
            x.value = null
        }
        else {
            val index = key[d] - 'a'
            x.nodes[index] = delete(x.nodes[index], key, d + 1)
        }

        if (x.value != null)
            return x

        return if ((0..ALPHABET_SIZE - 1).any { x.nodes[it] != null }) x else null
    }

    override fun words(): MutableIterable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun wordsWithPrefix(pref: String): MutableIterable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}