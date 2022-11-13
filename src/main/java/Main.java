package main.java;

import main.java.Inhabitant.TetHero;
import main.java.Inhabitant.TetRover;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // TFace tFace = new TFace(10, 10);
        // TetRover t1 = new TetRover(3, 4, 1);
        // TetRover t2 = new TetHero(5, 8, 2);
        // TetHero t3 = new TetHero(1, 1, 3);
        // // TetRover t3 = new TetHero(1, 1);
        // t1.setTFace(tFace);
        // t2.setTFace(tFace);
        // t3.setTFace(tFace);

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
    	
        JFrame frame = new JFrame("Test title");

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JPanel panel = new JPanel();
        // panel.setBackground(Color.BLUE);
        frame.setLayout(new GridLayout(10, 10));
        ImageIcon imageIcon = new ImageIcon("res/hero.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        JLabel picLabel = new JLabel(imageIcon);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(picLabel);
                // JButton btn = new JButton("1");

                // btn.setBackground(Color.RED);
                // btn.setForeground(Color.GREEN);
                // btn.setContentAreaFilled(false);
                // btn.setOpaque(true);
                frame.add(panel);
            }
        }

        // frame.add(panel);
        frame.setVisible(true);
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

    public static void bar() {
        System.out.println("--------------------");
    }

}