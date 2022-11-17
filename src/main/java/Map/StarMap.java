package main.java.Map;

public class StarMap extends Map {
    public StarMap() {

    }

    public StarMap(int row, int col, int mID) {
        super(row, col, mID);
    }

    public StarMap(StarMap starMap) {
        super(starMap);
    }

    public StarMap clone() {
        return new StarMap(this);
    }
}
