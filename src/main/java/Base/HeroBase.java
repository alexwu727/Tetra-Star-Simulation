package main.java.Base;

import main.java.Map.MapClass;

import java.util.ArrayList;

public class HeroBase extends Base {
    private ArrayList<MapClass> cloneMaps = new ArrayList<MapClass>();

    public HeroBase(int row, int col, String bID) {
        super(row, col, bID);
    }

    public void cloneMap(MapClass clonedMap) {
        if (clonedMap != null) {
            clonedMap.setRow(row);
            clonedMap.setCol(col);
            cloneMaps.add(clonedMap);
        }
    }
}