package ua.kotlin.demo.tries

private val ALPHABET_SIZE = 26

class RWayTrie : Trie {

    var size = 0

    data class Node(val value: Object? = null, var nodes: Array<Node?> = Array<Node?>(ALPHABET_SIZE, {null}))

    val words = ArrayList<String>();

    override fun add(word: Pair<String, Int?>?) {
        words.add(word!!.first)
        size++
    }

    override fun contains(word: String?): Boolean {
        return words.contains(word)
    }

    override fun delete(word: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun words(): MutableIterable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun wordsWithPrefix(pref: String?): MutableIterable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}