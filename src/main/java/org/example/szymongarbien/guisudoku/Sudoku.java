package org.example.szymongarbien.guisudoku;

import org.example.szymongarbien.guisudoku.domain.Board;
import org.example.szymongarbien.guisudoku.service.SudokuService;
import org.example.szymongarbien.guisudoku.view.GUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class Sudoku implements CommandLineRunner {

    private static final int[][] BOARD_VALUES = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };
//    private static final int[][] BOARD_VALUES = {
//            {5, 3, 4, 6, 7, 8, 9, 1, 2},
//            {6, 7, 2, 1, 9, 5, 3, 4, 8},
//            {1, 9, 8, 3, 4, 2, 5, 6, 7},
//            {8, 5, 9, 7, 6, 1, 4, 2, 3},
//            {4, 2, 6, 8, 5, 3, 7, 9, 1},
//            {7, 1, 3, 9, 2, 4, 8, 5, 6},
//            {9, 6, 1, 5, 3, 7, 2, 8, 4},
//            {2, 8, 7, 4, 1, 9, 6, 3, 5},
//            {3, 4, 5, 2, 8, 6, 1, 7, 9}
//    };

    private final Board board;
    private final GUI gui;
    private final SudokuService service;

    @Autowired
    public Sudoku(Board board, GUI gui, SudokuService service) {
        this.board = board;
        this.gui = gui;
        this.service = service;

        gui.setController(this);
        service.setController(this);
    }

    @Override
    public void run(String... args) {
        board.populateBoard(BOARD_VALUES);
        gui.init(board.getPositions());
    }

    public void solve() {
        service.solve(board);
    }

    public void announceSolved() {
        gui.announceSolved();
    }
}
