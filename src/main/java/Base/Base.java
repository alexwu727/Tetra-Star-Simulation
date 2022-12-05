package main.java.Base;

import main.java.Locatable;

public abstract class Base implements Locatable {
    protected int row;
    protected int col;
    protected String bID;

    public Base(int row, int col, String bID) {
        this.row = row;
        this.col = col;
        this.bID = bID;
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

    public String getBID() {
        return bID;
    }

}
