package main;

import com.formdev.flatlaf.FlatLightLaf;

import controllers.LoginController;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import assets.utils.AppFont;
import views.LoginWindow;

public class Main {

    public static void main(String[] args) {
        
        try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        UIManager.put("Label.font", AppFont.normal());
        
        //SignUpWindow window = new SignUpWindow(); 
        LoginWindow window = new LoginWindow(); 
        new LoginController(window.getLoginView());
    }
}