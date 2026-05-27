package controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import config.Config;
import views.MainWindow;

public class HomeController {
	private MainWindow view;
	private UserController userController;
	/*Constructor */
	public HomeController(MainWindow view) {
		this.view = view;
		
		loadWindowPreferences();
		registerListeners();
		
	}
	
	public void registerListeners( ) {
		// Evita NPE si el menú no fue inicializado en MainWindow.
		if (view.mItemExit != null) {
			view.mItemExit.addActionListener(e -> handleClose());
		}
		
		view.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});
		
		// Navbar/vistas para ADMIN. En caso de usuario normal, estos botones pueden ser null.
		if (view.usersBtn != null) {
			view.usersBtn.addActionListener(e -> {
				showUsers();
			});
		}
		if (view.homeBtn != null) {
			view.homeBtn.addActionListener(e -> {
				view.showView(MainWindow.HOME);
				updateMenuState(MainWindow.HOME);
			}); //showView pq es el public, clarooo (yo media hora intentando usar create views, que nada que ver)
		}

		
		view.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				saveWindowPreferences();
				handleClose();
			}
		});
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
	
	private void saveWindowPreferences() {
		Dimension size = view.getSize();
		Point point = view.getLocation();
		
		Config.set("registration.window.width", 
				String.valueOf(size.width));
		
		Config.set("registration.window.height", 
				String.valueOf(size.height));
		
		Config.set("registration.window.x", 
				String.valueOf(point.x));
		
		Config.set("registration.window.y", 
				String.valueOf(point.y));
		
	}
	
	private void loadWindowPreferences()
	{
		int width = Integer.parseInt(
				Config.get("registration.window.width"
						, "500"));
		
		int height = Integer.parseInt(
				Config.get("registration.window.height"
						, "500"));
		
		String xValue = Config.get("registration.window.x"
						, "");
		
		String yValue = Config.get("registration.window.y"
				, "");
		
		if(!xValue.isBlank() && !yValue.isBlank()) {
			view.setWindowLocation(Integer.parseInt(xValue), Integer.parseInt(yValue));
		}else {
			view.setLocationRelativeTo(null);
		}
		
		view.setWindowSize(width, height);
	}
}