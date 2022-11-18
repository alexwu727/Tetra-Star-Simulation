package main.java.Base;

import main.java.Map.Map;

import java.util.ArrayList;

public class HeroBase extends Base {
    private ArrayList<Map> cloneMaps = null;

    public HeroBase(int row, int col, String bID) {
        super(row, col, bID);
        cloneMaps = new ArrayList<Map>();
        this.setDisplayID("HB");
    }

    public void cloneMap(Map clone) {
        if (clone != null) {
            cloneMaps.add(clone);
        }
    }
}