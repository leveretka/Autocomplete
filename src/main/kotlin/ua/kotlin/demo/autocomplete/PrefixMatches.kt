package ua.kotlin.demo.autocomplete

import ua.kotlin.demo.tries.RWayTrie

class PrefixMatches (private val trie: RWayTrie = RWayTrie()) {

    fun load(vararg strings: String): Int {
        strings.forEach { trie.add(it to it.length) }
        return strings.size
    }

    fun contains(word: String): Boolean = trie.contains(word)

    fun delete(word: String) = trie.delete(word)

    fun getSize(): Int = trie.size

    fun wordsWithPrefix(pref: String): Iterable<String> {
        if (pref.length < 2)
            return emptyList()
        return trie.wordsWithPrefix(pref)
    }

    fun wordsWithPrefix(pref: String, k: Int): Iterable<String> {
        if (pref.length < 2)
            return emptyList()
        var i = 0
        val words = trie.wordsWithPrefix(pref).filter { it.length > 2 }
        val result = mutableListOf<String>()
        for (word in words) {
            if (result.isNotEmpty() && word.length > result.last().length)
                i++
            if (i < k)
                result.add(word)
            else
                break
        }
        return result
    }

}