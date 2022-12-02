package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import main.java.Base.*;
import main.java.Inhabitant.TetRover;

public class TFace {
    private static TFace singleton = null;
    private int rowSize;
    private int colSize;
    public Locatable Surface[][];
    public HashMap<String, List<String>> displayHashMap = new HashMap<String, List<String>>();
    public Map<String, Base> baseMap = new HashMap<String, Base>();
    public Map<String, String> mapMap = new HashMap<String, String>();
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

    public static void clear() {
        singleton = null;
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
            addBase(river);
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

    public void updateDisplayHashMap() {
        displayHashMap = new HashMap<String, List<String>>();
        // bases
        for (Map.Entry<String, Base> entry : baseMap.entrySet()) {
            List<String> displayList = new ArrayList<>();
            displayList.add(entry.getValue().getDisplayID());
            displayHashMap.put(entry.getKey(), displayList);
        }

        // rovers
        for (int i = 0; i < Surface.length; i++) {
            for (int j = 0; j < Surface[0].length; j++) {
                if (Surface[i][j] instanceof TetRover) {
                    String key = convertToKey(i, j);
                    if (displayHashMap.containsKey(convertToKey(i, j))) {
                        displayHashMap.get(key).add(Surface[i][j].getDisplayID());
                    } else {
                        List<String> displayList = new ArrayList<>();
                        displayList.add(Surface[i][j].getDisplayID());
                        displayHashMap.put(key, displayList);
                    }
                }
            }
        }

        // maps
        for (Map.Entry<String, String> entry : mapMap.entrySet()) {
            if (displayHashMap.containsKey(entry.getKey())) {
                displayHashMap.get(entry.getKey()).add(entry.getValue());
            } else {
                List<String> displayList = new ArrayList<>();
                displayList.add(entry.getValue());
                displayHashMap.put(entry.getKey(), displayList);
            }
        }
    }

    // public void printSurface() {
    // for (int i = 0; i < rowSize; i++) {
    // for (int j = 0; j < colSize; j++) {
    // String displayID = Surface[i][j] == null ? "0" :
    // Surface[i][j].getDisplayID();
    // System.out.print(displayID + " ");
    // }
    // System.out.println();
    // }
    // }

}
