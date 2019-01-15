package ua.kotlin.demo.main

import ua.kotlin.demo.autocomplete.PrefixMatches
import ua.kotlin.demo.tries.RWayTrie
import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {
    val trie = RWayTrie()

    BufferedReader(FileReader("words.txt")).use {
        it.lines().forEach {
            trie.add(it.toLowerCase() to it.length)
        }
    }

    val list = trie.wordsWithPrefix("a").take(5).toList()


    println("list: $list")
}