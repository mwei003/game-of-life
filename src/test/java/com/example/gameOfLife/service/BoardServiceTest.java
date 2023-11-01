package com.example.gameOfLife.service;

import com.example.gameOfLife.domain.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Test
    void testEvolveToNextStateWithNoLiveCells() {
        Board board = new Board();
        int[][] input = new int[][]{new int[]{1, 1}, new int[]{2, 1}};
        boardService.initializeLiveCells(board, input);
        boardService.evolveToNextState(board);
        int[][] output = boardService.outputLiveCellsCoordinates(board);
        assertEquals(0, output.length);
    }

    @Test
    void testEvolveToNextStateWithLiveCellsOnUpperLeftCorner() {
        Board board = new Board();
        int[][] input = new int[][]{new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}};
        boardService.initializeLiveCells(board, input);
        boardService.evolveToNextState(board);
        int[][] output = boardService.outputLiveCellsCoordinates(board);
        // output is expected to be [0,0], [0,1], [1,0], [1,1]
        assertEquals(4, output.length);
    }

    @Test
    void testEvolveToNextStateWithLiveCellsOnUpperRightCorner() {
        Board board = new Board();
        int[][] input = new int[][]{new int[]{0, 199}, new int[]{1, 199}, new int[]{2, 199}};
        boardService.initializeLiveCells(board, input);
        boardService.evolveToNextState(board);
        int[][] output = boardService.outputLiveCellsCoordinates(board);
        // output is expected to be [1,199], [2,199]
        assertEquals(2, output.length);
    }

    @Test
    void testEvolveToNextStateWithLiveCellsOnLowerLeftCorner() {
        Board board = new Board();
        int[][] input = new int[][]{new int[]{199, 0}, new int[]{199, 1}, new int[]{198, 0}, new int[]{197, 0}};
        boardService.initializeLiveCells(board, input);
        boardService.evolveToNextState(board);
        int[][] output = boardService.outputLiveCellsCoordinates(board);
        // output is expected to be [199,0], [198,0], [199,1]
        assertEquals(3, output.length);
    }

    @Test
    void testEvolveToNextStateWithLiveCellsOnLowerRightCorner() {
        Board board = new Board();
        int[][] input = new int[][]{new int[]{199, 197}, new int[]{199, 198}, new int[]{199, 199}};
        boardService.initializeLiveCells(board, input);
        boardService.evolveToNextState(board);
        int[][] output = boardService.outputLiveCellsCoordinates(board);
        // output is expected to be [199,198], [198,198]
        assertEquals(2, output.length);
    }
}
