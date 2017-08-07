package ua.kotlin.demo.tries

private val ALPHABET_SIZE = 26

class RWayTrie : Trie {

    var size = 0
    val root = Node()

    data class Node(var value: Int? = null, var nodes: Array<Node?> = Array<Node?>(ALPHABET_SIZE, {null}))

    override fun add(word: Pair<String, Int?>) {
        val key = word.first
        var x = root
        for (c in key.toCharArray()) {
            if (x.nodes[c - 'a'] == null)
                x.nodes[c - 'a'] = Node()

            x = x.nodes[c -  'a'] as Node
        }
        x.value = word.second

        size++
    }

    override fun contains(word: String): Boolean {
        var x = root
        for (c in word.toCharArray()) {
            if (x.nodes[c - 'a'] == null)
                return false

            x = x.nodes[c -  'a'] as Node
        }
        return x.value != null
    }

    override fun delete(word: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun words(): MutableIterable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun wordsWithPrefix(pref: String): MutableIterable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}