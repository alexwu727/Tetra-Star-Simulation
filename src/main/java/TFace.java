package main.java;

import java.util.HashMap;
import java.util.Map;

import main.java.Inhabitant.TetRover;

public class TFace {
    private int rowSize;
    private int colSize;
    public Locatable Surface[][];
    public Map<String, Locatable> baseMap = new HashMap<String, Locatable>();

    public TFace(int row, int col) {
        rowSize = row;
        colSize = col;
        setSurfaceSize(row, col);
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
        String key = convertToKey(new int[] { row, col });
        Surface[row][col] = baseMap.containsKey(key) ? baseMap.get(key) : null;
    }

    public void addBase(Locatable object) {
        int row = object.getRow();
        int col = object.getCol();
        String key = convertToKey(new int[] { row, col });
        baseMap.put(key, object);
        Surface[row][col] = object;
    }

    public String convertToKey(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0] + "," + arr[1]);
        return sb.toString();
    }

    // public void setBase() {
    // for (Map.Entry<Integer[], Integer> entry : baseMap.entrySet()) {
    // Surface[entry.getKey()[0]][entry.getKey()[1]] = entry.getValue();
    // }
    // }

    public void setSurfaceSize(int row, int col) {
        Surface = new Locatable[row][col];
    }

    public void printSurface() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                System.out.print(Surface[i][j] + " ");
            }
            System.out.println();
        }
    }

}
