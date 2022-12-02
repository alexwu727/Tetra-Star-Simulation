package main.java.GUI;

import main.java.BackendConsole;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame{

    public MainFrame() {
        JFrame frame = new JFrame ("Tetra Star");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(4, 1));
        JButton scenario1 = new JButton ("scenario 1");
        JButton scenario2 = new JButton ("scenario 2");
        JButton scenario3 = new JButton ("scenario 3");
        JButton scenario4 = new JButton ("scenario 4");
        frame.add (scenario1);
        frame.add (scenario2);
        frame.add (scenario3);
        frame.add (scenario4);

        frame.pack();
        frame.setVisible(true);
        BackendConsole.buildSimulator();

        scenario1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BackendConsole.simulateScenario(0);
                new SimulationWindow("1");
            }
        });
        scenario2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackendConsole.simulateScenario(1);
                new SimulationWindow("2");
            }
        });
        scenario3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackendConsole.simulateScenario(2);
                new SimulationWindow("3");
            }
        });
        scenario4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackendConsole.simulateScenario(3);
                new SimulationWindow("4");
            }
        });
        frame.setSize(400,600);

    }
}