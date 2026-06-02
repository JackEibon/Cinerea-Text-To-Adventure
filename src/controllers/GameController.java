package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gamelogic.GameLogic;
import views.GameView;

public class GameController implements ActionListener {

    private final GameView view;
    private final GameLogic logic;

    public GameController(GameView view, GameLogic logic) {
        this.view = view;
        this.logic = logic;
        view.hiddenInput.addActionListener(this);
        appendText(
        		"\nYou are falling across the dark, on a sea of black."+   
        		"\nSuddenly, you crash against something frail, and it breaks."+ 
        		"\nA thousand pieces fall by your side, and you fall unto the unwanted ground."
        		
+"\n\n\n"
+" look around  "
        		);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = view.hiddenInput.getText();
        String response = logic.execute(command);
        appendText("> " + command + " \n" + response);
        view.hiddenInput.setText("");
        view.reader.repaint();       
    }   
    public void appendText(String text) {view.appendColoredText(text);}
}