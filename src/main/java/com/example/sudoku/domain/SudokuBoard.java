package com.example.sudoku.domain;

import java.util.Arrays;
import java.util.Random;

public class SudokuBoard {
    private int[][] board;
    private final boolean[][] initialValues;

    private Random random = new Random();

    public SudokuBoard() {
        board = new int[9][9];
        initialValues = new boolean[9][9];

        for (int row = 0; row < 9; row++) {
            Arrays.fill(board[row], 0);
            Arrays.fill(initialValues[row], false);

        }
    }

    public void generateBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                board[row][col] = 0;
                initialValues[row][col] = false;
            }
        }
        for (int i = 0; i < 30; i++) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            int value = random.nextInt(9) + 1;

            if (isValidMove(row, col, value)) {
                board[row][col] = value;
                initialValues[row][col] = true;
            }else{
                i--;
            }
        }
    }

    public boolean isValidMove(int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }

        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solveBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int value = 1; value <= 9; value++) {
                        if (isValidMove(row, col, value)) {
                            board[row][col] = value;
                            if (solveBoard()) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean isInitialValue(int row, int col) {
        return initialValues[row][col];
    }

}
