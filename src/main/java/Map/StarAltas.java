package main.java.Map;

import java.util.ArrayList;
import java.util.List;

import main.java.TFace;
import main.java.Base.MapBase;
import main.java.Inhabitant.TetHero;

public class StarAltas extends Map {
    private MapBase mapBase;

    public StarAltas(int row, int col, int mID, TFace tFace) {
        super(row, col, mID, tFace);
        String bID = tFace.convertToKey(row, col);
        mapBase = new MapBase(row, col, bID);
        mapBase.setMap(this);
        mapBase.setDisplayID(7);
        tFace.addBase(mapBase);
    }

    @Override
    public void setDisplayID(int displayID) {

    }

    @Override
    public int getDisplayID() {
        return 0;
    }
}