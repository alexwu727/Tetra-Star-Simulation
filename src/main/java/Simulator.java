package main.java;

import main.java.Inhabitant.TetRover;

import java.util.HashMap;

public class Simulator {
    static Scenario scenario;
    static int currentScenarioIndex;
    static int helper = 10;
    private static HashMap<Integer, Scenario> scenarioHashMap = new HashMap<Integer, Scenario>();

    public static void start(int scenarioIndex) {
        TFace.clear();
        scenario = scenarioHashMap.get(scenarioIndex);
        currentScenarioIndex = scenarioIndex;
        TFace tFace = TFace.instance();
        tFace.setSurfaceSize(scenario.sufaceRowSize, scenario.sufaceColSize);
        scenario.createInstances();
        TFace.instance().updateDisplayHashMap();
    }

    public static void nextFrame() {
        helper -= 1;
        for (TetRover tetRover : scenario.inhibitantList) {
            tetRover.action();
        }
        if (helper == 0 && currentScenarioIndex == 3) {
            scenario.inhibitantList.get(0).setWalkDirections(1);
            scenario.inhibitantList.get(1).setWalkDirections(3);
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
        scenario.addTetHeroArgs(0, 0, 1, 0);
        scenario.addTetHeroArgs(5, 9, 2, 0);
        scenario.addTetVaderArgs(4, 4, 3, 0);
        scenario.addTetRoverArgs(2, 3, 4, 0);
        scenario.addTetRoverArgs(9, 8, 5, 0);
        scenario.addStarMapArgs(7, 2, 0, "hello world");
        scenario.addStarAtlasArgs(2, 7, 0, new int[] { 1, 2, 3, 4 }, new String[] { "hel", "lo", "wor", "ld" });
        scenarioHashMap.put(0, scenario);

        // scenario 1 - hero enters the mapbase with a map
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addTetHeroArgs(3, 0, 1, 4);
        scenario.addTetVaderArgs(9, 3, 2, 1);
        scenario.addStarMapArgs(3, 3, 0, "hello world");
        scenarioHashMap.put(1, scenario);

        // scenario 2 - hero enters the mapbase without a map
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addTetHeroArgs(3, 0, 1, 4);
        scenario.addTetVaderArgs(6, 4, 2, 1);
        scenario.addStarMapArgs(3, 4, 0, "hello world");
        scenarioHashMap.put(2, scenario);

        // scenario 3 - hero enters the mapbase with a encrypted map (by others)
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addTetHeroArgs(3, 0, 1, 4);
        scenario.addTetHeroArgs(3, 9, 2, 4);
        scenario.addTetVaderArgs(5, 4, 3, 1);
        scenario.addStarMapArgs(3, 4, 0, "hello world");
        scenarioHashMap.put(3, scenario);
    }

}