package main.java.Map;

import java.util.ArrayList;
import java.util.List;

import main.java.Base.MapBase;
import main.java.Inhabitant.TetHero;

public class StarAltas extends Map {

    public StarAltas(int row, int col, String mID, String locID) {
        super(row, col, mID, locID);
    }

    @Override
    public void setDisplayID(int displayID) {

    }

    @Override
    public int getDisplayID() {
        return 0;
    }
}