package main.java.Map;

public class StarMap extends MapClass {

    public StarMap(int row, int col, int mID, String text, boolean inAtlas) {
        super(row, col, mID, inAtlas);
        this.setText(text);
        this.itemCount = 1;
    }

    public StarMap(StarMap starMap) {
        super(starMap);
    }

    public StarMap clone() {
        return new StarMap(this);
    }
}
