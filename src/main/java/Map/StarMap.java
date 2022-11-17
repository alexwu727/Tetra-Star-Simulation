package main.java.Map;

import java.util.ArrayList;
import java.util.List;

import main.java.Base.MapBase;
import main.java.Inhabitant.TetHero;
import main.java.TFace;

public class StarMap extends Map {

    private int row;
    private int col;

    public StarMap(int row, int col, int mID, TFace tFace) {
        super(row, col, mID, tFace);
        String bID = tFace.convertToKey(row, col);
        mapBase = new MapBase(row, col, bID);
        mapBase.setMap(this);
        mapBase.setDisplayID(7);
        tFace.addBase(mapBase);
    }

    private int displayID;
    private MapBase mapBase;

    private String text;
    private boolean isEncrypted;
    private String encryptSymbol = "*";

    private int encryptHeroID;
    private int restorationCounter = 0;
    private List<Integer> heroList = new ArrayList<>();

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;

    }

    @Override
    public void setDisplayID(int displayID) {

    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public int getDisplayID() {
        return 0;
    }

    public MapBase getMapBase() {
        return mapBase;
    }

    public int getMID() {
        return this.mID;
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

    public void encrpyt() {
        isEncrypted = true;
    }

    public void decrpyt() {
        isEncrypted = false;
    }
}
