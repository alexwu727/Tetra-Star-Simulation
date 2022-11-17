package main.java.Inhabitant;

import java.util.*;

import main.java.Locatable;
import main.java.TFace;
import main.java.Base.MapBase;

public class TetRover implements Locatable {
    protected int tID;
    protected int displayID;
    protected int row;
    protected int col;
    protected int nextAction;
    protected TFace tFace = TFace.instance();;
    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public TetRover(int row, int col, int tID) {
        this.row = row;
        this.col = col;
        this.tID = tID;
        tFace.addObject(this);

    }

    public void setDisplayID(int displayID) {
        this.displayID = displayID;

    }

    public int getDisplayID() {
        return displayID;
    }

    public int getTID() {
        return tID;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int r) {
        this.row = r;
    }

    public void setCol(int c) {
        this.col = c;
    }

    public boolean positionCheck(int row, int col) {
        return !(tFace.Surface[row][col] instanceof Locatable);
    }

    public int nextActionEnterMapBase(MapBase mapBase) {
        return 0;
    }

    public void action() {
        walk();
    }

    public void walk() {

        List<int[]> possiblePositions = new ArrayList<int[]>();
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            // if not valid -> continue
            if (newRow < 0 || newRow >= tFace.getRowSize() || newCol < 0 || newCol >= tFace.getColSize()) {
                continue;
            }
            // check if there is other object there
            if (positionCheck(newRow, newCol)) {
                int[] tmp = { newRow, newCol };
                possiblePositions.add(tmp);
            }
        }
        if (possiblePositions.size() == 0) {
            System.out.println(getDisplayID() + " has no possible position to walk, stand for a round.");
            return;
        }
        int[] newPosition = possiblePositions.get((int) (Math.random() * possiblePositions.size()));
        if (tFace.Surface[newPosition[0]][newPosition[1]] instanceof MapBase) {
            this.nextAction = nextActionEnterMapBase((MapBase) tFace.Surface[newPosition[0]][newPosition[1]]);
        } else {
            this.nextAction = 0;
        }
        tFace.removeObject(this);
        setRow(newPosition[0]);
        setCol(newPosition[1]);
        System.out.println(getDisplayID() + " walks to (" + newPosition[0] + ", " + newPosition[1] + ").");
        tFace.addObject(this);
    }

}
