package controllers;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import models.User;
import views.LoginView;
import views.MainWindow;
import views.SignUpWindow;

public class LoginController {
	
	private LoginView view;

	public LoginController(LoginView loginView){
		this.view = loginView;
		registerListeners();
	}
	
	public void registerListeners() {
		view.getTxtEmail().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleLogin();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleLogin();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleLogin();
            }
        });
		
		view.getBtnLogin().addActionListener(e -> handleLogin());
		
		view.getBtnRegister().addActionListener(e -> handleBtnRegister());
	}
	
	private void handleLogin() {
        
		User user = new User(view.getEmail(), String.valueOf(view.getTxtPassword().getPassword()));

		try {
			if(validateAndShow(user)) {
			    JOptionPane.showMessageDialog(view.getWindow(), "Welcome, " + user.getEmail().trim() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
			    new MainWindow();
			    view.getWindow().dispose();
			}
		} catch (InvalidUserException | InvalidPasswordException e) {
			view.getErrPassword().setText("Credenciales Invalidas");
			view.getErrPassword().setVisible(true);
		} 
	}
	
	private boolean validateAndShow(User user) throws InvalidUserException, InvalidPasswordException {
		view.getErrEmail().setVisible(false);
		view.getErrPassword().setVisible(false);

        boolean isValid = true;
        
        String email = user.getEmail();
        String pass = user.getPassword();
        
        if(email.trim().isEmpty()) { 
        	view.getErrEmail().setText("Email required"); 
        	view.getErrEmail().setVisible(true); 
        	isValid = false;
        }else if(!email.contains("@")) { 
        	view.getErrEmail().setText("Valid email required"); 
        	view.getErrEmail().setVisible(true);
        	isValid = false;
        }else if(!email.trim().equals("esoto_24@alu.uabcs.mx")) {
    		throw new InvalidUserException("");
    	}

        if (pass.isEmpty()) {
        	view.getErrPassword().setText("Password is required");
        	view.getErrPassword().setVisible(true);
            isValid = false;
        } else if (!pass.equals("asdfasdf")) {
        	throw new InvalidPasswordException("");
        }
        
        view.revalidate();
        view.repaint();
        
        return isValid;
    }
	
	private void handleBtnRegister() {
    	new SignUpWindow();
    	view.getWindow().dispose();
	}
}