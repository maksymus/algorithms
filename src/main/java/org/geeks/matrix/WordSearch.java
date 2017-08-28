package org.geeks.matrix;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * For example, given board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * word = "eat", -> returns true,
 * word = "oath", -> returns true,
 * word = "abcb", -> returns false.
 */
public class WordSearch {
    public boolean dfs(char[][] matrix, String word) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean found = dfs(matrix, word, 0, i, j);
                if (found)
                    return true;
            }
        }

        return false;
    }

    public boolean dfs(char[][] matrix, String word, int pos, int i, int j) {
        if (word.length() == pos)
            return true;

        if (i < 0 || i >= matrix.length)
            return false;

        if (j < 0 || j >= matrix[0].length)
            return false;

        if (matrix[i][j] != word.charAt(pos) || matrix[i][j] == ' ')
            return false;

        matrix[i][j] = ' ';

        if (dfs(matrix, word, pos + 1, i - 1, j)
                || dfs(matrix, word, pos + 1, i + 1, j)
                || dfs(matrix, word, pos + 1, i, j - 1)
                || dfs(matrix, word, pos + 1, i, j + 1)) {
            return true;
        }

        matrix[i][j] = word.charAt(pos);

        return false;
    }
}
