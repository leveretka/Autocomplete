package ua.kotlin.demo.autocomplete

import ua.kotlin.demo.tries.RWayTrie
import ua.kotlin.demo.tries.Trie

class PrefixMatches (val trie: Trie = RWayTrie()) {

    fun load(vararg strings: String): Int {
        strings.forEach { trie.add(it to it.length) }
        return strings.size
    }

    fun contains(word: String): Boolean = trie.contains(word)

    fun delete(word: String): Boolean = TODO()

    fun getSize(): Int = TODO()

    fun wordsWithPrefix(pref: String): Iterable<String> = TODO()

    fun wordsWithPrefix(pref: String, k: Int): Iterable<String> = TODO()

}