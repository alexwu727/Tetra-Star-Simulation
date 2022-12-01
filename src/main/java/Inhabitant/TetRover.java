package main.java.Inhabitant;

import java.util.*;

import main.java.BackendConsole;
import main.java.Locatable;
import main.java.TFace;
import main.java.Base.MapBase;

public class TetRover implements Locatable {
    protected int tID;
    protected String displayID;
    protected int row;
    protected int col;
    protected int nextAction;
    protected TFace tFace = TFace.instance();
    protected boolean tFlier = false;
    int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    int[][] extraDirections = new int[][] { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

    public TetRover(int row, int col, int tID) {
        this.row = row;
        this.col = col;
        this.tID = tID;
        tFace.addObject(this);

    }

    public void setDisplayID(String displayID) {
        this.displayID = displayID;
    }

    public String getDisplayID() {
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

    public List<int[]> possiblePositions(int[][] directions) {
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
                possiblePositions.add(new int[] { newRow, newCol });
            }
        }
        return possiblePositions;
    }

    public void setWalkDirections(int walkDirectionIndex) {
        switch (walkDirectionIndex) {
            case 0:
                directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
                extraDirections = new int[][] { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
                break;
            // up
            case 1:
                directions = new int[][] { { -1, 0 } };
                extraDirections = new int[][] { { -2, 0 } };
                break;
            // down
            case 2:
                directions = new int[][] { { 1, 0 } };
                extraDirections = new int[][] { { 2, 0 } };
                break;
            // left
            case 3:
                directions = new int[][] { { 0, -1 } };
                extraDirections = new int[][] { { 0, -2 } };
                break;
            // right
            case 4:
                directions = new int[][] { { 0, 1 } };
                extraDirections = new int[][] { { 0, 2 } };
                break;
            default:
                directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
                extraDirections = new int[][] { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
                break;
        }
    }

    public void walk() {
        List<int[]> possiblePositions = possiblePositions(directions);
        if (possiblePositions.size() == 0) {
            if (this.tFlier) {
                possiblePositions = possiblePositions(extraDirections);
                if (possiblePositions.size() == 0) {
                    actionToConsole("Has no possible position to walk, stands for a round.");
                    return;
                }
            } else {
                actionToConsole("Has no possible position to walk, stands for a round.");
                return;
            }
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
        actionToConsole("Walks to (" + newPosition[0] + ", " + newPosition[1] + ").");
        tFace.addObject(this);
    }

    public void actionToConsole(String text) {
        BackendConsole.addConsole(this.getClass().getSimpleName() + " " + tID + ": " + text);
    }
}
