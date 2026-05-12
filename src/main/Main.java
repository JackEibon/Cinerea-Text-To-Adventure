package main;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import controllers.HomeController;
import controllers.LoginController;
import utils.ThemeManager;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import assets.utils.AppFont;
import views.LoginWindow;
import views.MainWindow;

public class Main {

    public static void main(String[] args) {
    	//FlatLightLaf.setup();
    	ThemeManager.applySavedTheme();
        
    	//new HomeController(new MainWindow());
		//new LoginController(new LoginWindow().getLoginView());

        //UIManager.put("Label.font", AppFont.normal());
        
        //SignUpWindow window = new SignUpWindow(); 
    	new LoginController(new LoginWindow().getLoginView());
        //new LoginController(window.getLoginView());
    }
}