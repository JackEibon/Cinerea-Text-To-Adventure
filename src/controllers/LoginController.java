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
		
		view.getBtnLogin().addActionListener(e -> handleLogin());
		
		view.getBtnRegister().addActionListener(e -> handleBtnRegister());
	}
	
	private void handle() {
		User user = new User(view.getEmail(), String.valueOf(view.getTxtPassword().getPassword()));

		try {
			if(validateAndShow(user)) {
			    JOptionPane.showMessageDialog(view.getWindow(), "Welcome, " + user.getEmail().trim() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
			    new MainWindow();
			    view.getWindow().dispose();
			}
		} catch (InvalidUserException | InvalidPasswordException e) {
			view.getErrPassword().setText("Invalid Credentials");
			view.getErrPassword().setVisible(true);
		} 
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
			view.getErrPassword().setText("Invalid Credentials");
			view.getErrPassword().setVisible(true);
		} 
	}
	
	private boolean validateAndShow(User user) throws InvalidUserException, InvalidPasswordException {
		view.getErrEmail().setVisible(false);
		view.getErrPassword().setVisible(false);

        boolean isValid = true;
        boolean throwingmail= false;
        boolean throwingpass= false;
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
        }/*else if(!email.trim().equals("esoto_24@alu.uabcs.mx")) {
    		throwingmail=true;
        	
    	}*/

        if (pass.isEmpty()) {
        	view.getErrPassword().setText("Password is required");
        	view.getErrPassword().setVisible(true);
            isValid = false;
        } /*else if (!pass.equals("asdfasdf")) {
        	throwingpass=true;
        	
        }*/
        view.revalidate();
        view.repaint();
        
        if (throwingmail) throw new InvalidUserException("");
        if (throwingpass) throw new InvalidPasswordException("");
        return isValid;
    }
	
	private void handleBtnRegister() {
    	new SignUpWindow();
    	view.getWindow().dispose();
	}
}