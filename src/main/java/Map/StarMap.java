package main.java.Map;

import main.java.Base.MapBase;

public class StarMap extends Map {
    public StarMap() {

    }

    public StarMap(int row, int col, int mID) {
        super(row, col, mID);
    }

    public StarMap(int row, int col, int mID, String text, boolean isLeaf) {
        this.row = row;
        this.col = col;
        this.mID = mID;
        this.itemCount = 0;

        this.setText(text);
        this.setDisplayID("StarMap");
        if (!isLeaf){
            mapBase = new MapBase(row, col, tFace.convertToKey(row, col));
            mapBase.setMap(this);
            mapBase.setMapID(mID);
            tFace.addBase(mapBase);
            tFace.addObject(mapBase);
            tFace.mapMap.put(tFace.convertToKey(row, col), getDisplayID());
        }

    }

    public StarMap(StarMap starMap) {
        super(starMap);
    }

    public StarMap clone() {
        return new StarMap(this);
    }
}
