package main.java.Base;

import main.java.Locatable;

public class Base implements Locatable {
    private int displayID;
    private int row;
    private int col;

    public Base(int row, int col) {
        this.row = row;
        this.col = col;
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
    public void setDisplayID(int displayID) {
        this.displayID = displayID;

    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getDisplayID() {
        return displayID;
    }

}
