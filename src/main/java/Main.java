package main.java;

import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Inhabitant.TetHero;
import main.java.Inhabitant.TetRover;
import main.java.Inhabitant.TetVader;
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
        TFace tFace = TFace.instance();
        tFace.setSurfaceSize(4, 4);
        List<TetRover> InhibitantList = new ArrayList<>();
        StarMap m1 = new StarMap(1, 1, 0);
        m1.setText("hello world");
        TetHero t1 = new TetHero(0, 0, 1);
        TetHero t2 = new TetHero(3, 3, 2);
        TetVader t3 = new TetVader(3, 1, 3);
        // TetHero t3 = new TetHero(1, 1, 3, tFace);
        // TetVader t4 = new TetVader(6, 7, 4, tFace);
        t1.setDisplayID("H");
        t2.setDisplayID("H");
        t3.setDisplayID("V");
        // t4.setDisplayID(4);

        InhibitantList.add(t1);
        InhibitantList.add(t2);
        InhibitantList.add(t3);
        // InhibitantList.add(t4);
        bar("Map");
        tFace.printSurface();

        while (true) {
            bar("Action");
            for (TetRover tetRover : InhibitantList) {
                tetRover.action();
            }
            bar("Map");

            tFace.printSurface();
            wait(1000);
        }
        // TetRover t3 = new TetHero(1, 1);
        // // System.out.println(t1.getRow() + " " + t1.getCol());
        // // let 1 be hero, 2 be vader, 3 be rover, 4 be
        // tFace.addObject(t1);
        // tFace.addObject(t2);
        // tFace.addObject(t3);
        // tFace.addBase(2, 0, 4);
        // tFace.addBase(2, 2, 4);
        // tFace.addBase(2, 4, 4);
        // tFace.addBase(2, 6, 4);
        // tFace.addBase(2, 8, 4);
        // tFace.printSurface();
        // JFrame frame = new JFrame("Test title");

        // frame.setSize(600, 600);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // // JPanel panel = new JPanel();
        // // panel.setBackground(Color.BLUE);
        // frame.setLayout(new GridLayout(10, 10));
        // ImageIcon imageIcon = new ImageIcon("res/hero.png"); // load the image to a
        // imageIcon
        // Image image = imageIcon.getImage(); // transform it
        // Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        // // scale it the smooth way
        // imageIcon = new ImageIcon(newimg);
        // JLabel picLabel = new JLabel(imageIcon);
        // for (int i = 0; i < 10; i++) {
        // for (int j = 0; j < 10; j++) {
        // JPanel panel = new JPanel();
        // panel.setLayout(new FlowLayout());
        // panel.add(picLabel);
        // // JButton btn = new JButton("1");

        // // btn.setBackground(Color.RED);
        // // btn.setForeground(Color.GREEN);
        // // btn.setContentAreaFilled(false);
        // // btn.setOpaque(true);
        // frame.add(panel);
        // }
        // }

        // // frame.add(panel);
        // frame.setVisible(true);
    }

    // for (Map.Entry<int[], Integer> entry : tFace.baseMap.entrySet()) {
    // System.out.println(entry.getKey()[0] + " " + entry.getKey()[1] + " " +
    // entry.getValue());
    // }
    // while (true) {
    // t1.walk();
    // t2.walk();
    // t3.walk();
    // bar();
    // tFace.printSurface();

    // wait(1000);
    // }

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