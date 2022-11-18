package main.java.Base;

import main.java.Map.*;

public class MapBase extends Base {

    private boolean hasMap;
    Map map = null;

    public MapBase(int row, int col, String MapBID) {
        super(row, col, MapBID);
        this.setDisplayID("MB");
    }

    public boolean hasMap() {
        return hasMap;
    }

    public void setMap(Map map) {
        this.map = map;
        this.hasMap = map == null ? false : true;
    }

    public Map getMap() {
        return map;
    }
}
