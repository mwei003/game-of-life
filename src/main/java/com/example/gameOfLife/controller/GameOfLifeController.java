package com.example.gameOfLife.controller;

import com.example.gameOfLife.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameOfLifeController {
    @Autowired
    GameService gameService;

    @PostMapping("/states")
    public List<int[][]> outputStates(@RequestBody int[][] initialLiveCells) {
        return gameService.retrieveGameOutput(initialLiveCells);
    }
}
