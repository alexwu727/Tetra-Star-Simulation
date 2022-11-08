package main.java.TetRover;

import java.util.*;

import main.java.TFace;

public class TetRover {
    private int tID;
    private int row;
    private int col;

    protected TFace tFace;
    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public TetRover(int row, int col, int tID) {
        this.row = row;
        this.col = col;
        this.tID = tID;
    }

    public int getTID() {
        return tID;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int r) {
        this.row = r;
    }

    public void setCol(int c) {
        this.col = c;
    }

    public void setTFace(TFace tFace) {
        this.tFace = tFace;
    }

    public boolean positionCheck(int row, int col) {
        return (tFace.Surface[row][col] == 0);
    }

    public void newPositionAction(int row, int col) {

    }

    public void walk() {
        tFace.removeObject(this);
        List<int[]> possiblePositions = new ArrayList<int[]>();
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            // if not valid -> continue
            if (newRow < 0 || newRow >= tFace.getRowSize() || newCol < 0 || newCol >= tFace.getColSize()) {
                continue;
            }
            // check if there is other object there
            if (positionCheck(newRow, newCol)) {
                int[] tmp = { newRow, newCol };
                possiblePositions.add(tmp);
            }
        }
        int[] newPosition = possiblePositions.get((int) (Math.random() * possiblePositions.size()));
        setRow(newPosition[0]);
        setCol(newPosition[1]);
        newPositionAction(newPosition[0], newPosition[1]);
        tFace.addObject(this);
    }
}
