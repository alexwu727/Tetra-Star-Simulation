package main.java.Base;

import main.java.Locatable;

public class River implements Locatable {
    private int row;
    private int col;
    private int displayID = 10;

    public River(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void setRow(int row) {

    }

    @Override
    public void setCol(int col) {

    }

    @Override
    public void setDisplayID(int displayID) {

    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getCol() {
        return 0;
    }

    @Override
    public int getDisplayID() {
        return displayID;
    }
}
