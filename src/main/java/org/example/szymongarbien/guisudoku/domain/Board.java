package org.example.szymongarbien.guisudoku.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Board {

    private static final int BOARD_SIZE = 9;
    private static final int CLUSTER_SIZE = 3;

    ArrayList<Position> positions;

    public Board() {
        positions = new ArrayList<>(BOARD_SIZE * BOARD_SIZE);
    }

    public void populateBoard(int[][] boardValues) {

        if (boardValues.length != BOARD_SIZE || boardValues[0].length != BOARD_SIZE) {
            throw new IllegalArgumentException("Board data matrix of illegal size!");
        }

        int value;
        boolean blocked;

        for (int i=0; i<BOARD_SIZE; i++) {
            for (int j=0; j<BOARD_SIZE; j++) {
                value = boardValues[i][j];
                if (value == 0) {
                    blocked = false;
                } else {
                    blocked = true;
                }
                positions.add(new Position(i+1, j+1, value, blocked));
            }
        }
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }
}
