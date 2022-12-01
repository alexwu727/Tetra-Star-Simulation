package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import main.java.Base.*;

public class TFace {
    private static TFace singleton = null;
    private int rowSize;
    private int colSize;
    public Locatable Surface[][];
    public Map<String, Base> baseMap = new HashMap<String, Base>();
    public int TetVaderBaseRow;
    public int TetVaderBaseCol;

    private TFace() {

    }

    public static TFace instance() {
        if (singleton == null) {
            singleton = new TFace();
        }
        return singleton;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void addObject(Locatable object) {
        int row = object.getRow();
        int col = object.getCol();
        Surface[row][col] = object;
    }

    public void removeObject(Locatable object) {
        int row = object.getRow();
        int col = object.getCol();
        String key = convertToKey(object.getRow(), object.getCol());
        Surface[row][col] = baseMap.containsKey(key) ? baseMap.get(key) : null;
    }

    public void addBase(Base base) {
        int row = base.getRow();
        int col = base.getCol();
        baseMap.put(base.getBID(), base);
        Surface[row][col] = base;
        if (base instanceof VaderBase) {
            addRiver(row, col);
            this.TetVaderBaseRow = row;
            this.TetVaderBaseCol = col;
        }
    }

    public Base getBase(int row, int col) {
        String key = convertToKey(row, col);
        return (Base) baseMap.get(key);
    }

    public void addRiver(int vbRow, int vbCol) {
        int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        for (int[] direction : directions) {
            int row = vbRow + direction[0];
            int col = vbCol + direction[1];
            // if not valid -> continue
            if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
                continue;
            }
            River river = new River(row, col, convertToKey(row, col));
            Surface[row][col] = river;
        }

    }

    public String convertToKey(int row, int col) {
        StringBuilder sb = new StringBuilder();
        sb.append(row + "," + col);
        return sb.toString();
    }

    public void setSurfaceSize(int row, int col) {
        this.rowSize = row;
        this.colSize = col;
        Surface = new Locatable[row][col];
    }

    public void printSurface() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                String displayID = Surface[i][j] == null ? "0" : Surface[i][j].getDisplayID();
                System.out.print(displayID + " ");
            }
            System.out.println();
        }
    }

}
