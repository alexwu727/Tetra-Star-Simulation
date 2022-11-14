package main.java.Base;

import main.java.Locatable;
import main.java.TFace;

public class River (int vbRow, int vbCol) implements Locatable {
    private int row;
    private int col;
    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    for (int[] direction : directions) {
        row = vbRow + direction[0];
        col = vbCol + direction[1];
        // if not valid -> continue
        if (row < 0 || row >= tFace.getRowSize() || col < 0 || col >= tFace.getColSize()) {
            tFace.addObject(this);
        }
    }