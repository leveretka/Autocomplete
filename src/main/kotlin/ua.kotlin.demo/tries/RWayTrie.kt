package ua.kotlin.demo.tries

/**
 * Created by leveret on 30.07.17.
 */
class RWayTrie : Trie {

    var size = 0

    override fun add(word: Pair<String, Int>?) {
        size++
    }

    override fun contains(word: String?): Boolean = true

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