package main.java;

import main.java.Inhabitant.TetRover;

import java.util.HashMap;

public class Simulator {
    static Scenario scenario;
    private static HashMap<Integer, Scenario> scenarioHashMap = new HashMap<Integer, Scenario>();

    public static void start(int scenarioIndex) {
        TFace.clear();
        scenario = scenarioHashMap.get(scenarioIndex);
        TFace tFace = TFace.instance();
        tFace.setSurfaceSize(scenario.sufaceRowSize, scenario.sufaceColSize);
        scenario.createInstances();
    }

    public static void nextFrame() {
        for (TetRover tetRover : scenario.inhibitantList) {
            tetRover.action();
        }
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
        scenario.addTetHeroArgs(3, 3, 2, 0);
        scenario.addTetVaderArgs(3, 1, 3, 0);
        scenario.addStarMapArgs(1, 1, 0, "hello world");
        scenario.addStarAltasArgs(1, 2, 0, new int[] { 1, 2, 3, 4 }, new String[] { "hel", "lo", "wor", "ld" });
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

        // scenario 3 - hero enters the mapbase with a encrypted map (by him)
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addTetHeroArgs(3, 0, 1, 4);
        scenario.addTetVaderArgs(6, 4, 2, 1);
        scenario.addStarMapArgs(3, 4, 0, "hello world");
        scenarioHashMap.put(3, scenario);

        // scenario 4 - hero enters the mapbase with a encrypted map (by others)
        scenario = new Scenario();
        scenario.sufaceRowSize = 10;
        scenario.sufaceColSize = 10;
        scenario.addTetHeroArgs(3, 0, 1, 4);
        scenario.addTetVaderArgs(6, 4, 2, 1);
        scenario.addStarMapArgs(3, 4, 0, "hello world");
        scenarioHashMap.put(3, scenario);
    }

}