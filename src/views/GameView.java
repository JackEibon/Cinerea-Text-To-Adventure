package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import gamelogic.GameLogic;
import gamelogic.Lexicon;
import gamelogic.TextReader;
import gamelogic.WordColors;

public class GameView extends JPanel {

	public JTextField hiddenInput = new JTextField();
	public TextReader reader;
	public JTextPane narrationPane = new JTextPane();

	public JTextArea minimap = new JTextArea();//placeholder
	public JLabel item1= new JLabel();
	public JLabel item2= new JLabel();
	public JLabel item3= new JLabel();
	public JLabel item4= new JLabel();
	public JLabel item5= new JLabel();
	public JLabel item6= new JLabel();
	public GameCanvas canvas= GameLogic.canvas;
	private StyledDocument narrationDocument; //this is what makes it look smooth
	//and allows for a certain personalization (placeholder)

	private Style defaultStyle;
	private Style actionStyle;
	private Style itemStyle;
	private Style directionStyle;
	private Style modifierStyle;
	private Style characterStyle;
	
	public GameView() {
		setLayout(new BorderLayout(10,10));
		setBorder(new EmptyBorder(10,10,10,10));
		setBackground(Color.BLACK);
		
		ImageIcon icon = new ImageIcon(
				getClass().getResource("/assets/sprites/item/item40.gif")
			            );
			item1.setIcon(icon);
			item2.setIcon(icon);
			item3.setIcon(icon);
			item4.setIcon(icon);
			item5.setIcon(icon);
			item6.setIcon(icon);
		
		add(canvas,BorderLayout.EAST);
		//add(item2,x.);
		//add(item3,x.);
		//add(item4,x.);
		//add(item5,x.);
		//add(item6,x.);

		// Hidden input
		hiddenInput.setOpaque(false);
		hiddenInput.setForeground(new Color(0, 0, 0, 0));
		hiddenInput.setCaretColor(new Color(0, 0, 0, 0));
		hiddenInput.setBorder(null);
		// Parser visual reader
		reader = new TextReader(hiddenInput);
		add(reader, BorderLayout.NORTH);
		reader.setFocusable(false);
		// Narration Pane
		narrationPane.setBackground(Color.BLACK);
		narrationPane.setForeground(Color.WHITE);
		narrationPane.setEditable(false);
		narrationPane.setFocusable(false);
		narrationPane.setBorder(null);
		narrationPane.setFont(new Font("Consolas", Font.BOLD, 18));
		narrationDocument = narrationPane.getStyledDocument();
		// Styles
		defaultStyle = narrationPane.addStyle("default", null);
		StyleConstants.setForeground(defaultStyle, Color.WHITE);
		StyleConstants.setFontFamily(defaultStyle, "Consolas");
		StyleConstants.setFontSize(defaultStyle, 18);

		itemStyle = narrationPane.addStyle("item", null);
		StyleConstants.setForeground(itemStyle, Color.RED);
		directionStyle = narrationPane.addStyle("direction", null);
		StyleConstants.setForeground(directionStyle, Color.GREEN);
		actionStyle = narrationPane.addStyle("action", null);
		StyleConstants.setForeground(actionStyle, Color.YELLOW);
		modifierStyle = narrationPane.addStyle("modifier", null);
		StyleConstants.setForeground(modifierStyle, Color.cyan);
		characterStyle = narrationPane.addStyle("character", null);
		StyleConstants.setForeground(characterStyle, Color.MAGENTA);

		JScrollPane narrationScroll = new JScrollPane(narrationPane);
		narrationScroll.setBorder(null);
		add(narrationScroll, BorderLayout.CENTER);
		// Minimap
		minimap.setBackground(Color.BLACK);
		minimap.setForeground(Color.WHITE);
		minimap.setEditable(false);
		minimap.setFocusable(false);
		minimap.setPreferredSize(new Dimension(220, 200));
		minimap.setFont(new Font("Consolas", Font.BOLD, 16));
		minimap.setBorder(null);
		// add(minimap,BorderLayout.EAST);
		// Repaint while typing
		hiddenInput.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				reader.repaint();
			}
		});

		add(hiddenInput, BorderLayout.SOUTH);
		setFocusable(true);
		SwingUtilities.invokeLater(() -> hiddenInput.requestFocusInWindow());
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener("focusOwner", e -> {
			if (!hiddenInput.hasFocus()) {
				hiddenInput.requestFocusInWindow();
			}
		});
	}

	public void appendDefault(String text) {
		appendStyledText(text + "\n", defaultStyle);
	}

	public void appendStyledText(String text, Style style) {
		try {
			narrationDocument.insertString(narrationDocument.getLength(), text, style);

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void appendColoredText(String text) {
		String[] words = text.split(" ");
		Style still;
		for (String word : words) {
			still = getStyleForWord(word);
			appendStyledText(word + " ", still);
		}
		appendStyledText("\n", defaultStyle);
	}

	private Style getStyleForWord(String word) {
		switch (Lexicon.wordIs(word.toLowerCase())) {
		case "character":
			return characterStyle;
		case "noun":
			return itemStyle;
		case "verb":
			return actionStyle;
		case "modifier":
			return modifierStyle;
		case "direction":
			return directionStyle;
		default:
			return defaultStyle;
		}
	}
}
