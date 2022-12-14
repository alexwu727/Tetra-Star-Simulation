package main.java.GUI;

import main.java.BackendConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SimulationWindow {
    JFrame frame;
    int row;
    int col;
    HashMap<String, List<String>> displayHashMap;

    ImageIcon river;
    ImageIcon hero;
    ImageIcon heroBase;
    ImageIcon mapBase;
    ImageIcon starMap;
    ImageIcon starAtlas;
    ImageIcon cloneMap;
    ImageIcon vader;
    ImageIcon vaderBase;
    JPanel mapPanel;
    JLabel[][] canvas;
    ArrayList<ImageIcon> roverList;
    int stepCount;

    SimulationWindow(String scene) {

        displayHashMap = BackendConsole.getDisplayHashMap();
        row = BackendConsole.getRowSize();
        col = BackendConsole.getColSize();

        frame = new JFrame("Simulation Scenario " + scene);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        mapPanel = new JPanel(new GridLayout(row, col, -1, -1));
        mapPanel.setBackground(Color.gray);
        mapPanel.setPreferredSize(new Dimension(500, 500));

        JPanel buttonPanel = new JPanel();
        JButton nextAction = new JButton("Next Action");
        buttonPanel.add(nextAction);

        JPanel printPanel = new JPanel();
        JTextArea output = new JTextArea("", 12, 60);
        JScrollPane scroll = new JScrollPane(output);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        printPanel.add(scroll);
        printPanel.setPreferredSize(new Dimension(500, 300));

        container.add(mapPanel);
        container.add(buttonPanel);
        container.add(printPanel);

        frame.getContentPane().add(container);
        frame.pack();

        river = new ImageIcon(((new ImageIcon("res/river.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        hero = new ImageIcon(((new ImageIcon("res/hero.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        heroBase = new ImageIcon(((new ImageIcon("res/hero_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        mapBase = new ImageIcon(((new ImageIcon("res/map_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        starMap = new ImageIcon(((new ImageIcon("res/star_map.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        starAtlas = new ImageIcon(((new ImageIcon("res/star_altas.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        cloneMap = new ImageIcon(((new ImageIcon("res/clone_map.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        vader = new ImageIcon(((new ImageIcon("res/vader.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        vaderBase = new ImageIcon(((new ImageIcon("res/vader_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

        roverList = new ArrayList<>();
        roverList.add(new ImageIcon(((new ImageIcon("res/man1.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        roverList.add(new ImageIcon(((new ImageIcon("res/woman1.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        roverList.add(new ImageIcon(((new ImageIcon("res/man2.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        roverList.add(new ImageIcon(((new ImageIcon("res/woman2.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        roverList.add(new ImageIcon(((new ImageIcon("res/man3.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));

        nextAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackendConsole.nextFrame();
                displayHashMap = BackendConsole.getDisplayHashMap();
                draw(mapPanel);
                String res = BackendConsole.getConsole();
                stepCount += 1;
                output.append("========================= Step "+stepCount+" =========================\n" + res + "======================================================\n\n");
            }
        });
        mapPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        canvas = new JLabel[row][col];
        draw(mapPanel);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    private void draw(JPanel mapPanel) {

        mapPanel.removeAll();
        int roverCount = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                canvas[i][j] = new JLabel();

                String searchKey = i + "," + j;
                if (displayHashMap.containsKey(searchKey)) {
                    List<String> residents = displayHashMap.get(searchKey);
                    JLabel crowded = new JLabel();
                    crowded.setLayout(new BoxLayout(crowded, BoxLayout.X_AXIS));
                    for (String inhabitant : residents) {
                        switch (inhabitant) {
                            case "TetHero":
                                crowded.add(new JLabel(hero));
                                break;
                            case "HeroBase":
                                crowded.add(new JLabel(heroBase));
                                break;
                            case "TetVader":
                                crowded.add(new JLabel(vader));
                                break;
                            case "VaderBase":
                                crowded.add(new JLabel(vaderBase));
                                break;
                            case "StarMap":
                                crowded.add(new JLabel(starMap));
                                break;
                            case "StarAtlas":
                                crowded.add(new JLabel(starAtlas));
                                break;
                            case "MapBase":
                                crowded.add(new JLabel(mapBase));
                                break;
                            case "CloneMap":
                                crowded.add(new JLabel(cloneMap));
                                break;
                            case "River":
                                crowded.add(new JLabel(river));
                                break;
                            case "TetRover":
                                crowded.add(new JLabel(roverList.get(roverCount%5)));
                                roverCount++;
                                break;
                        }
                    }
                    canvas[i][j] = crowded;
                }
                else {
                    canvas[i][j] = new JLabel("");
                }
                canvas[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
                mapPanel.add(canvas[i][j]);
            }
        }
        mapPanel.validate();
        mapPanel.repaint();
    }

}



