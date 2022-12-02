package main.java.Map;

import java.util.ArrayList;
import java.util.List;

public class StarAltas extends Map {
    List<StarMap> starMaps;

    public StarAltas() {

    }

    public StarAltas(int row, int col, int mID) {
        super(row, col, mID);
        starMaps = new ArrayList<StarMap>();
    }

    public StarAltas(int row, int col, int mID, int[] mIDs, String[] texts) {
        super(row, col, mID);
        starMaps = new ArrayList<StarMap>();
        for (int i = 0; i < mIDs.length; i++) {
            StarMap starMap = new StarMap(row, col, mIDs[i], texts[i]);
            this.addStarMaps(starMap);
            this.setDisplayID("StarAltas");
        }
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
        this.starMaps.add(starMap);
    }
}