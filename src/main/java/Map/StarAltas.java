package main.java.Map;

import java.util.List;

public class StarAltas extends Map {
    List<StarMap> starMaps;

    public StarAltas() {

    }

    public StarAltas(int row, int col, int mID) {
        super(row, col, mID);
    }

    public StarAltas(StarAltas starAltas) {
        super(starAltas);
    }

    public StarAltas clone() {
        return new StarAltas(this);
    }

    public List<StarMap> getStarMaps() {
        return starMaps;
    }

    public void addStarMaps(StarMap starMap) {
        starMaps.add(starMap);
    }
}