package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

import gamelogic.TextReader;

public class GameView extends JPanel {

	public JTextField hiddenInput = new JTextField();
	public TextReader reader;
	public JTextPane narrationPane = new JTextPane();
	public JTextArea minimap = new JTextArea();
	private StyledDocument narrationDocument; //this is what makes it look smooth
	//and allows for a certain personalization (placeholder)
	private Style defaultStyle;
	/*
	private Style systemStyle;
	private Style itemStyle;
	private Style conceptStyle;
	private Style combatStyle;
	*/
	public GameView() {

		setLayout(new BorderLayout(10,10));
		setBorder(new EmptyBorder(10,10,10,10));
		setBackground(Color.BLACK);

		//Hidden input
		hiddenInput.setOpaque(false);
		hiddenInput.setForeground(new Color(0,0,0,0));
		hiddenInput.setCaretColor(new Color(0,0,0,0));
		hiddenInput.setBorder(null);
		//Parser visual reader
		reader = new TextReader(hiddenInput);
		add(reader,BorderLayout.NORTH);
		reader.setFocusable(false);
		// Narration Pane
		narrationPane.setBackground(Color.BLACK);
		narrationPane.setForeground(Color.WHITE);
		narrationPane.setEditable(false);
		narrationPane.setFocusable(false);
		narrationPane.setBorder(null);
		narrationPane.setFont(new Font("Consolas",Font.BOLD,18));
		narrationDocument = narrationPane.getStyledDocument();
		//Styles
		defaultStyle = narrationPane.addStyle("default",null);
		StyleConstants.setForeground(defaultStyle,Color.WHITE);
		StyleConstants.setFontFamily(defaultStyle,"Consolas");
		StyleConstants.setFontSize(defaultStyle,18);
		/*
		systemStyle = narrationPane.addStyle("system",null);
		StyleConstants.setForeground(systemStyle,Color.GRAY);
		StyleConstants.setItalic(systemStyle,true);
		StyleConstants.setFontFamily(systemStyle,"Consolas");
		StyleConstants.setFontSize(systemStyle,18);

		itemStyle = narrationPane.addStyle("item",null);
		StyleConstants.setForeground(itemStyle,Color.RED);
		StyleConstants.setBold(itemStyle,true);
		StyleConstants.setFontFamily(itemStyle,"Consolas");
		StyleConstants.setFontSize(itemStyle,18);

		conceptStyle = narrationPane.addStyle("concept",null);
		StyleConstants.setForeground(conceptStyle,Color.GREEN);
		StyleConstants.setFontFamily(conceptStyle,"Consolas");
		StyleConstants.setFontSize(conceptStyle,18);

		combatStyle = narrationPane.addStyle("combat",null);
		StyleConstants.setForeground(combatStyle,Color.ORANGE);
		StyleConstants.setBold(combatStyle,true);
		StyleConstants.setFontFamily(combatStyle,"Consolas");
		StyleConstants.setFontSize(combatStyle,18);
		*/
		JScrollPane narrationScroll = new JScrollPane(narrationPane);
		narrationScroll.setBorder(null);
		add(narrationScroll,BorderLayout.CENTER);
		//Minimap
		minimap.setBackground(Color.BLACK);
		minimap.setForeground(Color.WHITE);
		minimap.setEditable(false);
		minimap.setFocusable(false);
		minimap.setPreferredSize(new Dimension(220,200));
		minimap.setFont(new Font("Consolas",Font.BOLD,16));
		minimap.setBorder(null);
		add(minimap,BorderLayout.EAST);
		//Repaint while typing
		hiddenInput.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				reader.repaint();
			}
		});

		add(hiddenInput,BorderLayout.SOUTH);
		setFocusable(true);
		SwingUtilities.invokeLater(() -> hiddenInput.requestFocusInWindow());
		KeyboardFocusManager
		.getCurrentKeyboardFocusManager()
		.addPropertyChangeListener("focusOwner",e -> {
			if(!hiddenInput.hasFocus()) {
				hiddenInput.requestFocusInWindow();
			}
		});
	}

	public void appendDefault(String text) 
	{
		
		appendStyledText(text + "\n",defaultStyle);}

	/*
	public void appendSystem(String text) {appendStyledText(text + "\n",systemStyle);}
	public void appendCombat(String text) {appendStyledText(text + "\n",combatStyle);}
	public void appendItem(String text) {appendStyledText(text + "\n",itemStyle);}
	public void appendConcept(String text) {appendStyledText(text + "\n",conceptStyle);}
	*/
	public void appendStyledText(String text,Style style) {
		try {
			narrationDocument.insertString(
					narrationDocument.getLength(),
					text,
					style
			);
			
		} catch(BadLocationException e) {
			e.printStackTrace();
		}
	}
}
