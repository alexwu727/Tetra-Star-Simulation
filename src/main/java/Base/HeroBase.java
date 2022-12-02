package main.java.Base;

import main.java.TFace;
import main.java.Map.Map;

import java.util.ArrayList;

public class HeroBase extends Base {
    private ArrayList<Map> cloneMaps = null;

    public HeroBase(int row, int col, String bID) {
        super(row, col, bID);
        cloneMaps = new ArrayList<Map>();
        this.setDisplayID("HeroBase");
    }

    public void cloneMap(Map clonedMap) {
        if (clonedMap != null) {
            clonedMap.setRow(row);
            clonedMap.setCol(col);
            cloneMaps.add(clonedMap);
        }
    }
}