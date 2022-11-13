package main.java.Base;

import main.java.StarMap;

public class MapBase extends Base {
    private StarMap starMap;

    public StarMap getStarMap() {
        return starMap;
    }

    public void setStarMap(StarMap starMap) {
        this.starMap = starMap;
    }

    public MapBase(int row, int col) {
        super(row, col);
    }

    public boolean hasMap() {
        return (starMap != null);
    }

}
