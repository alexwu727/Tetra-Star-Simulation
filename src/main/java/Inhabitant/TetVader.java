package main.java.Inhabitant;

import java.util.Stack;
import main.java.Locatable;
import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Base.River;
import main.java.Base.VaderBase;
import main.java.Map.MapClass;

public class TetVader extends Inhabitant {
    VaderBase vaderBase;
    Stack<int[]> moveStack = new Stack<>();

    public TetVader(int row, int col, int tID) {
        super(row, col, tID);
        this.tFlier = true;
        vaderBase = new VaderBase(row, col, tFace.convertToKey(row, col));
        tFace.addBase(vaderBase);
        moveStack.push(new int[] { row, col });
    }

    public boolean positionCheck(int row, int col) {
        Locatable object = tFace.surface[row][col];
        return !(object instanceof Inhabitant || object instanceof HeroBase || object instanceof River);
    }

    public int nextActionEnterMapBase(MapBase mapBase) {
        return mapBase.hasMap() ? 1 : 0;
    }

    public void action() {
        switch (nextAction) {
            case 0:
                walk();
                moveStack.push(new int[] { getRow(), getCol() });
                break;
            case 1:

                steal(((MapBase) tFace.getBase(getRow(), getCol())));
                moveStack.pop();
                break;
            case 2:

                backtrack();
                break;
        }
    }

    public void steal(MapBase mapBase) {
        MapClass map = (MapClass) mapBase.getMap();
        actionToConsole("Steals " + map.getName() + " in the map base.");
        map.updateMapLocation(tFace.tetVaderBaseRow, tFace.tetVaderBaseCol);
        mapBase.setMap(null);
        vaderBase.stealMap(map);
        this.nextAction = 2;
    }

    public void backtrack() {
        int[] pos = moveStack.pop();
        // if there is a rover the the way back home, wait 1 round.
        if (tFace.surface[pos[0]][pos[1]] instanceof Inhabitant) {
            moveStack.push(pos);
            actionToConsole(" Someone blocks the way, stands for one turn");
            return;
        }
        actionToConsole(" Backtracks.");
        flyTo(pos[0], pos[1]);
        if (tFace.tetVaderBaseRow == pos[0] && tFace.tetVaderBaseCol == pos[1]) {
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
