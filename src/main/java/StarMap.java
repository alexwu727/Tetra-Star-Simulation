package main.java;

import java.util.ArrayList;
import java.util.List;

import main.java.Base.MapBase;
import main.java.Inhabitant.TetHero;

public class StarMap {
    private int displayID;
    private int row;
    private int col;
    private MapBase mapBase;
    private int starMapID;

    private String text;
    private boolean isEncrypted;
    private String encryptSymbol = "*";
    private int encryptHeroID;
    private int restorationCounter = 0;
    private List<Integer> heroList = new ArrayList<>();

    public StarMap(TFace tFace, int row, int col, int starMapID, String text) {
        this.row = row;
        this.col = col;
        this.starMapID = starMapID;
        this.text = text;
        mapBase = new MapBase(row, col);
        mapBase.setStarMap(this);
        mapBase.setDisplayID(7);
        tFace.addBase(mapBase);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public MapBase getMapBase() {
        return mapBase;
    }

    public int getStarMapID() {
        return starMapID;
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

    public int getEncryptHeroID() {
        return encryptHeroID;
    }

    public void setEncryptHeroID(int encryptHeroID) {
        this.encryptHeroID = encryptHeroID;
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
