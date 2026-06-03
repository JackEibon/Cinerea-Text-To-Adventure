package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import assets.utils.AppFont;
import config.Config;
import utils.ThemeManager;

public class LoginView extends JPanel {

	private LoginWindow window;

	private JPanel bigPanel, midPanel, centerPanel;
	private JLabel lblTitle;

	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JLabel errEmail, errPassword;
	private JButton btnLogin, btnRegister;
	private List<JLabel> formLabels = new ArrayList<>();

	private static final Color LIGHT_BG = new Color(92, 122, 237);
	private static final Color LIGHT_BIG = new Color(17, 53, 189);
	private static final Color LIGHT_MID = new Color(52, 86, 217);

	private static final Color DARK_BG = new Color(22, 30, 70);
	private static final Color DARK_BIG = new Color(8, 12, 38);
	private static final Color DARK_MID = new Color(14, 22, 55);

	public LoginView(LoginWindow window) {
		this.window = window;
		setLayout(new BorderLayout());
		initializeCompounds();

		// Se registra para recibir cambios de tema desde cualquier ventana con este
		// foreach
		ThemeManager.addThemeListener(this::applyThemeColors);
	}

	public void initializeCompounds() {
		Color bgColor;
		Color bigBdColor;
		Color midBdColor;
		Color textColor;

		if (isDarkTheme()) {
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

		bigPanel = new JPanel();
		bigPanel.setBackground(bigBdColor);
		bigPanel.setLayout(new BorderLayout());
		bigPanel.setBorder(BorderFactory.createLineBorder(bigBdColor, 20));

		midPanel = new JPanel();
		midPanel.setBackground(midBdColor);
		midPanel.setLayout(new BorderLayout());
		midPanel.setBorder(BorderFactory.createLineBorder(midBdColor, 20));

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(bgColor);
		centerPanel.setBorder(new EmptyBorder(40, 60, 40, 60));

		lblTitle = new JLabel("LOGIN");
		lblTitle.setFont(AppFont.title());
		lblTitle.setForeground(textColor);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(lblTitle);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		txtEmail = new JTextField();
		errEmail = addFormGroup(centerPanel, "EMAIL", txtEmail);

		txtPassword = new JPasswordField();
		errPassword = addFormGroup(centerPanel, "PASSWORD", txtPassword);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		btnLogin = new JButton();
		addButtonForm("Log In", btnLogin);
		btnLogin.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnLogin);
			}

			public void mouseExited(MouseEvent e) {
				resetBackground(btnLogin);
			}
		});

		centerPanel.add(btnLogin);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		btnRegister = new JButton();
		addButtonForm("Sign Up", btnRegister);
		btnRegister.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnRegister);
			}

			public void mouseExited(MouseEvent e) {
				resetBackground(btnRegister);
			}
		});

		centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		centerPanel.add(btnRegister);

		bigPanel.add(midPanel);
		midPanel.add(centerPanel);
		add(bigPanel, BorderLayout.CENTER);
	}

	public void applyThemeColors() {
		Color bgColor;
		Color bigBdColor;
		Color midBdColor;
		Color textColor;

		if (isDarkTheme()) {
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

		for (JLabel lbl : formLabels) {
			lbl.setForeground(textColor);
		}

		revalidate();
		repaint();
	}

	private boolean isDarkTheme() {
		return Config.get("ui.theme", "light").equalsIgnoreCase("dark");
	}

	private void changeBackground(JComponent component) {
		component.setBackground(new Color(17, 53, 189));
		component.setForeground(Color.white);
	}

	private void resetBackground(JComponent component) {
		component.setBackground(Color.white);
		component.setForeground(Color.black);
	}

	private void addButtonForm(String labelText, JButton button) {
		pixelBorderText(button);
		button.setText(labelText);
		button.setFont(AppFont.titleSecondary());
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setMaximumSize(new Dimension(250, 50));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	private JLabel addFormGroup(JPanel panel, String labelText, JTextField field) {
		JLabel lbl = new JLabel(labelText);
		formLabels.add(lbl);
		lbl.setFont(AppFont.normalSecondary());
		lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lbl);

		pixelBorderText(field);
		field.setMaximumSize(new Dimension(400, 45));
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(field);

		JLabel lblError = new JLabel(" ");
		lblError.setFont(new Font("Monospaced", Font.BOLD, 14));
		lblError.setForeground(Color.CYAN);
		lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblError.setVisible(false);
		panel.add(lblError);

		panel.add(Box.createRigidArea(new Dimension(0, 25)));
		return lblError;
	}

	private void pixelBorderText(JComponent component) {
		component.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		component.setFont(AppFont.normalSecondary());
		component.setForeground(Color.black);
		component.setBackground(Color.WHITE);
		component.putClientProperty("JComponent.focusWidth", 0);
	}

	public LoginWindow getWindow() {
		return window;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public JLabel getErrEmail() {
		return errEmail;
	}

	public JLabel getErrPassword() {
		return errPassword;
	}

	public String getEmail() {
		return txtEmail.getText();
	}

	public String getPassword() {
		return new String(txtPassword.getPassword());
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}
}