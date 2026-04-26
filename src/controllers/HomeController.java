package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.User;
import repository.UserRepository;
import tablemodels.UserTableModel;
import views.LoginWindow;
import views.MainWindow;

public class HomeController {
	private MainWindow view;
	private UserController userController;
	/*Constructor */
	public HomeController(MainWindow view) {
		this.view = view;
		registerListeners();
	}
	
	public void registerListeners( ) {
		
		view.mItemExit.addActionListener(e -> handleClose()); //pq es null?
		
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});
		
		view.usersBtn.addActionListener(e -> {
			showUsers();
		});
		
		view.homeBtn.addActionListener(e -> {
			view.showView(MainWindow.HOME);
			updateMenuState(MainWindow.HOME);
		}); //showView pq es el public, clarooo (yo media hora intentando usar create views, que nada que ver)	
	}
	
	private void showUsers() {
		if(userController == null) {
			userController = new UserController(view.usersPanel);
		}
			
		userController.loadUsers();
		
		view.showView(MainWindow.USERS);
		updateMenuState(MainWindow.USERS);
	}
	
	private void handleClose() {
		view.dispose();
	}
	
	private void updateMenuState(String viewName) {
		view.usersBtn.setEnabled(!viewName.equals(MainWindow.USERS));
		view.homeBtn.setEnabled(!viewName.equals(MainWindow.HOME));
	}
}