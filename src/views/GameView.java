package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameView extends JPanel {

    public JTextField commandInput;
    public JTextArea narrationArea;
    public JTextArea minimapArea;

    public GameView() {

        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(Color.BLACK);

        commandInput = new JTextField();
        commandInput.setPreferredSize(new Dimension(100,40));

        narrationArea = new JTextArea();
        narrationArea.setEditable(false);
        narrationArea.setLineWrap(true);
        narrationArea.setWrapStyleWord(true);

        minimapArea = new JTextArea();
        minimapArea.setEditable(false);
        minimapArea.setPreferredSize(new Dimension(220,200));

        add(commandInput, BorderLayout.NORTH);
        add(new JScrollPane(narrationArea), BorderLayout.CENTER);
        add(minimapArea, BorderLayout.EAST);
    }

    public void appendText(String text) {
        narrationArea.append("\n\n" + text);
    }
}