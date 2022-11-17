package main.java.Map;

public class StarAltas extends Map {
    public StarAltas() {

    }

    public StarAltas(int row, int col, int mID) {
        super(row, col, mID);
    }

    public StarAltas(StarAltas starAltas) {
        super(starAltas);
    }

    public StarAltas clone() {
        return new StarAltas(this);
    }
}