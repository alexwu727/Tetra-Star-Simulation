package main.java.Base;

import main.java.Locatable;
import main.java.TFace;

public class River implements Locatable {
    private int row;
    private int col;

    public River(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
