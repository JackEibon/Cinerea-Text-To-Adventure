package controllers;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.InvalidPasswordException;
import exceptions.InvalidUserException;
import views.LogInWindow;
import views.MainWindow;
import views.SignUpWindow;

public class LoginController {
	
	private LogInWindow view;

	LoginController(LogInWindow view){
		this.view = view;
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
		try {
			if(validateAndShow(view.getEmail(), String.valueOf(view.getTxtPassword().getPassword()))) {
			    JOptionPane.showMessageDialog(view, "Welcome, " + view.getTxtEmail().getText().trim() + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
			    new MainWindow();
			    view.dispose();
			}
		} catch (InvalidUserException | InvalidPasswordException e) {
			// TODO Auto-generated catch block
			view.getErrPassword().setText("Credenciales Invalidas");
			view.getErrPassword().setVisible(true);
		} //Aqui proximamente pondremos la variable del personaje al que le pertenece la cuenta
	}
	
	private boolean validateAndShow(String txtEmail, String pass) throws InvalidUserException, InvalidPasswordException {
		view.getErrEmail().setVisible(false);
		view.getErrPassword().setVisible(false);

        boolean isValid = true;
        
        if(txtEmail.trim().isEmpty()) { 
        	view.getErrEmail().setText("Email required"); 
        	view.getErrEmail().setVisible(true); 
        	isValid = false;
        }else if(!txtEmail.contains("@")) { 
        	view.getErrEmail().setText("Valid email required"); 
        	view.getErrEmail().setVisible(true);
        	isValid = false;
        }else if(!txtEmail.trim().equals("esoto_24@alu.uabcs.mx")) {
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
    	view.dispose();
	}
}
