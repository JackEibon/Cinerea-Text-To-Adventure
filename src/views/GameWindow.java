package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class GameWindow extends JFrame {
    public JTextField commandInput;
    public JTextArea narrationArea;
    public JTextArea minimapArea;

    public GameWindow() {
    	System.out.print("here");
        setTitle("Cinerea - Adventure");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(10,10,10,10));
        root.setBackground(Color.BLACK);

        commandInput = new JTextField();
        commandInput.setPreferredSize(new Dimension(100, 40));

        narrationArea = new JTextArea();
        narrationArea.setEditable(false);
        narrationArea.setLineWrap(true);
        narrationArea.setWrapStyleWord(true);
        narrationArea.setText("Welcome to Cinerea. Type commands like: go forest");

        minimapArea = new JTextArea();
        minimapArea.setEditable(false);
        minimapArea.setPreferredSize(new Dimension(220, 200));
        minimapArea.setText("[ MAP ]\n\n     Mountains\n         ^\nForest <- YOU -> Lake\n         v\n       Cave");

        root.add(commandInput, BorderLayout.NORTH);
        root.add(new JScrollPane(narrationArea), BorderLayout.CENTER);
        root.add(minimapArea, BorderLayout.EAST);
        add(root);
     

//        SwingUtilities.invokeLater(() -> commandInput.requestFocusInWindow());
    }

    public void appendText(String text) {
        narrationArea.append("\n\n" + text);
    }
}
