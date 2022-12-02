package main.java.GUI;

import main.java.BackendConsole;
import main.java.Base.HeroBase;
import main.java.Base.MapBase;
import main.java.Base.River;
import main.java.Base.VaderBase;
import main.java.Inhabitant.TetHero;
import main.java.Inhabitant.TetRover;
import main.java.Inhabitant.TetVader;
import main.java.Locatable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class SimulationWindow {
    JFrame frame;
    int row;
    int col;
    Locatable[][] map;

    ImageIcon river;
    ImageIcon hero;
    ImageIcon heroBase;
    ImageIcon mapBase;
    ImageIcon starMap;
    ImageIcon cloneMap;
    ImageIcon vader;
    ImageIcon vaderBase;
    ImageIcon rover;
    JPanel mapPanel;
    JLabel[][] canvas;
    int stepCount;

    SimulationWindow(String scene) {

        map = BackendConsole.getSurface();
        row = map.length;
        col = map[0].length;

        frame = new JFrame("Simulation Scenario " + scene);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        cloneMap = new ImageIcon(((new ImageIcon("res/clone_map.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        vader = new ImageIcon(((new ImageIcon("res/vader.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        vaderBase = new ImageIcon(((new ImageIcon("res/vader_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        rover = new ImageIcon(((new ImageIcon("res/man1.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));


        nextAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackendConsole.nextFrame();
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

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                canvas[i][j] = new JLabel();


                if (map[i][j] instanceof HeroBase) {
                    JLabel crowded = new JLabel();
                    crowded.setLayout(new BoxLayout(crowded, BoxLayout.X_AXIS));
                    JLabel roommate1 = new JLabel();
                    JLabel roommate2 = new JLabel();
                    JLabel roommate3 = new JLabel();
                    roommate1.setIcon(heroBase);
                    roommate2.setIcon(vader);
                    roommate3.setIcon(starMap);

                    crowded.add(roommate1);
                    crowded.add(roommate3);
                    crowded.add(roommate2);
                    canvas[i][j] = crowded;
//                    canvas[i][j] = new JLabel(heroBase);
                }
                else if (map[i][j] instanceof TetHero) {
                    canvas[i][j] = new JLabel(hero);
                }
                else if (map[i][j] instanceof VaderBase) {
                    canvas[i][j] = new JLabel(vaderBase);
                }
                else if (map[i][j] instanceof TetVader) {
                    canvas[i][j] = new JLabel(vader);
                }
                else if (map[i][j] instanceof MapBase) {
                    canvas[i][j] = new JLabel(mapBase);
                }
                else if (map[i][j] instanceof Map) {
                    canvas[i][j] = new JLabel(starMap);
                }
                else if (map[i][j] instanceof TetRover) {
                    canvas[i][j] = new JLabel(rover);
                }
                else if (map[i][j] instanceof River) {
                    canvas[i][j] = new JLabel(river);
                }
                canvas[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
                mapPanel.add(canvas[i][j]);
            }
        }
        mapPanel.validate();
        mapPanel.repaint();
    }

}



