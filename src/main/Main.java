package main;

import com.formdev.flatlaf.FlatLightLaf; 
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities; 

import utils.AppFont;
import views.LogIn;
import views.SignIn;

public class Main {

    public static void main(String[] args) {
        
        try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        UIManager.put("Label.font", AppFont.normal());
        
        //SignIn window = new SignIn(); 
        LogIn window = new LogIn(); 
    }
}