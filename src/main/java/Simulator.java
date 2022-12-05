package main.java;

import main.java.Inhabitant.Inhabitant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Simulator {
    static Scenario scenario;
    static int currentScenarioIndex;
    static int helper;
    private static HashMap<Integer, Scenario> scenarioHashMap = new HashMap<Integer, Scenario>();
    static ArrayList<String> occupied = new ArrayList<>();
    static Random rand = new Random();

    public static void start(int scenarioIndex) {
        helper = 15;
        TFace.clear();
        scenario = scenarioHashMap.get(scenarioIndex);
        currentScenarioIndex = scenarioIndex;
        TFace tFace = TFace.instance();
        tFace.setSurfaceSize(scenario.sufaceRowSize, scenario.sufaceColSize);
        scenario.createInstances();
        TFace.instance().updateDisplayHashMap();
    }

    private static int[] getAvailableLoc(int row, int col, boolean isVader) {
        int currRow = rand.nextInt(row), currCol = rand.nextInt(col);
        while (occupied.contains(currRow + "," + currCol)) {
            currRow = rand.nextInt(row);
            currCol = rand.nextInt(col);
        }
        occupied.add(currRow + "," + currCol);
        if (isVader) {
            if ( currRow-1 >= 0 ) { occupied.add(currRow-1 + "," + currCol);}
            if ( currRow+1 < row ) { occupied.add(currRow+1 + "," + currCol);}
            if ( currCol-1 >= 0 ) { occupied.add(currRow + "," + (currCol-1));}
            if ( currCol-1 < col ) { occupied.add(currRow + "," + currCol+1);}
        }
        return new int[]{currRow, currCol};
    }
    public static void startWithArgs(int[] scenarioArgs) {
        TFace.clear();
        int row = scenarioArgs[4], col =  scenarioArgs[5];
        scenario = scenarioHashMap.get(4);
        scenario.resetOldInhibitants();
        scenario.sufaceRowSize = row;
        scenario.sufaceColSize = col;
        occupied.clear();

        int[] heroLoc = rand.ints(0, (2 * row + 2 * col) - 5).distinct().limit(scenarioArgs[0]).toArray();

        for (int i = 0; i < scenarioArgs[0]; i++) {
            int CurrRow, CurrCol;
            if (0 <= heroLoc[i] && heroLoc[i] < col) {
                CurrRow = 0; CurrCol = heroLoc[i];
            } else if (col <= heroLoc[i] && heroLoc[i] < row + col - 2) {
                CurrRow = heroLoc[i] - row; CurrCol = col - 1;
            } else if (row + col - 1 <= heroLoc[i] && heroLoc[i] < row + 2*col - 2) {
                CurrRow = row - 1; CurrCol = row + 2*col - 3 - heroLoc[i];
            } else {
                CurrRow = 2 * (row + col) - 4 - heroLoc[i]; CurrCol = 0;
            }
            scenario.addInhabitantArgs("TetHero", CurrRow, CurrCol, i, 0);
            occupied.add(row + "," + col);
        }

        scenario.addInhabitantArgs("TetVader", getAvailableLoc(row, col, true)[0], getAvailableLoc(row, col, true)[1], 0, 0);

        for (int i = 0; i < scenarioArgs[1]; i++) {
            scenario.addInhabitantArgs("TetRover", getAvailableLoc(row, col, false)[0], getAvailableLoc(row, col, false)[1], i, 0);
        }
        for (int i = 0; i < scenarioArgs[2]; i++) {
            scenario.addStarMapArgs(getAvailableLoc(row, col, false)[0], getAvailableLoc(row, col, false)[1], i, "hello world" + i);
        }
        for (int i = 0; i < scenarioArgs[3]; i++) {
            scenario.addStarAtlasArgs(getAvailableLoc(row, col, false)[0], getAvailableLoc(row, col, false)[1], i,
                    new int[] { 1, 2, 3, 4 }, new String[] { "hel" + i, "lo" + i, "wor" + i, "ld" + i });
        }

        TFace tFace = TFace.instance();
        tFace.setSurfaceSize(scenario.sufaceRowSize, scenario.sufaceColSize);
        scenario.createInstances();
        TFace.instance().updateDisplayHashMap();
    }

    public static void nextFrame() {
        for (Inhabitant inhabitant : scenario.inhibitantList) {
            inhabitant.action();
        }
        if (currentScenarioIndex == 3) {
            helper--;
            if (helper == 0) {
                scenario.inhibitantList.get(0).setWalkDirections(1);
                scenario.inhibitantList.get(1).setWalkDirections(3);
            }
        }
        TFace.instance().updateDisplayHashMap();

    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void bar(String str) {
        System.out.println("-------------------- " + str + " --------------------");
    }

    public static void createScenarios() {
        // scenario 0 - random walk
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addInhabitantArgs("TetHero", 0, 0, 1, 0);
        scenario.addInhabitantArgs("TetHero", 5, 9, 2, 0);
        scenario.addInhabitantArgs("TetVader", 4, 4, 3, 0);
        scenario.addInhabitantArgs("TetRover", 2, 3, 4, 0);
        scenario.addInhabitantArgs("TetRover", 9, 8, 5, 0);
        scenario.addStarMapArgs(7, 2, 0, "hello world");
        scenario.addStarAtlasArgs(2, 7, 0, new int[] { 1, 2, 3, 4 }, new String[] { "hel", "lo", "wor", "ld" });
        scenarioHashMap.put(0, scenario);

        // scenario 1 - hero enters the mapbase with a map
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addInhabitantArgs("TetHero", 3, 0, 1, 4);
        scenario.addInhabitantArgs("TetVader", 9, 3, 2, 1);
        scenario.addStarAtlasArgs(3, 3, 0, new int[] { 1, 2, 3, 4 }, new String[] { "hel", "lo", "wor", "ld" });
        scenarioHashMap.put(1, scenario);

        // scenario 2 - hero enters the mapbase without a map
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addInhabitantArgs("TetHero", 3, 0, 1, 4);
        scenario.addInhabitantArgs("TetVader", 6, 4, 2, 1);
        scenario.addStarMapArgs(3, 4, 0, "hello world");
        scenarioHashMap.put(2, scenario);

        // scenario 3 - hero enters the mapbase with a encrypted map (by others)
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addInhabitantArgs("TetHero", 3, 0, 1, 4);
        scenario.addInhabitantArgs("TetHero", 3, 9, 2, 4);
        scenario.addInhabitantArgs("TetVader", 5, 4, 3, 1);
        scenario.addStarMapArgs(3, 4, 0, "hello world");
        scenarioHashMap.put(3, scenario);

        // scenario customize
        scenario = new Scenario();
        scenario.sufaceRowSize = 0;
        scenario.sufaceColSize = 0;
        scenarioHashMap.put(4, scenario);
    }

}