package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gamelogic.GameLogic;
import models.User;
import views.GameView;

public class GameController implements ActionListener {

	private final GameView view;
	private final GameLogic logic;
	private User user= null;

	public GameController(GameView view, GameLogic logic) {
		this.logic = logic;
		
		
		
		this.view = view;
		view.hiddenInput.addActionListener(this);
		appendText("\nYou are falling across the dark, on a sea of black."
				+ "\nSuddenly, you crash against something frail, and it breaks."
				+ "\nA thousand pieces fall by your side, and you fall unto the unwanted ground."
				+ "\n\n\n" + "look around  ");
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