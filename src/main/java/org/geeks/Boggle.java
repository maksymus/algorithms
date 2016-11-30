package org.geeks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Boggle (Find all possible words in a board of characters)
 * </br>
 * Given a dictionary, a method to do lookup in dictionary and a M x N board where every cell has one character. 
 * Find all possible words that can be formed by a sequence of adjacent charactersNote that we can move to any of 
 * 8 adjacent characters, but a word should not have multiple instances of same cell.
 * 
 * Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
 *       boggle[][]   = {{'G','I','Z'},
 *                       {'U','E','K'},
 *                       {'Q','S','E'}};
 *      isWord(str): returns true if str is present in dictionary
 *                   else false.
 *
 * Output:  Following words of dictionary are present
 *         GEEKS
 *         QUIZ
 */
public class Boggle {
    
    private char[][] boggle;
    private Set<String> dictionary;

    public Boggle(char[][] boggle, Set<String> dictionary) {
        this.boggle = boggle;
        this.dictionary = dictionary;
    }
    
    private static enum Move {
        UP {
            public int x(int x) { return x - 1; }
            public int y(int y) { return y; }
        }, 
        DOWN {
            public int x(int x) { return x + 1; }
            public int y(int y) { return y; }
        }, 
        LEFT {
            public int x(int x) { return x; }
            public int y(int y) { return y - 1; }
        }, 
        RIGHT {
            public int x(int x) { return x; }
            public int y(int y) { return y + 1; }
        },
        UPLEFT {
            public int x(int x) { return x - 1; }
            public int y(int y) { return y - 1; }
        }, 
        
        UPRIGHT {
            public int x(int x) { return x - 1; }
            public int y(int y) { return y + 1; }
        }, 
        DOWNLEFT {
            public int x(int x) { return x + 1; }
            public int y(int y) { return y - 1; }
        }, 
        DOWNRIGHT {
            public int x(int x) { return x + 1; }
            public int y(int y) { return y + 1; }
        };

        
        public abstract int x(int x);
        public abstract int y(int y);
    };
    
    private boolean isWorld(String text) {
        return dictionary.contains(text);
    }
    
    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < boggle.length && y < boggle.length;
    }
    
    public Set<String> findWords() {
        HashSet<String> words = new HashSet<>();
        
        for (int i = 0; i < boggle.length; i++) {
            for (int j = 0; j < boggle.length; j++) {
                boolean[][] visited = new boolean[boggle.length][boggle.length];
                findWords("" + boggle[i][j], words, visited, i , j);
            }
        }
        
        return words;
    }

    private void findWords(String string, Set<String> words, boolean[][] visited, int i, int j) {
        if (visited[i][j])
            return;
        
        visited[i][j] = true; 
        
        if (isWorld(string)) {
            words.add(string);
        } else {
            for (Move move : Move.values()) {
                int x = move.x(i), y = move.y(j);
                if (isValid(x, y)) {
                    findWords(string + boggle[x][y], words, visited, x, y);
                }
            }
        }
        
        visited[i][j] = false; 
    }
    
    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>(Arrays.asList(new String[] { "geek", "for", "quiz", "go" }));
        char[][] boggle = { 
                { 'g', 'i', 'z' }, 
                { 'u', 'e', 'k' }, 
                { 'q', 's', 'e' } };
        
        Set<String> words = new Boggle(boggle, dictionary).findWords();
        words.forEach(word -> System.out.println(word));
    }
}
