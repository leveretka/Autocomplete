package ua.kotlin.demo.tries;

import kotlin.Pair;

public interface Trie {

    public void add(Pair<String, Integer> word);

    public boolean contains(String word);

    public boolean delete(String word);

    public Iterable<String> words();

    public Iterable<String> wordsWithPrefix(String pref);
    
    public int size();
}
