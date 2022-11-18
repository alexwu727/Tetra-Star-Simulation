package main.java;

public interface Locatable {
    public void setRow(int row);

    public void setCol(int col);

    public void setDisplayID(String displayID);

    public int getRow();

    public int getCol();

    public String getDisplayID();
}
