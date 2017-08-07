package ua.kotlin.demo.tries;

import kotlin.Pair;

public interface Trie {

    void add(Pair<String, Integer> word);

    boolean contains(String word);

    void delete(String word);

    Iterable<String> words();

    Iterable<String> wordsWithPrefix(String pref);
}
