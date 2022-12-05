package main.java;

import main.java.Inhabitant.Inhabitant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Simulator {
    static Scenario scenario;
    static int currentScenarioIndex;
    static int helper;
    private static HashMap<Integer, Scenario> scenarioHashMap = new HashMap<Integer, Scenario>();

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

    public static void startWithArgs(int[] scenarioArgs) {
        TFace.clear();
        scenario = scenarioHashMap.get(4);
        scenario.resetOldInhibitants();
        scenario.sufaceRowSize = scenarioArgs[4];
        scenario.sufaceColSize = scenarioArgs[5];
        Random rand = new Random();

        int[] heroLoc = rand.ints(1, 2 * scenarioArgs[4] + 2 * scenarioArgs[5] - 4).distinct().limit(scenarioArgs[0])
                .toArray();
        List<String> occupied = new ArrayList<>();
        for (int i = 0; i < scenarioArgs[0]; i++) {
            int row, col;
            if (0 < heroLoc[i] && heroLoc[i] <= scenarioArgs[4]) {
                row = 0;
                col = heroLoc[i] - 1;
            } else if (scenarioArgs[4] < heroLoc[i] && heroLoc[i] < scenarioArgs[4] + scenarioArgs[5]) {
                row = heroLoc[i] - scenarioArgs[4];
                col = scenarioArgs[4] - 1;
            } else if (scenarioArgs[4] + scenarioArgs[5] <= heroLoc[i]
                    && heroLoc[i] < 2 * scenarioArgs[4] + scenarioArgs[5] - 2) {
                row = scenarioArgs[5] - 1;
                col = 2 * scenarioArgs[4] + scenarioArgs[5] - 2 - heroLoc[i];
            } else {
                row = 2 * (scenarioArgs[4] + scenarioArgs[5]) - 3 - heroLoc[i];
                col = 0;
            }
            scenario.addInhabitantArgs("TetHero", row, col, i, 0);
            occupied.add(row + "," + col);
        }
        int row = rand.nextInt(scenarioArgs[4]), col = rand.nextInt(scenarioArgs[5]);
        while (occupied.contains(row + "," + col)) {
            row = rand.nextInt(scenarioArgs[4]);
            col = rand.nextInt(scenarioArgs[5]);
        }
        scenario.addInhabitantArgs("TetVader", row, col, 0, 0);
        occupied.add(row + "," + col);

        for (int i = 0; i < scenarioArgs[1]; i++) {
            while (occupied.contains(row + "," + col)) {
                row = rand.nextInt(scenarioArgs[4]);
                col = rand.nextInt(scenarioArgs[5]);
            }
            scenario.addInhabitantArgs("TetRover", row, col, 4, 0);
            occupied.add(row + "," + col);
        }
        for (int i = 0; i < scenarioArgs[2]; i++) {
            while (occupied.contains(row + "," + col)) {
                row = rand.nextInt(scenarioArgs[4]);
                col = rand.nextInt(scenarioArgs[5]);
            }
            scenario.addStarMapArgs(row, col, i, "hello world" + i);
            occupied.add(row + "," + col);
        }
        for (int i = 0; i < scenarioArgs[3]; i++) {
            while (occupied.contains(row + "," + col)) {
                row = rand.nextInt(scenarioArgs[4]);
                col = rand.nextInt(scenarioArgs[5]);
            }
            scenario.addStarAtlasArgs(row, col, i, new int[] { 1, 2, 3, 4 },
                    new String[] { "hel" + i, "lo" + i, "wor" + i, "ld" + i });
            occupied.add(row + "," + col);
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