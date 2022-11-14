package main.java.Base;

import main.java.Map.*;
import main.java.TFace;
public class MapBase extends Base {

    private boolean hasMap;
    Map map = null;

    public MapBase(int row, int col, String MapBID, String type){
        super(row, col, MapBID);
        String locID = "";
        if (type == "StarMap") {
            map = new StarMap(row, col, MapBID, locID);
        } else if (type == "StarAtlas") {
            map = new StarAltas(row, col, MapBID, locID);
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

        if (! hasMap) {
            return null;
        }
        Map temp = map;
        map = null;
        hasMap = false;
        return temp;
    }
}
