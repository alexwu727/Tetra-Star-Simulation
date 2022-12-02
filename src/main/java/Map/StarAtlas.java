package main.java.Map;

import java.util.ArrayList;
import java.util.List;

public class StarAtlas extends Map {
    List<StarMap> starMaps;

    public StarAtlas() {

    }

    public StarAtlas(int row, int col, int mID) {
        super(row, col, mID);
        starMaps = new ArrayList<StarMap>();
    }

    public StarAtlas(int row, int col, int mID, int[] mIDs, String[] texts) {
        super(row, col, mID);
        this.setDisplayID("StarAtlas");
        tFace.mapMap.put(tFace.convertToKey(row, col), getDisplayID());
        starMaps = new ArrayList<StarMap>();
        for (int i = 0; i < mIDs.length; i++) {
            StarMap starMap = new StarMap(row, col, mIDs[i], texts[i], true);
            this.addStarMaps(starMap);
            // tFace.mapMap.put(tFace.convertToKey(row, col), getDisplayID());
        }
    }

    public StarAtlas(StarAtlas starAtlas) {
        super(starAtlas);
    }

    public StarAtlas clone() {
        return new StarAtlas(this);
    }

    public List<StarMap> getStarMaps() {
        return starMaps;
    }

    public void addStarMaps(StarMap starMap) {
        this.starMaps.add(starMap);
    }
}