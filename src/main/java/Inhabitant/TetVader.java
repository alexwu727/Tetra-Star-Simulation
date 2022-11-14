package main.java.Inhabitant;

import main.java.Locatable;
import main.java.StarMap;
import main.java.TFace;
import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Base.VaderBase;

public class TetVader extends TetRover {
    VaderBase vaderBase;

    public TetVader(int row, int col, int tID, TFace tFace) {
        super(row, col, tID, tFace);
        vaderBase = new VaderBase(row, col, tID);
        vaderBase.setDisplayID(tID + 20);
        tFace.addBase(vaderBase);
        tFace.addRiver(row, col);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean positionCheck(int row, int col) {
        // return !(tFace.Surface[row][col] instanceof HeroBase ||
        // tFace.Surface[row][col] instanceof Inhabitant);

        return !(tFace.Surface[row][col] instanceof Inhabitant || tFace.Surface[row][col] instanceof HeroBase);
    }

    @Override
    public void setNextAction(int row, int col) {
        // if (tFace.Surface[row][col] instanceof Base)
        // int mapID = tFace.Surface[row][col].getMapID();
        // encrypt(mapID)
        if (tFace.Surface[row][col] instanceof MapBase && ((MapBase) tFace.Surface[row][col]).hasMap()) {
            this.nextAction = 1;
        } else {
            this.nextAction = 0;
        }
    }

    @Override
    public void action() {
        switch (nextAction) {
            case 0:
                walk();
                System.out.println("Vader " + getDisplayID() + " walks");
                break;
            case 1:
                steal(((MapBase) tFace.getBase(getRow(), getCol())));
        }
        setNextAction(getRow(), getCol());
    }

    public void steal(MapBase mapBase) {
        StarMap starMap = mapBase.getStarMap();
        starMap.setRow(tFace.TetVaderBaseRow);
        starMap.setCol(tFace.TetVaderBaseCol);
        mapBase.setStarMap(null);
    }

    public void backtrack() {

    }
}
