package main.java.Inhabitant;

import java.util.Stack;

import main.java.Locatable;
import main.java.Map.*;
import main.java.TFace;
import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Base.VaderBase;

public class TetVader extends TetRover {
    VaderBase vaderBase;
    Stack<int[]> moveStack = new Stack<>();

    public TetVader(int row, int col, int tID, TFace tFace) {
        super(row, col, tID, tFace);
        String bID = tFace.convertToKey(row, col);
        vaderBase = new VaderBase(row, col, bID);
        vaderBase.setDisplayID(tID + 20);
        tFace.addBase(vaderBase);
        moveStack.push(new int[] { row, col });
    }

    @Override
    public boolean positionCheck(int row, int col) {
        return !(tFace.Surface[row][col] instanceof TetRover || tFace.Surface[row][col] instanceof HeroBase);
    }

    @Override
    public int nextActionEnterMapBase(MapBase mapBase) {
        return mapBase.hasMap() ? 1 : 0;
    }

    @Override
    public void action() {
        switch (nextAction) {
            case 0:
                walk();
                moveStack.push(new int[] { getRow(), getCol() });
                break;
            case 1:
                System.out.println("Vader " + getDisplayID() + " steals the map in the map base.");
                steal(((MapBase) tFace.getBase(getRow(), getCol())));
                break;
            case 2:
                System.out.println("Vader " + getDisplayID() + " backtracks.");
                backtrack();
                break;
        }
    }

    public void steal(MapBase mapBase) {
        StarMap starMap = (StarMap) mapBase.getMap();
        starMap.setRow(tFace.TetVaderBaseRow);
        starMap.setCol(tFace.TetVaderBaseCol);
        mapBase.setMap(null);
        vaderBase.stealMap(starMap);
        this.nextAction = 2;
    }

    public void backtrack() {
        int[] pos = moveStack.pop();
        flyTo(pos[0], pos[1]);
        if (tFace.TetVaderBaseRow == pos[0] && tFace.TetVaderBaseCol == pos[1]) {
            this.nextAction = 0;
            moveStack.push(new int[] { pos[0], pos[1] });
        }
    }

    private void flyTo(int row, int col) {
        tFace.removeObject(this);
        setRow(row);
        setCol(col);
        tFace.addObject(this);
    }
}
