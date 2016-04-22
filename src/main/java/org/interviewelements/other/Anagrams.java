package org.interviewelements.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Anagrams {
    private static final String WORD_LIST_FILE = "english-words.txt";
    private final Set<String> words = new HashSet<>();

    public Anagrams() throws IOException {
        InputStream wordStream = Anagrams.class.getClassLoader().getResourceAsStream(WORD_LIST_FILE);

        try (BufferedReader wordReader = new BufferedReader(new InputStreamReader(wordStream))) {
            for (String str = wordReader.readLine(); str != null; str = wordReader.readLine()) {
                words.add(str);
            }
        }
    }

    public Set<String> findAnagrams(String word) {
        return permutate(word).stream()
                .filter(w -> words.contains(w))
                .filter(w -> !word.equals(w))
                .collect(Collectors.toSet());
    }

    private List<String> permutate(String word) {
        char[] chars = word.toCharArray();
        List<String> permutated = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            char ch = chars[i];

            String prefix = String.valueOf(ch);
            if (word.length() <= 1) {
                permutated.add(prefix);
            } else {
                String subword = word.substring(0, i) + word.substring(i + 1, word.length());
                List<String> permutate = permutate(subword);
                List<String> collected = permutate.stream().map(w -> prefix + w).collect(Collectors.toList());
                permutated.addAll(collected);
            }
        }

        return permutated;
    }

    public static void main(String[] args) throws IOException {
        Set<String> anagrams = new Anagrams().findAnagrams("ripples");
        anagrams.stream().forEach(anagram -> System.out.println(anagram));
    }
}
