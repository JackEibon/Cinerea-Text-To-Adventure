package controllers;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.User;
import repository.LoginRepository;
import utils.PasswordUtils;
import utils.Session;
import views.LoginView;
import views.MainWindow;
import views.SignUpWindow;

public class LoginController {

	private LoginView view;
	private LoginRepository repository;

	public LoginController(LoginView loginView) {
		repository = new LoginRepository();
		this.view = loginView;
		registerListeners();
	}

	public void registerListeners() {
		view.getTxtEmail().getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				handle();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				handle();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				handle();
			}
		});

		view.getBtnLogin().addActionListener(e -> {
			try {
				handleLogin();
			} catch (InvalidUserException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidPasswordException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		view.getBtnRegister().addActionListener(e -> handleBtnRegister());
	}

	private void handle() {
		User user = new User(view.getEmail(), String.valueOf(view.getTxtPassword().getPassword()));

		try {
			if (validateAndShow(user)) {

			}
		} catch (InvalidUserException | InvalidPasswordException e) {
			view.getErrPassword().setText("Invalid Credentials");
			view.getErrPassword().setVisible(true);
		}
	}

	private void handleLogin() throws InvalidUserException, InvalidPasswordException {
		if (!validateAndShow(new User(view.getEmail(), view.getPassword()))) {
			return;
		}

		User user = null;

		try {
			String sql = "SELECT id_user_cinerea, email, wordpass, role_cinerea, nickname FROM user_cinerea WHERE email = ?";
			java.sql.Connection conn = config.DatabaseConnection.getConnection();
			if (conn == null) {
				view.getErrPassword().setText("Connection with database failed");
				view.getErrPassword().setVisible(true);
				return;
			}
			try (java.sql.Connection c = conn; java.sql.PreparedStatement stmt = c.prepareStatement(sql)) {
				stmt.setString(1, view.getEmail()); // stmt es statement
				try (java.sql.ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						String hashedPassword = rs.getString("wordpass");
						boolean correctPassword = PasswordUtils.checkPassword(view.getPassword(), hashedPassword);
						if (!correctPassword) {
							user = null;
						} else {
							user = new User();
							user.setId(rs.getInt("id_user_cinerea"));
							user.setEmail(rs.getString("email"));
							user.setNickname(rs.getString("nickname"));
							user.setRole_cinerea(rs.getString("role_cinerea"));
							user.setWeapon(rs.getString("weapon"));
							user.setGem(rs.getString("gem"));
							user.setElements(rs.getString("elements"));
						}
					}
				}
			}
		} catch (java.sql.SQLException ex) {
			ex.printStackTrace();
			user = null;
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			user = null;
		}

		if (user == null) {
			view.getErrPassword().setText("Invalid Credentials");
			view.getErrPassword().setVisible(true);
			return;
		}

		Session.login(user);

		MainWindow mainWindow = new MainWindow();
		new HomeController(mainWindow);
		String role = Session.getRole();
		boolean isAdmin = (role != null && role.trim().equalsIgnoreCase("ADMIN"));
		if (!isAdmin) {
			mainWindow.showView(MainWindow.USERS);
		}

		view.getWindow().dispose();
	}

	private boolean validateAndShow(User user) throws InvalidUserException, InvalidPasswordException {
		view.getErrEmail().setVisible(false);
		view.getErrPassword().setVisible(false);

		boolean isValid = true;
		boolean throwingmail = false;
		boolean throwingpass = false;
		String email = user.getEmail();
		String pass = user.getPassword();

		if (email.trim().isEmpty()) {
			view.getErrEmail().setText("Email required");
			view.getErrEmail().setVisible(true);
			isValid = false;
		} else if (!email.contains("@")) {
			view.getErrEmail().setText("Valid email required");
			view.getErrEmail().setVisible(true);
			isValid = false;
		}

		if (pass.isEmpty()) {
			view.getErrPassword().setText("Password is required");
			view.getErrPassword().setVisible(true);
			isValid = false;
		}
		view.revalidate();
		view.repaint();

		if (throwingmail)
			throw new InvalidUserException("");
		if (throwingpass)
			throw new InvalidPasswordException("");
		return isValid;
	}

	private void handleBtnRegister() {
		new SignUpWindow();
		view.getWindow().dispose();
	}
}