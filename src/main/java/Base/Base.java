package main.java.Base;

import main.java.Locatable;

public abstract class Base implements Locatable {
    private int displayID;
    private int row;
    private int col;
    private int bID;

    public Base(int row, int col, int bID) {
        this.row = row;
        this.col = col;
        this.bID = bID;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public void setCol(int col) { this.col = col; }

    @Override
    public int getRow() { return row; }

    @Override
    public int getCol() { return col; }

    public int getBID() { return bID; }

    @Override
    public void setDisplayID(int displayID) { this.displayID = displayID; }

    @Override
    public int getDisplayID() { return displayID; }

}
