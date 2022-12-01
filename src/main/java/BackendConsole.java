package main.java;

public class BackendConsole {
    private static String console;

    public static void buildSimulator() {
        Simulator.createScenarios();
    }

    public static void simulateScenario(int index) {

        Simulator.start(index);
    }

    public static Locatable[][] getSurface() {
        return TFace.instance().Surface;
    }

    public static void AddConsole(String text) {
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
