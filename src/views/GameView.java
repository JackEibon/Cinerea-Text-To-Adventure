package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import gamelogic.TextReader;
public class GameView extends JPanel {

    public JTextField hiddenInput = new JTextField();
    public TextReader reader;
    public JTextArea narrationArea = new JTextArea();
    public JTextArea minimap = new JTextArea();

    public GameView() {

        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));
        setBackground(Color.BLACK);

        /*
         * Hidden input
         */
        hiddenInput.setOpaque(false);
        hiddenInput.setForeground(new Color(0,0,0,0));
        hiddenInput.setCaretColor(new Color(0,0,0,0));
        
        hiddenInput.setBorder(null);

        /*
         * The AI called it Renderer, but i decided reader
         */
        reader = new TextReader(hiddenInput);
        add(reader, BorderLayout.NORTH);
        reader.setFocusable(false);
        /*
         * Description are, Called "narration"
         */
        narrationArea.setBackground(Color.BLACK);
        narrationArea.setForeground(Color.WHITE);
        narrationArea.setEditable(false);//needed
        narrationArea.setLineWrap(true);
        narrationArea.setWrapStyleWord(true);
        narrationArea.setFocusable(false);
        //add(narrationArea,BorderLayout.SOUTH);
        add(new JScrollPane(narrationArea), BorderLayout.CENTER);
        narrationArea.setBorder(null);
        /*
         * Minimap
         */
        minimap.setBackground(Color.BLACK);
        minimap.setForeground(Color.WHITE);
        minimap.setEditable(false);
        minimap.setFocusable(false);
        minimap.setPreferredSize(new Dimension(220,200));
        add(minimap, BorderLayout.EAST);
        //the minimap is mostly a placeholder for now
        /*
         * Repaint while typing
         */
       
    	minimap.setBorder(null);
        hiddenInput.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
           
            	reader.repaint();
            	
            	
            }
        });
        
        add(hiddenInput,BorderLayout.SOUTH);
        setFocusable(true);
        SwingUtilities.invokeLater(() -> hiddenInput.requestFocusInWindow());
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
        .addPropertyChangeListener("focusOwner", e -> {
            if (!hiddenInput.hasFocus()) {
                hiddenInput.requestFocusInWindow();
            }
        });
    }

    public void appendText(String text) {narrationArea.append("\n\n" + text);}
}