package main.java;

import main.java.GUI.MainFrame;

public class Main {
    public static void main(String[] args) {
        new MainFrame();
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

}