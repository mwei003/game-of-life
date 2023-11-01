package com.example.gameOfLife.service;

import com.example.gameOfLife.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private static final int NUM_OF_STATES = 100;
    @Autowired
    BoardService boardService;

    public List<int[][]> retrieveGameOutput(int[][] input) {
        validateInput(input);
        Board board = new Board();
        boardService.initializeLiveCells(board, input);
        List<int[][]> output = new ArrayList<>();

        for (int i = 0; i < NUM_OF_STATES; i++) {
            boardService.evolveToNextState(board);
            int[][] liveCells = boardService.outputLiveCellsCoordinates(board);
            output.add(liveCells);
        }

        return output;
    }

    private void validateInput(int[][] input) {
        for (int[] coordinates : input) {
            if (coordinates.length != 2) {
                throw new InvalidInputException("Coordinates must contain two numbers");
            }
            if (coordinates[0] < 0 || coordinates[0] >= Board.ROW) {
                throw new InvalidInputException(String.format("Row number need to be in the range of 0 - %s", Board.ROW - 1));
            }
            if (coordinates[1] < 0 || coordinates[1] >= Board.ROW) {
                throw new InvalidInputException(String.format("Column number need to be in the range of 0 - %s", Board.COLUMN - 1));
            }
        }
    }
}
