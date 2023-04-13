package com.example.sudoku.controller;

import com.example.sudoku.service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SudokuController {

    private final SudokuService sudokuService;

    @Autowired
    public SudokuController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("board", sudokuService.getBoard());
        model.addAttribute("sudokuService", sudokuService);
        return "index";
    }

    @PostMapping("/generate")
    public String generateNewBoard() {
        sudokuService.generateNewBoard();
        return "redirect:/";
    }

    @PostMapping("/move")
    public String makeMove(@RequestParam("row") int row,
                           @RequestParam("col") int col,
                           @RequestParam("value") int value,
                           Model model) {
        if (!sudokuService.isInitialValue(row, col)) {
            boolean validMove = sudokuService.isValidMove(row, col, value);
            if (validMove) {
                sudokuService.setCellValue(row, col, value);
            }
            model.addAttribute("validMove", validMove);
        }
        model.addAttribute("board", sudokuService.getBoard());
        model.addAttribute("sudokuService", sudokuService);
        if (sudokuService.isSolved()) {
            return "redirect:/win";
        }
        return "index";
    }


//    @PostMapping("/solve")
//    public String solveBoard() {
//        sudokuService.solveBoard();
//        return "redirect:/";
//    }

    @GetMapping("/win")
    public String showWinPage() {
        return "win";
    }

}
