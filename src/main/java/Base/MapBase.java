package main.java.Base;

import main.java.StarMap;
import main.java.TFace;
public class MapBase extends Base {

    private boolean hasMap;
    private Map map = null;

    public MapBase(int row, int col, String MapBID, String type){
        super(row, col, MapBID);
        if (type = "StarMap") {
            map = new StarMap(row, col, MapBID);
        } else if (type = "StarAtlas") {
            map = new StarAtlas(row, col, MapBID);
        }
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
