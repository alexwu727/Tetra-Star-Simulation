package main.java.Inhabitant;

import main.java.Locatable;
import main.java.Base.MapBase;

public class TetRover extends Inhabitant {
    public TetRover(int row, int col, int tID) {
        super(row, col, tID);
    }

    public void createBase() {
    }

    public boolean positionCheck(int row, int col) {
        return !(tFace.surface[row][col] instanceof Locatable);
    }

    public int nextActionEnterMapBase(MapBase mapBase) {
        return 0;
    }

    public void action() {
        walk();
    }
}
