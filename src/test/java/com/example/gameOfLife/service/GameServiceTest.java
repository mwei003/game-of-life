package com.example.gameOfLife.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @InjectMocks
    GameService gameService;

    @Test
    void testInvalidInputExceptionWhenCoordinatesLengthIsLessThanTwo() {
        int[][] invalidInput = new int[][]{{1}};
        Exception exception = assertThrows(
                InvalidInputException.class,
                () -> gameService.retrieveGameOutput(invalidInput));
        assertEquals("Coordinates must contain two numbers", exception.getMessage());
    }

    @Test
    void testInvalidInputExceptionWhenCoordinatesLengthIsGreaterThanTwo() {
        int[][] invalidInput = new int[][]{{1, 2, 3}};
        Exception exception = assertThrows(
                InvalidInputException.class,
                () -> gameService.retrieveGameOutput(invalidInput)
        );
        assertEquals("Coordinates must contain two numbers", exception.getMessage());
    }

    @Test
    void testInvalidInputExceptionWhenColumnIsOutOfRange() {
        int[][] invalidInput = new int[][]{{1, 200}};
        Exception exception = assertThrows(
                InvalidInputException.class,
                () -> gameService.retrieveGameOutput(invalidInput)
        );
        assertEquals("Column number need to be in the range of 0 - 199", exception.getMessage());
    }

    @Test
    void testInvalidInputExceptionWhenRowIsOutOfRange() {
        int[][] invalidInput = new int[][]{{-1, 199}};
        Exception exception = assertThrows(
                InvalidInputException.class,
                () -> gameService.retrieveGameOutput(invalidInput)
        );
        assertEquals("Row number need to be in the range of 0 - 199", exception.getMessage());
    }
}