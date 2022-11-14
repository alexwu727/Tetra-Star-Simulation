package main.java.Base;

import main.java.StarMap;

public class MapBase extends Base {

    public MapBase(int row, int col, MapBID){
        super(row, col, MapBID);
        tFace.addObject(this);

    }
    private StarMap starMap;

    public StarMap getStarMap() {
        return starMap;
    }

    public void setStarMap(StarMap starMap) {
        this.starMap = starMap;
    }

    public boolean hasMap() {
        return (starMap != null);
    }

}
