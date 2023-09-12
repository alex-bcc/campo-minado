package com.example.campominado.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    public int x, y;
    public boolean isMine;
    public int adjacentMines;
    public boolean isRevealed;
    public boolean isFlagged;
    public double probability;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isMine = false;
        this.isRevealed = false;
        this.adjacentMines = 0;
    }

    public Cell(int x, int y, boolean isMine, int adjacentMines, boolean isRevealed, boolean isFlagged) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
        this.adjacentMines = adjacentMines;
        this.isRevealed = isRevealed;
        this.isFlagged = isFlagged;
    }

    public Cell(int x, int y, int adjacentMines) {
        this.x = x;
        this.y = y;
        this.adjacentMines = adjacentMines;
    }


}
