package main.java;

import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Inhabitant.TetHero;
import main.java.Inhabitant.TetRover;
import main.java.Inhabitant.TetVader;
import main.java.Map.StarAltas;
import main.java.Map.StarMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        BackendConsole.buildSimulator();
        BackendConsole.simulateScenario(2);
        TFace tFace = TFace.instance();
        bar("Map");
        tFace.printSurface();
        while (true) {
            BackendConsole.nextFrame();
            bar("Action");
            System.out.println(BackendConsole.getConsole());
            bar("Map");
            tFace.printSurface();
            // BackendConsole.getSurface().printSurface();
            wait(1000);
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

}