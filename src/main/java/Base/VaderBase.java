package main.java.Base;

import main.java.Locatable;
import main.java.TFace;
import main.java.Map.Map;

public class VaderBase extends Base {

    private ArrayList<Map> stolenMaps = null;
    public VaderBase(int row, int col, VaderBID){
        super(row, col, VaderBID);
        tFace.addObject(this);
        River(row, col);
        stolenMaps = new ArrayList<Map>();
    }


}