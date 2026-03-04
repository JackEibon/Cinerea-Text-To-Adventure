package main;

import com.formdev.flatlaf.FlatLightLaf; 
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities; 

import utils.AppFont;
import views.LogInWindow;
import views.SignUpWindow;

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
        LogInWindow window = new LogInWindow(); 
    }
}