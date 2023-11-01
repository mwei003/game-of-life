package com.example.gameOfLife.domain;

/**
 * A cell has two states, live or dead, and it has up to 8 neighbours,
 * which are the cells that are horizontally, vertically, or diagonally adjacent.
 * The next state of a cell depends on how many live neighbours it has now.
 */
public class Cell {
    private boolean alive = false;
    private int liveNeighbours = 0;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getLiveNeighbours() {
        return liveNeighbours;
    }

    public void setLiveNeighbours(int liveNeighbours) {
        this.liveNeighbours = liveNeighbours;
    }
}
