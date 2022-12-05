package main.java.Base;

import main.java.Map.*;

public class MapBase extends Base {
    private boolean hasMap;
    MapClass map = null;
    int mapID;

    public MapBase(int row, int col, String bID) {
        super(row, col, bID);
    }

    public boolean hasMap() {
        return hasMap;
    }

    public MapClass getMap() {
        return map;
    }

    public void setMap(MapClass map) {
        this.map = map;
        this.hasMap = map == null ? false : true;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }
}
