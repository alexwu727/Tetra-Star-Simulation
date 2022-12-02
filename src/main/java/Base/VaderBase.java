package main.java.Base;

import main.java.Map.Map;

import java.util.HashMap;

public class VaderBase extends Base {

    private HashMap<Integer, Map> stolenMaps = null;

    public VaderBase(int row, int col, String VaderBID) {
        super(row, col, VaderBID);
        stolenMaps = new HashMap<Integer, Map>();
        this.setDisplayID("VaderBase");
    }

    public HashMap<Integer, Map> getStolenMaps() {
        return stolenMaps;
    }

    public void stealMap(Map map) {
        if (map != null) {
            stolenMaps.put(map.getMID(), map);
        }
    }

    public Map removeMap(int mapID) {
        Map temp = stolenMaps.get(mapID);
        stolenMaps.remove(mapID);
        return temp;
    }

}