package main.java.Map;

import java.util.ArrayList;
import java.util.List;

public class StarAtlas extends MapClass {
    List<StarMap> starMaps = new ArrayList<StarMap>();



    public StarAtlas(int row, int col, int mID, int[] mIDs, String[] texts) {
        super(row, col, mID, false);
        for (int i = 0; i < mIDs.length; i++) {
            StarMap starMap = new StarMap(row, col, mIDs[i], texts[i], true);
            this.addStarMaps(starMap);
            // tFace.mapMap.put(tFace.convertToKey(row, col), getDisplayID());
        }
        itemCount = mIDs.length;
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