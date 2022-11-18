package main.java.Map;

import main.java.TFace;
import main.java.Base.MapBase;
import main.java.Inhabitant.TetHero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Map {
    protected int mID;
    protected int row;
    protected int col;
    protected TFace tFace = TFace.instance();
    protected MapBase mapBase;
    protected int itemCount;
    protected String text;
    protected boolean isEncrypted;
    protected String encryptSymbol = "*";
    protected int encryptHeroID;
    protected int restorationCounter = 0;
    protected List<Integer> heroList = new ArrayList<>();
    protected HashMap<String, String> header;
    protected String body;

    public Map() {

    }

    public Map(int row, int col, int mID) {
        this.row = row;
        this.col = col;
        this.mID = mID;
        this.itemCount = 0;
        mapBase = new MapBase(row, col, tFace.convertToKey(row, col));
        mapBase.setMap(this);
        tFace.addBase(mapBase);
    }

    public Map(Map map) {
        if (map != null) {
            this.mapBase = map.mapBase;
            this.text = map.text;
            this.isEncrypted = map.isEncrypted;
            this.encryptSymbol = map.encryptSymbol;
            this.encryptHeroID = map.encryptHeroID;
            this.restorationCounter = map.restorationCounter;
            this.heroList = map.heroList;
            this.header = map.header;
            this.body = map.body;
        }
    }

    public abstract Map clone();

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getMID() {
        return mID;
    }

    public MapBase getMapBase() {
        return mapBase;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void encrpyt() {
        isEncrypted = true;
    }

    public void decrpyt() {
        isEncrypted = false;
    }

    public int getEncryptHeroID() {
        return encryptHeroID;
    }

    public void setEncryptHeroID(int encryptHeroID) {
        this.encryptHeroID = encryptHeroID;
    }

    public String getEncryptSymbol() {
        return encryptSymbol;
    }

    public int getRestorationCounter() {
        return restorationCounter;
    }

    public void setRestorationCounter(int restorationCounter) {
        this.restorationCounter = restorationCounter;
    }

    public void addHero(TetHero tetHero) {
        heroList.add(tetHero.getTID());
    }

}
