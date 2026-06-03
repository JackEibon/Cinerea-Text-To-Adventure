package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.utils.AppFont;
import config.Config;

public class TutorialView extends JPanel {

	private TutorialWindow window;

	private JPanel bigPanel, midPanel, centerPanel;
	private JLabel lblTitle;

	private static final Color LIGHT_BG = new Color(92, 122, 237);
	private static final Color LIGHT_BIG = new Color(17, 53, 189);
	private static final Color LIGHT_MID = new Color(52, 86, 217);

	private static final Color DARK_BG = new Color(22, 30, 70);
	private static final Color DARK_BIG = new Color(8, 12, 38);
	private static final Color DARK_MID = new Color(14, 22, 55);

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

		revalidate();
		repaint();
	}

	private boolean isDarkTheme() {
		return Config.get("ui.theme", "light").equalsIgnoreCase("dark");
	}

	public TutorialWindow getWindow() {
		return window;
	}
}