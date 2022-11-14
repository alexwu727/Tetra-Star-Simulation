package main.java.Base;

import main.java.Locatable;
import main.java.TFace;
import main.java.Map.Map;

public class VaderBase extends Base {

    private HashMap<String, Map> stolenMaps = null;
    public VaderBase(int row, int col, String VaderBID){
        super(row, col, VaderBID);
        stolenMaps = new HashMap<String, Map>();
    }

    public void stealMap (Map map) {
        if (map != null) {
            stolenMaps.put(map.getMID(), map);
        }
    }

    public Map removeMap (String mapID){
        Map temp = stolenMaps.get(mapID);
        stolenMaps.remove(mapID);
        return temp;
    }


}