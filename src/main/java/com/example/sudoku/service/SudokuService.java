package com.example.sudoku.service;

import com.example.sudoku.domain.SudokuBoard;
import org.springframework.stereotype.Service;

@Service
public class SudokuService {
    private SudokuBoard sudokuBoard;

    public SudokuService() {
        this.sudokuBoard = new SudokuBoard();
        generateNewBoard();
    }

    public int[][] getBoard() {
        return sudokuBoard.getBoard();
    }

    public void generateNewBoard() {
        sudokuBoard.generateBoard();
    }

    public boolean isValidMove(int row, int col, int value) {
        return sudokuBoard.isValidMove(row, col, value);
    }

    public void setCellValue(int row, int col, int value) {
        sudokuBoard.getBoard()[row][col] = value;
    }

    public void solveBoard() {
        sudokuBoard.solveBoard();
    }
    public boolean isSolved() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (sudokuBoard.getBoard()[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isInitialValue(int row, int col) {
        return sudokuBoard.isInitialValue(row, col);
    }


}
