package com.example.gameOfLife.service;

import com.example.gameOfLife.domain.Board;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    public void initializeLiveCells(Board board, int[][] liveCells) {
        for (int[] coordinates : liveCells) {
            board.getCells()[coordinates[0]][coordinates[1]].setAlive(true);
        }
    }

    public int[][] outputLiveCellsCoordinates(Board board) {
        List<int[]> liveCells = new ArrayList<>();

        for (int i = 0; i < Board.ROW; i++) {
            for (int j = 0; j < Board.COLUMN; j++) {
                if (board.getCells()[i][j].isAlive()) {
                    liveCells.add(new int[]{i, j});
                }
            }
        }

        return liveCells.toArray(new int[0][0]);
    }

    public void evolveToNextState(Board board) {
        recordLiveNeighbours(board);
        updateStatus(board);
    }

    private void updateStatus(Board board) {
        for (int i = 0; i < Board.ROW; i++) {
            for (int j = 0; j < Board.COLUMN; j++) {
                // No changes if a cell has 2 live neighbours
                if (cellWillDieByUnderPopulation(board, i, j) || cellWillDieByOverPopulation(board, i, j)) {
                    board.getCells()[i][j].setAlive(false);
                } else if (cellWillLiveByReproduction(board, i, j)) {
                    board.getCells()[i][j].setAlive(true);
                }
            }
        }
    }

    private static boolean cellWillLiveByReproduction(Board board, int i, int j) {
        return board.getCells()[i][j].getLiveNeighbours() == 3;
    }

    private static boolean cellWillDieByOverPopulation(Board board, int i, int j) {
        return board.getCells()[i][j].getLiveNeighbours() > 3;
    }

    private static boolean cellWillDieByUnderPopulation(Board board, int i, int j) {
        return board.getCells()[i][j].getLiveNeighbours() < 2;
    }

    private void recordLiveNeighbours(Board board) {
        for (int i = 0; i < Board.ROW; i++) {
            for (int j = 0; j < Board.COLUMN; j++) {
                board.getCells()[i][j].setLiveNeighbours(countLiveNeighboursForCell(board, i, j));
            }
        }
    }

    private int countLiveNeighboursForCell(Board board, int cellRow, int cellColumn) {
        int total = 0;

        if (isNotTheFirstRow(cellRow)) {
            total += countCellAbove(board, cellRow, cellColumn);
        }
        if (isNotTheLastRow(cellRow)) {
            total += countCellBelow(board, cellRow, cellColumn);
        }
        if (isNotTheFirstColumn(cellColumn)) {
            total += countCellOnTheLeft(board, cellRow, cellColumn);
        }
        if (isNotTheLastColumn(cellColumn)) {
            total += countCellOnTheRight(board, cellRow, cellColumn);
        }
        if (isNotTheFirstRow(cellRow) && isNotTheFirstColumn(cellColumn)) {
            total += countCellOnUpperLeft(board, cellRow, cellColumn);
        }
        if (isNotTheLastRow(cellRow) && isNotTheLastColumn(cellColumn)) {
            total += countCellOnLowerRight(board, cellRow, cellColumn);
        }
        if (isNotTheFirstRow(cellRow) && isNotTheLastColumn(cellColumn)) {
            total += countCellOnUpperRight(board, cellRow, cellColumn);
        }
        if (isNotTheLastRow(cellRow) && isNotTheFirstColumn(cellColumn)) {
            total += countCellOnLowerLeft(board, cellRow, cellColumn);
        }

        return total;
    }

    private static boolean isNotTheLastColumn(int cellColumn) {
        return cellColumn < Board.COLUMN - 1;
    }

    private static boolean isNotTheFirstColumn(int cellColumn) {
        return cellColumn > 0;
    }

    private static boolean isNotTheLastRow(int cellRow) {
        return cellRow < Board.ROW - 1;
    }

    private static boolean isNotTheFirstRow(int cellRow) {
        return cellRow > 0;
    }

    private int countCellOnLowerLeft(Board board, int cellRow, int cellColumn) {
        return board.getCells()[cellRow + 1][cellColumn - 1].isAlive() ? 1 : 0;
    }

    private int countCellOnUpperRight(Board board, int cellRow, int cellColumn) {
        return board.getCells()[cellRow - 1][cellColumn + 1].isAlive() ? 1 : 0;
    }

    private int countCellOnLowerRight(Board board, int cellRow, int cellColumn) {
        return board.getCells()[cellRow + 1][cellColumn + 1].isAlive() ? 1 : 0;
    }

    private int countCellOnUpperLeft(Board board, int cellRow, int cellColumn) {
        return board.getCells()[cellRow - 1][cellColumn - 1].isAlive() ? 1 : 0;
    }

    private int countCellOnTheRight(Board board, int cellRow, int cellColumn) {
        return board.getCells()[cellRow][cellColumn + 1].isAlive() ? 1 : 0;
    }

    private int countCellOnTheLeft(Board board, int cellRow, int cellColumn) {
        return board.getCells()[cellRow][cellColumn - 1].isAlive() ? 1 : 0;
    }

    private int countCellBelow(Board board, int cellRow, int cellColumn) {
        return board.getCells()[cellRow + 1][cellColumn].isAlive() ? 1 : 0;
    }

    private int countCellAbove(Board board, int cellRow, int cellColumn) {
        return board.getCells()[cellRow - 1][cellColumn].isAlive() ? 1 : 0;
    }
}
