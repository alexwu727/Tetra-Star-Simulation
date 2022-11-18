package main.java.Base;

import main.java.Map.StarMap;

import java.util.HashMap;

public class VaderBase extends Base {

    private HashMap<Integer, StarMap> stolenMaps = null;

    public VaderBase(int row, int col, String VaderBID) {
        super(row, col, VaderBID);
        stolenMaps = new HashMap<Integer, StarMap>();
        this.setDisplayID("VB");
    }

    public HashMap<Integer, StarMap> getStolenMaps() {
        return stolenMaps;
    }

    public void stealMap(StarMap map) {
        if (map != null) {
            stolenMaps.put(map.getMID(), map);
        }
    }

    public StarMap removeMap(int mapID) {
        StarMap temp = stolenMaps.get(mapID);
        stolenMaps.remove(mapID);
        return temp;
    }

}