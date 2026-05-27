package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assets.utils.AppFont;
import java.util.Properties;
import models.User;
import utils.ThemeManager;
import utils.WindowPreferences;

public class MainWindow extends JFrame {

	/* Para la tabla de Usuarios */
	public static final String HOME = "Home";
	public static final String USERS = "Users";
	public JMenuItem mItemExit;
	public JButton usersBtn;
	public JButton homeBtn;
	public UsersView usersPanel;
	private CardLayout cardLayout; // gestor de diseño
	private JPanel container; // la caja/panel
	private JPanel bigPanel, midPanel, centerPanel;
	private JLabel lblTitle;

	private static final Color LIGHT_BG = new Color(92, 122, 237);
	private static final Color LIGHT_BIG = new Color(17, 53, 189);
	private static final Color LIGHT_MID = new Color(52, 86, 217);
	private static final Color DARK_BG = new Color(22, 30, 70);
	private static final Color DARK_BIG = new Color(8, 12, 38);
	private static final Color DARK_MID = new Color(14, 22, 55);

	public MainWindow() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		setIconImage(tk.getImage("src/assets/img/pixeles.png"));
		setTitle("Cinerea");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Properties prefs = WindowPreferences.load();
		int width = Integer.parseInt(prefs.getProperty("window.width", "1000"));
		int heigth = Integer.parseInt(prefs.getProperty("window.height", "750"));
		int x = Integer.parseInt(prefs.getProperty("window.x", "-1"));
		int y = Integer.parseInt(prefs.getProperty("window.y", "-1"));
		setSize(width, heigth);
		if (x == -1 || y == -1)
			setLocationRelativeTo(null);
		else
			setLocation(x, y);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				WindowPreferences.save(getWidth(), getHeight(), getX(), getY());
			}
		});

		initializeCompounds();

		String role = null;
		try {
			role = utils.Session.getRole();
		} catch (Exception ignored) {
		}
		boolean isAdmin = (role != null && role.trim().equalsIgnoreCase("ADMIN"));

		if (isAdmin) {
			setMenu();
			createNavbar();
			createViews();
		}

		ThemeManager.addThemeListener(this::applyThemeColors);
		setVisible(true);
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

		lblTitle = new JLabel("CINEREA");
		lblTitle.setFont(AppFont.title());
		lblTitle.setForeground(textColor); 
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(lblTitle);

		centerPanel.add(Box.createRigidArea(new Dimension(0, 80)));

		JButton btnPlay = new JButton("Start");
		addButtonMain(centerPanel, "Start", btnPlay);
		btnPlay.addActionListener(e -> {
		    new GameWindow();
		    dispose();
		});

		btnPlay.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnPlay);
			}

			public void mouseExited(MouseEvent e) {
				resetBackground(btnPlay);
			}
		});

		centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JButton btnConfig = new JButton();
		addButtonMain(centerPanel, "Settings", btnConfig);
		btnConfig.addActionListener(e -> new SettingsWindow());
		btnConfig.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnConfig);
			}

			public void mouseExited(MouseEvent e) {
				resetBackground(btnConfig);
			}
		});

		centerPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JButton btnTutorial = new JButton();
		addButtonMain(centerPanel, "Tutorial", btnTutorial);
		btnTutorial.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				changeBackground(btnTutorial);
			}

			public void mouseExited(MouseEvent e) {
				resetBackground(btnTutorial);
			}
		});

		bigPanel.add(midPanel);
		midPanel.add(centerPanel);
		add(bigPanel);
	}

	private JButton addButtonMain(JPanel panel, String labelText, JButton button) {
		pixelBorderText(button);
		button.setText(labelText);
		button.setFont(AppFont.titleSecondary());
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setMaximumSize(new Dimension(400, 100));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(button);
		return button;
	}

	public void setWindowSize(int width, int height) {
		setSize(width, height);
	}

	public void setWindowLocation(int x, int y) {
		setLocation(x, y);
	}

	public void setMenu() {

		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);

		JMenu mF = new JMenu("File");
		mF.setMnemonic(KeyEvent.VK_F);
		mb.add(mF);

		JMenuItem mItemOpen = new JMenuItem("Open");
		mItemOpen.setMnemonic(KeyEvent.VK_O);
		mF.add(mItemOpen);

		JMenuItem mItemSave = new JMenuItem("Save");
		mItemSave.setMnemonic(KeyEvent.VK_S);
		mF.add(mItemSave);

		mF.addSeparator();

		mItemExit = new JMenuItem("Exit");
		mItemExit.setMnemonic(KeyEvent.VK_E);
		mF.add(mItemExit);

	}

	public void createNavbar() {
		JPanel navbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		homeBtn = new JButton("Home");
		usersBtn = new JButton("Users");

		navbar.add(homeBtn);
		navbar.add(usersBtn);

		add(navbar, BorderLayout.NORTH);
	}

	private void createViews() {
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		JPanel homePanel = new JPanel();
		homePanel.add(new JLabel("Welcome"));
		usersPanel = new UsersView();
		container.add(homePanel, HOME);
		container.add(usersPanel, USERS);
		add(container, BorderLayout.CENTER);
	}

	public void showView(String view) {
		if (cardLayout == null || container == null) {
			if (usersPanel != null && (USERS.equals(view) || "Users".equals(view))) {
				getContentPane().removeAll();
				add(usersPanel, BorderLayout.CENTER);
				revalidate();
				repaint();
			}
			return;
		}
		cardLayout.show(container, view); // usaremos el panel container, nice, segun el view (Home o Users)
	}

	public int confirmExit() { // pq esto es un int?, naturalmente, JOptionPane es un int
		return JOptionPane.showConfirmDialog(this, "¿Are you sure you want to go back? The data will be lost", "¿Sure?",
				JOptionPane.YES_NO_OPTION);
	}

	private void changeBackground(JComponent component) {
		component.setBackground(new Color(17, 53, 189));
		component.setForeground(Color.white);
	}

	private void resetBackground(JComponent component) {
		component.setBackground(Color.white);
		component.setForeground(Color.black);
	}

	private void pixelBorderText(JComponent component) {
		component.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		component.setFont(AppFont.normalSecondary());
		component.setForeground(Color.black);
		component.setBackground(Color.WHITE);
		component.putClientProperty("JComponent.focusWidth", 0);
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

		revalidate();
		repaint();
	}

	private boolean isDarkTheme() {
		return config.Config.get("ui.theme", "light").equalsIgnoreCase("dark");
	}
}