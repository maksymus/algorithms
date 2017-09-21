package org.geeks.dynamic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid
 * dictionary word. Return all such possible sentences.
 *
 * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"],
 * the solution is ["cats and dog", "cat sand dog"].
 */
public class WordBreak {
    private Set<String> dictionary;

    public WordBreak(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> naive(String string) {
        ArrayList<String> result = new ArrayList<>();
        naive(string, new ArrayList<>(), 0, result);
        return result;
    }

    private void naive(String string, List<String> words, int pos, List<String> result) {
        if (pos == string.length())
            result.add(words.stream().collect(Collectors.joining(" ")));

        for (int i = pos; i <= string.length(); i++) {
            String word = string.substring(pos, i);

            if (dictionary.contains(word)) {
                List<String> prefix = new ArrayList<>(words);
                prefix.add(word);

                naive(string, prefix, i, result);
            }
        }
    }

    public ArrayList<String> dynamic(String string) {
        List<String>[] table = new ArrayList[string.length() + 1];

        for (int i = 0; i < string.length(); i++) {
            for (String word: dictionary) {
                int end = i + word.length();
                if (end > string.length())
                    continue;

                String substring = string.substring(i, end);

                if (substring.equals(word)) {
                    List<String> words = table[end] = table[end] != null ?
                            table[end] : new ArrayList<>();
                    words.add(word);
                    table[end] = words;
                }
            }
        }

        if (table[string.length()] == null)
            return new ArrayList<>();

        ArrayList<String> result = new ArrayList<>();
        walk(table, table.length - 1, result, new LinkedList<>());
        return result;
    }

    private void walk(List<String>[] table, int pos, List<String> result, Deque<String> prefix) {
        if (pos == 0) {
            result.add(prefix.stream().collect(Collectors.joining(" ")));
            return;
        }

        List<String> words = table[pos];
        for (String word : words) {
            Deque<String> newPrefix = new LinkedList(prefix);
            newPrefix.push(word);
            walk(table, pos - word.length(), result, newPrefix);
        }
    }

    public static void main(String[] args) {
        Set<String> dict = new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        WordBreak wordBreak = new WordBreak(dict);

        System.out.println("Naive: ");
        wordBreak.naive("catsanddog").stream().forEach(str -> System.out.println(str));

        System.out.println("Dynamic: ");
        wordBreak.dynamic("catsanddog").stream().forEach(str -> System.out.println(str));
    }
}
