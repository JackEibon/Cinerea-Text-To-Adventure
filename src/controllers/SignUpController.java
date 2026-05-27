package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.User;
import repository.UserRepository;
import utils.PasswordUtils;
import views.LoginWindow;
import views.SignUpView;

public class SignUpController {

	private SignUpView view;
	private UserRepository repository;

	public SignUpController(SignUpView view) {
		this.view = view;
		this.repository = new UserRepository();
		registerListeners();
	}

	private void registerListeners() {

		view.getTxtEmail().getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateTxtEmail();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateTxtEmail();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateTxtEmail();
			}
		});

		view.getTxtNickname().getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateTxtNickname();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateTxtNickname();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateTxtNickname();
			}
		});

		view.getTxtEmail().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (view.getTxtEmail().getText().length() > 30) {
					e.consume();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					view.getTxtNickname().requestFocusInWindow();
				}
			}
		});

		view.getTxtNickname().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (view.getTxtNickname().getText().length() > 20) {
					e.consume();
				}
			}
		});

		view.getCbGems().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validateCb("Pick a gem", view.getCbGems(), view.getErrGem());
			}
		});

		view.getCbWeapon().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validateCb("Pick a weapon", view.getCbWeapon(), view.getErrWeapon());
			}
		});

		view.getCbElement().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validateCb("Pick an element", view.getCbElement(), view.getErrElement());
			}
		});

		view.getBtnRegister().addActionListener(e -> {
			validateRegister();
		});

		view.getBtnCancel().addActionListener(e -> {
			cancelRegister();
		});
	}

	private void registerUser(User user) {
		try {
			repository.save(user);
			JOptionPane.showMessageDialog(view, "Saved");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, e.getMessage());
		}
	}

	private boolean validateTxtEmail() {
		String email = view.getTxtEmail().getText().trim();

		if (email.isEmpty()) {
			view.getErrEmail().setText("Email required");
			view.getErrEmail().setVisible(true);
			return false;
		} else if (!email.contains("@")) {
			view.getErrEmail().setText("Valid email required");
			view.getErrEmail().setVisible(true);
			return false;
		}

		view.getErrEmail().setText("");
		view.getErrEmail().setVisible(false);
		return true;
	}

	private boolean validateTxtNickname() {
		String nickname = view.getTxtNickname().getText().trim();

		if (nickname.isEmpty()) {
			view.getErrNickname().setText("Nickname required");
			view.getErrNickname().setVisible(true);
			return false;
		} else if (nickname.length() <= 4) {
			view.getErrNickname().setText("5 characters minimum");
			view.getErrNickname().setVisible(true);
			return false;
		}

		view.getErrNickname().setText("");
		view.getErrNickname().setVisible(false);
		return true;
	}

	private boolean validateCb(String text, JComboBox<String> combo, JLabel err) {
		if (combo.getSelectedIndex() == 0) {
			err.setText(text);
			err.setVisible(true);
			return false;
		}

		err.setText("");
		err.setVisible(false);
		return true;
	}

	private void validateRegister() {
		JLabel[] allErrors = { view.getErrEmail(), view.getErrNickname(), view.getErrGem(), view.getErrWeapon(),
				view.getErrElement(), view.getErrPassword(), view.getErrConfirm() };

		for (int i = 0; i < allErrors.length; i++) {
			allErrors[i].setVisible(false);
		}

		boolean isValid = true;

		if (!validateTxtEmail()) {
			isValid = false;
		}

		if (!validateTxtNickname()) {
			isValid = false;
		}

		if (!validateCb("Pick a gem", view.getCbGems(), view.getErrGem())) {
			isValid = false;
		}

		if (!validateCb("Pick a weapon", view.getCbWeapon(), view.getErrWeapon())) {
			isValid = false;
		}

		if (!validateCb("Pick an element", view.getCbElement(), view.getErrElement())) {
			isValid = false;
		}

		String password = new String(view.getTxtPass().getPassword());
		String secondPassword = new String(view.getTxtConfirmPass().getPassword());

		if (password.isEmpty()) {
			view.getErrPassword().setText("Password required");
			view.getErrPassword().setVisible(true);
			isValid = false;
		} else if (!password.equals(secondPassword)) {
			view.getErrConfirm().setText("Passwords don't match");
			view.getErrConfirm().setVisible(true);
			isValid = false;
		}

		String HashedPassword = PasswordUtils.hashPassword(password);

		if (isValid) {
			User user = new User(0, view.getTxtNickname().getText(), view.getTxtEmail().getText(),
					view.getCbGems().getSelectedItem().toString(), view.getCbWeapon().getSelectedItem().toString(),
					view.getCbElement().getSelectedItem().toString(), "USER",

					HashedPassword);

			registerUser(user);

			JOptionPane.showMessageDialog(view.getWindow(), "Character Created: " + view.getTxtNickname().getText());
			new LoginWindow();
			view.getWindow().dispose();
			return;
		}

		view.revalidate();
		view.repaint();
	}

	private void cancelRegister() {
		int option = JOptionPane.showConfirmDialog(view.getWindow(), "Cancel Operation?");

		if (option == JOptionPane.YES_OPTION) {
			view.getWindow().dispose();
			new LoginWindow();
		}
	}
}