package org.example.szymongarbien.guisudoku.service;

import org.example.szymongarbien.guisudoku.Sudoku;
import org.example.szymongarbien.guisudoku.domain.Board;
import org.example.szymongarbien.guisudoku.domain.Position;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SudokuService {

    Sudoku controller;

    public void solve(Board board) {
        if (!linesSolved(board.getPositions())) {
            return;
        }
        if (!columnsSolved(board.getPositions())) {
            return;
        }
        if (!clusterSolved(board.getPositions())) {
            return;
        }

        controller.announceSolved();
    }

    private boolean linesSolved(List<Position> positions) {
        for (int i=1; i<=9; i++) {
            int finalI = i;
            List<Position> line = positions.stream().filter(pos -> pos.getI() == finalI).toList();
            Set<Integer> vals = line.stream().map(Position::getVal).collect(Collectors.toSet());
            vals.remove(0);

            if (vals.size() < 9) {
                return false;
            }
        }
        return true;
    }

    private boolean columnsSolved(List<Position> positions) {
        for (int j=1; j<=9; j++) {
            int finalJ = j;
            List<Position> column = positions.stream().filter(pos -> pos.getJ() == finalJ).toList();
            Set<Integer> vals = column.stream().map(Position::getVal).collect(Collectors.toSet());
            vals.remove(0);

            if (vals.size() < 9) {
                return false;
            }
        }
        return true;
    }

    private boolean clusterSolved(List<Position> positions) {
        for (int i=1; i<=9; i+=3) {
            for (int j=1; j<=9; j+=3) {
                int finalI = i;
                int finalJ = j;
                List<Position> cluster = positions.stream()
                        .filter(pos -> pos.getI() >= finalI && pos.getI() < finalI+3)
                        .filter(pos -> pos.getJ() >= finalJ && pos.getJ() < finalJ+3)
                        .toList();
                Set<Integer> vals = cluster.stream().map(Position::getVal).collect(Collectors.toSet());
                vals.remove(0);

                if (vals.size() < 9) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setController(Sudoku controller) {
        this.controller = controller;
    }
}
