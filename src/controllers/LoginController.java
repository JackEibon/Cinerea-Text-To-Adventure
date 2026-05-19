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
	
	public LoginController(LoginView loginView){
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
			if(validateAndShow(user)) {
			    
			}
		} catch (InvalidUserException | InvalidPasswordException e) {
			view.getErrPassword().setText("Invalid Credentials");
			view.getErrPassword().setVisible(true);
		} 
	}
	
	
	private void handleLogin() throws InvalidUserException, InvalidPasswordException {
		if(!validateAndShow(new User(view.getEmail(), view.getPassword()))){
			return;
		}
		
		User user = repository.login(view.getEmail(), view.getPassword());
		
		if(user == null) {
			view.getErrPassword().setText("Invalid Credentials");
			view.getErrPassword().setVisible(true);
			return;
		}
		
		Session.login(user);
		
		if(Session.getRole().equals("ADMIN")) {
			new HomeController(new MainWindow());	
		} else {
			JOptionPane.showMessageDialog(view.getWindow(), "You don't have permissions");
		}

		view.getWindow().dispose();
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