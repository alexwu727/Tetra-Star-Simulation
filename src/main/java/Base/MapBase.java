package main.java.Base;

import main.java.StarMap;
import main.java.TFace;
public class MapBase extends Base {

    private boolean hasMap;
    private Map map;

    public MapBase(int row, int col, MapBID, TFace tFace){
        super(row, col, MapBID);
        tFace.addBase(this);
    }

    public boolean hasMap() {
        return hasMap;
    }

    public void setMap (Map map) {
        if (hasMap) { return; }
        this.map = map;
        this.hasMap = true;
    }

    public Map getMap() {
        if (hasMap) {
            Map temp = map;
            map = null;
            hasMap = false;
            return temp;
        }
    }
}
