package org.rust.backtrack;

import java.util.List;

/**
 * Given a chess board of size N x N, determine how many ways N queens can be placed on this
 * board so that no two queens attack each other.
 */
public class NQueen {
    private boolean[][] board;

    public NQueen(int n) {
        this.board = new boolean[n][n];
    }

    public void solve() {
        // try each column starting from first row
        for (int j = 0; j < board[0].length; j++) {
            solve(0, j);
        }
    }

    public void solve(int row, int col) {
        // mark visited
        board[row][col] = true;

        if (isValid(row, col)) {
            if (row == board.length - 1) {
                print();
            } else {
                // try each column on next row
                for (int j = 0; j < board[0].length; j++) {
                    solve(row + 1, j);
                }
            }
        }

        // back track visited
        board[row][col] = false;
    }

    private boolean isValid(int i, int j) {
        // check no queen is on column j
        for (int row = 0; row < i; row++) {
            if (board[row][j]) {
                return false;
            }
        }

        // check no queen is on i,j diagonals
        for (int row = 0; row < i; row++) {
            if (isColumnValid(j-i+row) && board[row][j-i+row]) {
                return false;
            }

            if (isColumnValid(j+i-row) && board[row][j+i-row]) {
                return false;
            }
        }

        return true;
    }

    private boolean isColumnValid(int col) {
        return col >= 0 && col < board[0].length;
    }

    private void print() {
        System.out.println("======================================");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] ? " 1 " : " 0 ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen(20);
        nQueen.solve();
    }
}
