package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import assets.utils.AppFont;
import config.Config;

public class TutorialView extends JPanel {

	private TutorialWindow window;

	private JPanel bigPanel, midPanel, centerPanel;
	private JLabel lblTitle;
	private JTextPane txtInstructions;

	private static final Color LIGHT_BG = new Color(92, 122, 237);
	private static final Color LIGHT_BIG = new Color(17, 53, 189);
	private static final Color LIGHT_MID = new Color(52, 86, 217);

	private static final Color DARK_BG = new Color(22, 30, 70);
	private static final Color DARK_BIG = new Color(8, 12, 38);
	private static final Color DARK_MID = new Color(14, 22, 55);
	
	private static final Color ACTION_COLOR = Color.YELLOW;
	private static final Color CONCEPT_COLOR = Color.GREEN;
	private static final Color ITEM_COLOR = new Color(178, 0, 0);
	private static final Color CHARACTER_COLOR = new Color(128, 0, 128);
	private static final Color MODIFIER_COLOR = Color.CYAN;

	public TutorialView(TutorialWindow window) {
		this.window = window;
		setLayout(new BorderLayout());
		build();
	}

	private void build() {
		boolean isDark = isDarkTheme();

		Color bgColor;
		Color bigBdColor;
		Color midBdColor;
		Color textColor;

		if (isDark) {
			bgColor = DARK_BG;
			bigBdColor = DARK_BIG;
			midBdColor = DARK_MID;
			textColor = Color.WHITE;
		} else {
			bgColor = LIGHT_BG;
			bigBdColor = LIGHT_BIG;
			midBdColor = LIGHT_MID;
			textColor = Color.BLACK;
		}

		bigPanel = new JPanel(new BorderLayout());
		bigPanel.setBackground(bigBdColor);
		bigPanel.setBorder(BorderFactory.createLineBorder(bigBdColor, 20));

		midPanel = new JPanel(new BorderLayout());
		midPanel.setBackground(midBdColor);
		midPanel.setBorder(BorderFactory.createLineBorder(midBdColor, 20));

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(bgColor);
		centerPanel.setBorder(new EmptyBorder(45, 60, 45, 60));

		lblTitle = new JLabel("TUTORIAL");
		lblTitle.setFont(AppFont.title());
		lblTitle.setForeground(textColor);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		txtInstructions = new JTextPane();
		txtInstructions.setEditable(false);
		txtInstructions.setOpaque(false);
		txtInstructions.setFocusable(false);
		txtInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		txtInstructions.getStyledDocument().setParagraphAttributes(0, 1, center, false);
		
		updateInstructionsText(textColor);
		
		centerPanel.add(lblTitle);
		centerPanel.add(Box.createRigidArea(new java.awt.Dimension(0, 30)));
		centerPanel.add(txtInstructions);

		bigPanel.add(midPanel);
		midPanel.add(centerPanel);
		add(bigPanel, BorderLayout.CENTER);
	}

	public void applyThemeColors() {
		boolean isDark = isDarkTheme();

		Color bgColor;
		Color bigBdColor;
		Color midBdColor;
		Color textColor;

		if (isDark) {
			bgColor = DARK_BG;
			bigBdColor = DARK_BIG;
			midBdColor = DARK_MID;
			textColor = Color.WHITE;
		} else {
			bgColor = LIGHT_BG;
			bigBdColor = LIGHT_BIG;
			midBdColor = LIGHT_MID;
			textColor = Color.BLACK;
		}

		bigPanel.setBackground(bigBdColor);
		bigPanel.setBorder(BorderFactory.createLineBorder(bigBdColor, 20));
		midPanel.setBackground(midBdColor);
		midPanel.setBorder(BorderFactory.createLineBorder(midBdColor, 20));
		centerPanel.setBackground(bgColor);

		lblTitle.setForeground(textColor);
		
		updateInstructionsText(textColor);

		revalidate();
		repaint();
	}
	
	private void updateInstructionsText(Color baseColor) { // Instrucciones del tutorial, no agregue todas las palabras porque eibon me dijo que las personas le echaran ganas y las descubrieran
		txtInstructions.setText("");
		Font font = AppFont.normalSecondary();

		append(baseColor, font, "You have awakened, not knowing where you are.\nYour goal: survive for 3 days.\nYour energy depletes as you move, so explore wisely!\n\nType commands to interact with the world. Discover words, but start with these:\n\nActions: ");
		
		append(ACTION_COLOR, font, "look");
		append(baseColor, font, ", ");
		append(ACTION_COLOR, font, "go");
		append(baseColor, font, ", ");
		append(ACTION_COLOR, font, "take");
		
		append(baseColor, font, "\nDirections: ");
		append(CONCEPT_COLOR, font, "north");
		append(baseColor, font, ", ");
		append(CONCEPT_COLOR, font, "around");
		
		append(baseColor, font, "\nItems: ");
		append(ITEM_COLOR, font, "sword");
		append(baseColor, font, ", ");
		append(ITEM_COLOR, font, "map");
		
		append(baseColor, font, "\nModifiers: ");
		append(MODIFIER_COLOR, font, "carefully");
		
		append(baseColor, font, "\n\nExample: '");
		append(ACTION_COLOR, font, "go");
		append(baseColor, font, " ");
		append(CONCEPT_COLOR, font, "north");
		append(baseColor, font, " ");
		append(MODIFIER_COLOR, font, "carefully");
		append(baseColor, font, "'");

		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		txtInstructions.getStyledDocument().setParagraphAttributes(0, txtInstructions.getDocument().getLength(), center, false);
	}

	private void append(Color color, Font font, String text) {
		StyledDocument doc = txtInstructions.getStyledDocument();
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		
		StyleConstants.setForeground(attributes, color);
		StyleConstants.setFontFamily(attributes, font.getFamily());
		StyleConstants.setFontSize(attributes, font.getSize());

		try {
			doc.insertString(doc.getLength(), text, attributes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isDarkTheme() {
		return Config.get("ui.theme", "light").equalsIgnoreCase("dark");
	}

	public TutorialWindow getWindow() {
		return window;
	}
}