package main.java.GUI;


import main.java.TFace;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class MainFrame{
    JFrame frame;
//    MainFrame(TFace surface){
    // HashMap<String, Object>
    MainFrame(int[][] map){

//        int row = surface.getRowSize();
//        int col = surface.getColSize();
        int row = map.length;
        int col = map[0].length;

        frame = new JFrame("Tetra Star Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel mapPanel = new JPanel(new GridLayout(row, col, -1, -1));
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
        Random rand = new Random();

        nextAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.append(rand.nextInt(1000)+"\n");

            }
        });

        ImageIcon river = new ImageIcon(((new ImageIcon("res/river.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        ImageIcon hero = new ImageIcon(((new ImageIcon("res/hero.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        ImageIcon heroBase = new ImageIcon(((new ImageIcon("res/hero_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        ImageIcon mapBase = new ImageIcon(((new ImageIcon("res/map_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        ImageIcon starMap = new ImageIcon(((new ImageIcon("res/star_map.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        ImageIcon vader = new ImageIcon(((new ImageIcon("res/vader.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        ImageIcon vaderBase = new ImageIcon(((new ImageIcon("res/vader_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        ImageIcon rover = new ImageIcon(((new ImageIcon("res/man1.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

        mapPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        JLabel initMap[][] = new JLabel[row][col];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++) {
                initMap[i][j] = new JLabel();
                switch (map[i][j]) {
                    case 11:
                        initMap[i][j] = new JLabel(hero);
                        break;
                    case 22:
                        initMap[i][j] = new JLabel(vaderBase);
                        break;
                    case 33:
                        initMap[i][j] = new JLabel(mapBase);
                        break;
                    case -1:
                        initMap[i][j] = new JLabel(river);
                        break;
                    default:
                        initMap[i][j] = new JLabel("");
                        break;
                }
                initMap[i][j].setBorder(BorderFactory.createLineBorder(Color.white));
                mapPanel.add(initMap[i][j]);


////                Locatable obj = surface.Surface[i][j];
////                if (obj instanceof HeroBase) {
////                JLabel label = new JLabel(" ");
//                if(map[i][j] == 11){
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/hero_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    heroBase.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(heroBase);
//                }
////                else if (obj instanceof TetHero) {
//                else if (map[i][j] == 1) {
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/hero.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    hero.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(hero);
//                }
//                else if (map[i][j] == 111) {
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/hero.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    hero.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(hero);
//                }
////                else if (obj instanceof VaderBase) {
//                else if (map[i][j] == 22) {
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/vader_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    vaderBase.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(vaderBase);
//                }
////                else if (obj instanceof TetVader) {
//                else if (map[i][j] == 2) {
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/vader.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    vader.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(vader);
//                }
////                else if (obj instanceof MapBase) {
//                else if (map[i][j] == 33) {
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/map_base.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    mapBase.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(mapBase);
//                }
////                else if (obj instanceof Map) {
//                else if (map[i][j] == 3) {
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/star_map.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    starMap.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(starMap);
//                }
////                else if (obj instanceof River) {
//                else if (map[i][j] == -1) {
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/river.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    river.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(river);
//                }
////                else if (obj instanceof TetRover) {
//                else if (map[i][j] == 4) {
//                    final JLabel label = new JLabel(new ImageIcon(((new ImageIcon("res/man1.png")).getImage()).getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
////                    rover.setBorder(BorderFactory.createLineBorder(Color.white));
////                    mapPanel.add(rover);
//                }
//                else{
//                    final JLabel label = new JLabel(" ");
//                    label.setBorder(BorderFactory.createLineBorder(Color.white));
//                    mapPanel.add(label);
//                }
//
            }
        }

        frame.setSize(800,800);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        int[][] map = {{0,0,0,33,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,3,0,0},
                {111,0,0,4,0,0,0,0,0,11},
                {0,2,0,0,0,4,0,3,-1,0},
                {0,0,0,0,0,0,0,-1,22,-1},
                {0,0,0,33,0,0,0,0,-1,0},
                {0,0,0,0,0,1,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,0,0,3,0,0,0,0,1,0},
                {11,0,0,0,0,0,0,0,11,0}};
        new MainFrame(map);


    }
}