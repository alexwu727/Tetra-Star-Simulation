package main.java;

import java.util.HashMap;
import java.util.List;

public class BackendConsole {
    private static String console;

    public static void buildSimulator() {
        Simulator.createScenarios();
    }

    public static void simulateScenario(int index, int[] args) {
        console = "";
        if (index == 5) { Simulator.startWithArgs(args);}
        else {Simulator.start(index);}

    }

    public static int getRowSize() {
        return TFace.instance().getRowSize();
    }

    public static int getColSize() {
        return TFace.instance().getColSize();
    }

    public static HashMap<String, List<String>> getDisplayHashMap() {
        return TFace.instance().displayHashMap;
    }

    public static void addConsole(String text) {
        console += text + "\n";
    }

    public static String getConsole() {
        return console;
    }

    public static void nextFrame() {
        console = "";
        Simulator.nextFrame();
    }
}
