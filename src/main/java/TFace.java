package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.Base.*;
import main.java.Inhabitant.Inhabitant;
import main.java.Map.MapClass;

public class TFace {
    private static TFace singleton = null;
    private int rowSize;
    private int colSize;
    public Locatable surface[][];
    public HashMap<String, List<String>> displayHashMap = new HashMap<String, List<String>>();
    private Map<String, Base> baseHashMap = new HashMap<String, Base>();
    private Map<String, MapClass> mapHashMap = new HashMap<String, MapClass>();
    private VaderBase vaderBase;
    public int tetVaderBaseRow;
    public int tetVaderBaseCol;

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

    public void setSurfaceSize(int row, int col) {
        this.rowSize = row;
        this.colSize = col;
        surface = new Locatable[row][col];
    }

    public VaderBase getVaderBase() {
        return vaderBase;
    }

    public void addObject(Locatable object) {
        int row = object.getRow();
        int col = object.getCol();
        surface[row][col] = object;
    }

    public void removeObject(Locatable object) {
        int row = object.getRow();
        int col = object.getCol();
        String key = convertToKey(object.getRow(), object.getCol());
        surface[row][col] = baseHashMap.containsKey(key) ? baseHashMap.get(key) : null;
    }

    public void addBase(Base base) {
        int row = base.getRow();
        int col = base.getCol();
        baseHashMap.put(base.getBID(), base);
        if (base instanceof VaderBase) {
            vaderBase = (VaderBase) base;
            addRiver(row, col);
            this.tetVaderBaseRow = row;
            this.tetVaderBaseCol = col;
        }
    }

    public Base getBase(int row, int col) {
        String key = convertToKey(row, col);
        return (Base) baseHashMap.get(key);
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
            surface[row][col] = river;
        }
    }

    public void addMap(int row, int col, MapClass map) {
        mapHashMap.put(convertToKey(row, col), map);
    }

    public void removeMap(int row, int col) {
        mapHashMap.remove(convertToKey(row, col));
    }

    public String convertToKey(int row, int col) {
        StringBuilder sb = new StringBuilder();
        sb.append(row + "," + col);
        return sb.toString();
    }

    public void updateDisplayHashMap() {
        displayHashMap = new HashMap<String, List<String>>();
        // bases
        for (Map.Entry<String, Base> entry : baseHashMap.entrySet()) {
            List<String> displayList = new ArrayList<>();
            displayList.add(entry.getValue().getClass().getSimpleName());
            displayHashMap.put(entry.getKey(), displayList);
        }

        // inhabitants
        for (int i = 0; i < surface.length; i++) {
            for (int j = 0; j < surface[0].length; j++) {
                if (surface[i][j] instanceof Inhabitant) {
                    String name = surface[i][j].getClass().getSimpleName();
                    String key = convertToKey(i, j);
                    if (displayHashMap.containsKey(key)) {
                        displayHashMap.get(key).add(name);
                    } else {
                        List<String> displayList = new ArrayList<>();
                        displayList.add(name);
                        displayHashMap.put(key, displayList);
                    }
                }
            }
        }

        // maps
        for (Map.Entry<String, MapClass> entry : mapHashMap.entrySet()) {
            MapClass map = entry.getValue();
            String name = map.isClone() ? "CloneMap" : map.getClass().getSimpleName();
            if (displayHashMap.containsKey(entry.getKey())) {
                displayHashMap.get(entry.getKey()).add(name);
            } else {
                List<String> displayList = new ArrayList<>();
                displayList.add(name);
                displayHashMap.put(entry.getKey(), displayList);
            }
        }
    }
}
