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

public class SettingsView extends JPanel {

	private SettingsWindow window;

	private JPanel bigPanel, midPanel, centerPanel;
	private JLabel lblTitle, lblCurrentTheme;
	private JButton btnLight, btnDark;

	private static final Color LIGHT_BG = new Color(92, 122, 237);
	private static final Color LIGHT_BIG = new Color(17, 53, 189);
	private static final Color LIGHT_MID = new Color(52, 86, 217);

	private static final Color DARK_BG = new Color(22, 30, 70);
	private static final Color DARK_BIG = new Color(8, 12, 38);
	private static final Color DARK_MID = new Color(14, 22, 55);

	private static final Color BTN_ACTIVE_BG = new Color(17, 53, 189);
	private static final Color BTN_ACTIVE_FG = Color.WHITE;
	private static final Color BTN_IDLE_BG = Color.WHITE;
	private static final Color BTN_IDLE_FG = Color.BLACK;

	public SettingsView(SettingsWindow window) {
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

		lblTitle = new JLabel("SETTINGS");
		lblTitle.setFont(AppFont.title());
		lblTitle.setForeground(textColor);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(lblTitle);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 12)));

		String themeLabel;
		if (isDark) {
			themeLabel = "Current theme: Dark";
		} else {
			themeLabel = "Current theme: Light";
		}
		lblCurrentTheme = new JLabel(themeLabel);
		lblCurrentTheme.setFont(AppFont.normalSecondary());
		lblCurrentTheme.setForeground(textColor);
		lblCurrentTheme.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(lblCurrentTheme);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 55)));

		btnLight = createThemeButton("Light Mode");
		applyButtonState(btnLight, !isDark);
		addHoverBehavior(btnLight, true);
		centerPanel.add(btnLight);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

		btnDark = createThemeButton("Dark Mode");
		applyButtonState(btnDark, isDark);
		addHoverBehavior(btnDark, false);
		centerPanel.add(btnDark);

		bigPanel.add(midPanel);
		midPanel.add(centerPanel);
		add(bigPanel, BorderLayout.CENTER);
	}

	private JButton createThemeButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(AppFont.titleSecondary());
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setMaximumSize(new Dimension(300, 70));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		btn.setFocusPainted(false);
		btn.putClientProperty("JComponent.focusWidth", 0);
		return btn;
	}

	private void applyButtonState(JButton btn, boolean active) {
		if (active) {
			btn.setBackground(BTN_ACTIVE_BG);
			btn.setForeground(BTN_ACTIVE_FG);
		} else {
			btn.setBackground(BTN_IDLE_BG);
			btn.setForeground(BTN_IDLE_FG);
		}
	}

	private void addHoverBehavior(JButton btn, boolean isLightBtn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(BTN_ACTIVE_BG);
				btn.setForeground(BTN_ACTIVE_FG);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				boolean dark = isDarkTheme();
				boolean isActive;
				if (isLightBtn) {
					isActive = !dark;
				} else {
					isActive = dark;
				}
				applyButtonState(btn, isActive);
			}
		});
	}

	public void applyThemeColors() {
		boolean isDark = isDarkTheme();

		Color bgColor;
		Color bigBdColor;
		Color midBdColor;
		Color textColor;
		String themeLabel;

		if (isDark) {
			bgColor = DARK_BG;
			bigBdColor = DARK_BIG;
			midBdColor = DARK_MID;
			textColor = Color.WHITE;
			themeLabel = "Current theme: Dark";
		} else {
			bgColor = LIGHT_BG;
			bigBdColor = LIGHT_BIG;
			midBdColor = LIGHT_MID;
			textColor = Color.BLACK;
			themeLabel = "Current theme: Light";
		}

		bigPanel.setBackground(bigBdColor);
		bigPanel.setBorder(BorderFactory.createLineBorder(bigBdColor, 20));
		midPanel.setBackground(midBdColor);
		midPanel.setBorder(BorderFactory.createLineBorder(midBdColor, 20));
		centerPanel.setBackground(bgColor);

		lblTitle.setForeground(textColor);
		lblCurrentTheme.setForeground(textColor);
		lblCurrentTheme.setText(themeLabel);

		applyButtonState(btnLight, !isDark);
		applyButtonState(btnDark, isDark);

		revalidate();
		repaint();
	}

	private boolean isDarkTheme() {
		return Config.get("ui.theme", "light").equalsIgnoreCase("dark");
	}

	public SettingsWindow getWindow() {
		return window;
	}

	public JButton getBtnLight() {
		return btnLight;
	}

	public JButton getBtnDark() {
		return btnDark;
	}
}