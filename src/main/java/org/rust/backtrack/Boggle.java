package org.rust.backtrack;


import java.util.*;

/**
 * Given an NxN grid of characters and a dictionary, find all words which can be made from the characters in grid
 * and present in the given dictionary. A word can start and end at any character in the grid. Next character
 * must be adjacent to previous character in any of the directions i.e. up, down, left, right and diagonal.
 * Character at each position in grid can be used only once while making a word.
 */
public class Boggle {

    private final Set<String> dictionary;
    private char[][] grid;

    private Trie trie;
    private boolean[][] visited;

    public Boggle(Set<String> dictionary, char[][] grid) {
        this.dictionary = dictionary;
        this.grid = grid;

        this.trie = Trie.buildTrie(dictionary);

        visited = new boolean[grid.length][grid[0].length];
    }

    public List<String> findAllWords() {
        List<String> allWords = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                List<String> words = new ArrayList<>();
                findWords(i, j, words);
                allWords.addAll(words);
            }
        }

        return allWords;
    }

    private void findWords(int i, int j, List<String> words) {
        findWord(i, j, "", words);
    }

    private void findWord(int i, int j, String prefix, List<String> words) {
        if (visited[i][j])
            return ;

        visited[i][j] = true;
        String word = prefix + grid[i][j];

        if (trie.hasPrefix(word)) {
            if (isInDictionary(word))
                words.add(word);

            // go through each dir
            for (Direction direction : Direction.values()) {
                if (isValid(i, j, direction)) {
                    findWord(i + direction.getDi(), j + direction.getDj(), word, words);
                }
            }
        }

        visited[i][j] = false;
    }

    private boolean isInDictionary(String word) {
        return dictionary.contains(word);
    }

    private boolean isValid(int i, int j, Direction direction) {
        int nextI = i + direction.getDi();
        int nextJ = j + direction.getDj();

        return nextI >= 0 && nextI < grid.length &&
                nextJ >= 0 && nextJ < grid[0].length;
    }

    private enum Direction {
        NW(-1, -1), NN(-1, 0), NE(-1, 1), EE(0, 1), SE(1, 1), SS(1, 0), SW(1, -1), WW(0, -1);

        private int di;
        private int dj;

        Direction(int di, int dj) {
            this.di = di;
            this.dj = dj;
        }

        public int getDi() {
            return di;
        }

        public int getDj() {
            return dj;
        }
    }

    private static class Trie {
        private Map<Character, Node> children = new HashMap<>();

        public boolean hasPrefix(String prefix) {
            Map<Character, Node> iter = children;

            char[] chars = prefix.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (!iter.containsKey(chars[i]))
                    return false;

                Node node = iter.get(chars[i]);
                iter = node.children;
            }

            return true;
        }

        public static Trie buildTrie(Set<String> words) {
            Trie trie = new Trie();

            for (String word : words) {
                buildTrie(trie, word);
            }

            return trie;
        }

        private static void buildTrie(Trie trie, String word) {
            Map<Character, Node> children = trie.children;

            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (children.containsKey(chars[i])) {
                    Node node = children.get(chars[i]);
                    children = node.children;
                } else {
                    Node node = new Node();
                    children.put(chars[i], node);
                    children = node.children;
                }
            }
        }

        private static class Node {
            private Map<Character, Node> children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("art", "cat", "carter", "cartoon",
                "toon", "moon", "not", "apple", "ton");

        char[][] grid = new char[][] {
                {'c', 'a', 't'},
                {'r', 'r', 'e'},
                {'t', 'o', 'n'}
        };

        Boggle boggle = new Boggle(new HashSet<>(dictionary), grid);
        List<String> allWords = boggle.findAllWords();

        allWords.forEach(w -> System.out.println(w));
    }
}
