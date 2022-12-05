package main.java.Base;

import main.java.Map.MapClass;

import java.util.HashMap;

public class VaderBase extends Base {
    private HashMap<Integer, MapClass> stolenMaps = null;

    public VaderBase(int row, int col, String bID) {
        super(row, col, bID);
        stolenMaps = new HashMap<Integer, MapClass>();
    }

    public HashMap<Integer, MapClass> getStolenMaps() {
        return stolenMaps;
    }

    public void stealMap(MapClass map) {
        if (map != null) {
            stolenMaps.put(map.getMID(), map);
        }
    }

    public MapClass removeMap(int mapID) {
        MapClass map = stolenMaps.get(mapID);
        stolenMaps.remove(mapID);
        return map;
    }

    public MapClass getMap(int mapID) {
        return stolenMaps.get(mapID);
    }

}