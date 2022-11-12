package main.java;

import java.util.HashMap;
import java.util.Map;

import main.java.Inhabitant.TetRover;

public class TFace {
    private int rowSize;
    private int colSize;
    public int Surface[][];
    public Map<String, Integer> baseMap = new HashMap<String, Integer>();

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

    public void addObject(TetRover tetRover) {
        int row = tetRover.getRow();
        int col = tetRover.getCol();
        Surface[row][col] = tetRover.getTID();
    }

    public void removeObject(TetRover tetRover) {
        int row = tetRover.getRow();
        int col = tetRover.getCol();
        String key = convertToKey(new int[] { row, col });
        Surface[row][col] = baseMap.containsKey(key) ? baseMap.get(key) : 0;
    }

    public void addBase(int row, int col, int baseID) {
        String key = convertToKey(new int[] { row, col });
        baseMap.put(key, baseID);
        if (baseMap.containsKey(key)) {
            System.out.println(baseMap.get(key));
        }
        Surface[row][col] = baseID;
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
        Surface = new int[row][col];
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
