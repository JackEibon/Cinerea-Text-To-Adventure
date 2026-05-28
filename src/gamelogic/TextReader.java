package gamelogic;

import javax.swing.*;
import java.awt.*;


public class TextReader extends JComponent {

    private JTextField input;

    public TextReader(JTextField input) {
        this.input = input;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(100,40));
        setFocusable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setFont(new Font("Consolas", Font.BOLD, 18));
        String text = input.getText();
        String[] words = text.split("\\s+");
        int x = 10;
        int y = 25;
        String lastVerb = null;
        int coloredCount = countColored(words);
        for(String word : words) {
            Color base = WordColors.getColorForWord(word,false);

            boolean isTarget = false;
            if(Lexicon.isNoun(word) || Lexicon.isCharacter(word)) {
                if(coloredCount == 1) {
                    isTarget = true;
                }
                if(lastVerb != null) {
                    isTarget = true;
                }
            }
            Color finalColor =       WordColors.getColorForWord(word,isTarget);
            g.setColor(finalColor);
            g.drawString(word,x,y);
            if(Lexicon.isVerb(word)) {lastVerb = word;}
            x += g.getFontMetrics().stringWidth(word + " ");
        }
    }

    private int countColored(String[] words) {

        int count = 0;

        for(String w : words) {

            if(!Lexicon.wordIs(w).equals("non")) {
                count++;
            }
        }
        return count;
    }
}

/*OLD TEXT READER*/
/*
 * package gamecontentlogic;
/*
 * Author: Eibon
 * added in class of 24/2/2026 at 0944 from New Cinerea Netbeans Project
 * Resume: This code will read commands and new text and pass it to the description box
 * where the story is narrated
 * it is meant to be fixed for this new project
 * status: useless
 * 


import javax.swing.*;
import java.awt.*;

public class TextReader extends JComponent {
    private String text = "";
    private String lastVerb = null;

    public TextReader() {
        setFont(new Font("Arial", Font.BOLD, 16));
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFocusable(true);
    }

    // --- Public methods for GameWindow to call ---
    public void appendChar(char c) {
        text += c;
        repaint();
    }

    public void backspace() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
            repaint();
        }
    }

    public void submit() {
        if (!text.isEmpty()) {
            //GameWindow.logPanel.addMessage("> " + text); -Quit Commenting when GameWindowAdded & Fixed
            // Optionally: forward to parser here
        }
        text = "";
        lastVerb = null;
        repaint();
    }

    // --- Painting with Lexicon coloring ---
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        int x = 10;
        int y = 20;

        String[] words = text.split("\\s+");
        lastVerb = null;
        int coloredCount = countColored(words);

        for (String word : words) {
            Color base = getBaseColor(word);
            boolean isColored = base != Color.WHITE;
            boolean isTarget = false;

            if (isColored) {
                if (coloredCount == 1 && isNoun(word)) {
                    isTarget = true;
                } else if (lastVerb != null && isNoun(word)) {
                    isTarget = true;
                }

                Color finalColor = isTarget ? darken(base) : base;
                g.setColor(finalColor);
                g.drawString(word, x, y);

                if (isVerb(word)) {
                    lastVerb = word.toLowerCase();
                }
            } else {
                g.setColor(Color.WHITE);
                g.drawString(word, x, y);
            }

            x += g.getFontMetrics().stringWidth(word + " ");
        }
    }

    private int countColored(String[] words) {
        int count = 0;
        for (String w : words) {
            if (getBaseColor(w) != Color.WHITE) count++;
        }
        return count;
    }

    private boolean isVerb(String word) {
        return Lexicon.verbs.contains(word.toLowerCase());
    }

    private boolean isNoun(String word) {
        return Lexicon.items.contains(word.toLowerCase())
            || Lexicon.concepts.contains(word.toLowerCase())
            || Lexicon.characters.contains(word.toLowerCase());
    }

    private Color getBaseColor(String word) {
        word = word.toLowerCase();
        if (Lexicon.verbs.contains(word)) return Color.YELLOW;
        if (Lexicon.concepts.contains(word)) return Color.GREEN;
        if (Lexicon.items.contains(word)) return Color.RED;
        if (Lexicon.characters.contains(word)) return new Color(228, 100, 228);
        if (Lexicon.modifiers.contains(word)) return Color.CYAN;
        return Color.WHITE;
    }

    private Color darken(Color c) {
        return new Color(
            (int)(c.getRed() * 0.6),
            (int)(c.getGreen() * 0.6),
            (int)(c.getBlue() * 0.6)
        );
    }
}

 * 
 * */
 