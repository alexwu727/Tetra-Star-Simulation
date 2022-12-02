package main.java.Map;

public class StarMap extends Map {
    public StarMap() {

    }

    public StarMap(int row, int col, int mID) {
        super(row, col, mID);
    }

    public StarMap(int row, int col, int mID, String text, boolean isLeaf) {
        super(row, col, mID);
        this.setText(text);
        if (isLeaf){
            this.setDisplayID("StarAtlas");
        } else {
            this.setDisplayID("StarMap");
        }
        tFace.mapMap.put(tFace.convertToKey(row, col), getDisplayID());
    }

    public StarMap(StarMap starMap) {
        super(starMap);
    }

    public StarMap clone() {
        return new StarMap(this);
    }
}
