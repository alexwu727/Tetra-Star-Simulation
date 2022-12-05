package main.java.GUI;

import main.java.BackendConsole;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

interface Command {
    void openSimulator();
}
class DefaultCommand implements Command {
    private int scene;
    private String title;
    public DefaultCommand(int scene, String title) {
        this.scene = scene;
        this.title = title;
    }
    public void openSimulator() {
        BackendConsole.simulateScenario(scene, null);
        new SimulationWindow(title);
    }
}
class CustomizeCommand implements Command {
    private int[] args;
    public CustomizeCommand(int[] args) {
        this.args = args;
    }
    public void openSimulator() {
        BackendConsole.simulateScenario(5, args);
        new SimulationWindow("Customize");
    }
}
public class MainFrame{

    public MainFrame() {
        JFrame frame = new JFrame ("Tetra Star");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(5, 1));
        JButton scenario1 = new JButton ("Scenario 1");
        JButton scenario2 = new JButton ("Scenario 2");
        JButton scenario3 = new JButton ("Scenario 3");
        JButton scenario4 = new JButton ("Scenario 4");

        JPanel customize = new JPanel(new GridLayout(2,1));
        JPanel settingBoard = new JPanel(new GridLayout(4, 4));
        JTextField heroNumInput = new JTextField("2",5);
        JTextField roverNumInput = new JTextField("1",5);
        JTextField mapNumInput = new JTextField("1",5);
        JTextField atlasNumInput = new JTextField("0",5);
        JTextField rowSizeInput = new JTextField("10",5);
        JTextField colSizeInput = new JTextField("10",5);
        JLabel errorMsg = new JLabel();
        errorMsg.setForeground(Color.red);
        JButton customizeScenario = new JButton("customize you scenario");

        settingBoard.add(new JLabel("Enter hero num(2-5): "));
        settingBoard.add(heroNumInput);
        settingBoard.add(new JLabel("Enter rover num(0-5): "));
        settingBoard.add(roverNumInput);
        settingBoard.add(new JLabel("Enter starMap num(1-5): "));
        settingBoard.add(mapNumInput);
        settingBoard.add(new JLabel("Enter starAtlas num(0-3): "));
        settingBoard.add(atlasNumInput);
        settingBoard.add(new JLabel("Enter row size(5-20): "));
        settingBoard.add(rowSizeInput);
        settingBoard.add(new JLabel("Enter col size(5-20): "));
        settingBoard.add(colSizeInput);
        settingBoard.add(errorMsg);

        customize.add(settingBoard);
        customize.add(customizeScenario);

        frame.add (scenario1);
        frame.add (scenario2);
        frame.add (scenario3);
        frame.add (scenario4);
        frame.add(customize);

        frame.pack();
        frame.setVisible(true);
        BackendConsole.buildSimulator();
        Command[] command = new Command[5];

        scenario1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command[0] = new DefaultCommand(0, "1");
                command[0].openSimulator();
            }
        });
        scenario2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command[1] = new DefaultCommand(1, "2");
                command[1].openSimulator();
            }
        });
        scenario3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command[2] = new DefaultCommand(2, "3");
                command[2].openSimulator();
            }
        });
        scenario4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command[3] = new DefaultCommand(3, "4");
                command[3].openSimulator();
            }
        });
        customizeScenario.addActionListener(new ActionListener() {
            public boolean isParsable(String input) {
                try {
                    Integer.parseInt(input);
                    return true;
                } catch (final NumberFormatException e) {
                    return false;
                }
            }
            @Override
            public void actionPerformed(ActionEvent e) {

                if( isParsable(heroNumInput.getText()) && isParsable(roverNumInput.getText()) && isParsable(mapNumInput.getText()) && isParsable(atlasNumInput.getText()) && isParsable(rowSizeInput.getText()) && isParsable(colSizeInput.getText())) {
                    int heroNum = Integer.parseInt(heroNumInput.getText());
                    int roverNum = Integer.parseInt(roverNumInput.getText());
                    int mapNum = Integer.parseInt(mapNumInput.getText());
                    int atlasNum = Integer.parseInt(atlasNumInput.getText());
                    int rowSize = Integer.parseInt(rowSizeInput.getText());
                    int colSize = Integer.parseInt(colSizeInput.getText());

                    if (2 <= heroNum && heroNum <= 5 && 0 <= roverNum && roverNum <= 5 && 1 <= mapNum && mapNum <= 5 && 0 <= atlasNum && atlasNum <= 3 && 5 <= rowSize && rowSize <= 20 && 5 <= colSize && colSize <= 20) {
                        errorMsg.setText("");
                        int[] args = {heroNum, roverNum, mapNum, atlasNum, rowSize, colSize};
                        command[4] = new CustomizeCommand(args);
                        command[4].openSimulator();
                    } else {
                        errorMsg.setText("Input out of range");
                    }
                }
                else {
                    errorMsg.setText("Input not integer");
                }


            }
        });

        frame.setSize(700,700);

    }
}