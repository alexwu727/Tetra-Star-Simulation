package main.java.Map;

import main.java.Locatable;
import main.java.TFace;
import main.java.Base.MapBase;

import java.util.HashMap;

public abstract class Map implements Locatable {
    int mID;
    private int row;
    private int col;
    private TFace tFace;
    private MapBase mapBase;
    private int itemCount;

    private HashMap<String, String> header;
    private String body;

    public Map(int row, int col, int mID, TFace tFace) {
        this.row = row;
        this.col = col;
        this.mID = mID;
        this.tFace = tFace;
        this.itemCount = 0;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    public int getMID() {
        return mID;
    }

    public MapBase getMapBase() {
        return mapBase;
    }

}
