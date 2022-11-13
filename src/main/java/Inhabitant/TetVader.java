package main.java.Inhabitant;

import main.java.Locatable;

public class TetVader extends TetRover {
    public TetVader(int row, int col, int tID) {
        super(row, col, tID);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean positionCheck(int row, int col) {
        // return !(tFace.Surface[row][col] instanceof HeroBase ||
        // tFace.Surface[row][col] instanceof Inhabitant);
        return !(tFace.Surface[row][col] instanceof Inhabitant);
    }

    // public void steal(StarMap starMap) {
    // starMap.setRow(getTetVaderBaseCol());
    // starMap.setCol(getTetVaderBaseRow());
    // tFace.removeObject(starMap);
    // tFace.addObject(starMap);
    // }

    public void backtrack() {

    }
}
