package com.example.gameOfLife.domain;

/**
 * Board represents a 2D array of Cells
 */
public class Board {
    public static final int ROW = 200;
    public static final int COLUMN = 200;
    private final Cell[][] cells = new Cell[ROW][COLUMN];

    public Board() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}