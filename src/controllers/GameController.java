package controllers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import gamelogic.GameLogic;
import models.User;
import views.GameView;
import views.LoginWindow;
import views.MainWindow;

public class GameController implements ActionListener {

	private final GameView view;
	private final GameLogic logic;
	private final Window window;
	private User user = null;

	public GameController(GameView view, GameLogic logic, Window window) {
		this.logic = logic;
		this.view = view;
		this.window = window;
		
		view.hiddenInput.addActionListener(this);
		appendText("\nYou are falling across the dark, on a sea of black."
				+ "\nSuddenly, you crash against something frail, and it breaks."
				+ "\nA thousand pieces fall by your side, and you fall unto the unwanted ground." + "\n\n\n"
				+ "look around  ");
		
		this.window.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        int option = JOptionPane.showConfirmDialog(
		        	window,
		            "Are you sure?", 
		            "Closing confirmation", 
		            JOptionPane.YES_NO_OPTION, 
		            JOptionPane.QUESTION_MESSAGE
		        );
		        
		        if (option == JOptionPane.YES_OPTION) {
		        	window.dispose();
		        	new MainWindow();
		        }
		    }
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String response= "";
		//if (logic.getPlayerSheet().isAlive()&&logic.getNightEnd()<3) {
		String command = view.hiddenInput.getText();
		response = logic.execute(command.toLowerCase());
		
		appendText("> " + command + "\n" + response);
		
		view.hiddenInput.setText("");
		view.reader.repaint();	
	}
		//else
		//{
			//response= logic.execute("GAME OVER");
			//appendText(response);
		//}
	
	//}

	public void appendText(String text) {
		view.appendColoredText(text);
	}
	
	
}