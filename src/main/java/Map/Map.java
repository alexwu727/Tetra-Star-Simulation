package main.java.Map;

public abstract class Map implements Locatable{
    private String mID;
    private int row;
    private int col;

    private int itemCount;

    private HashMap<String, String> header;
    private String body;

    public Map(int row, int col, String mID, String locID) {
        this.row = row;
        this.col = col;
        this.mID = mID;
        this.locID = locID;
        this.itemCount = 0
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

    public String getMId(){ return mID; }


}
