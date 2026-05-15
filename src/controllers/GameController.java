package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gamelogic.GameLogic;
import views.GameWindow;

public class GameController implements ActionListener {
	

    private final GameWindow view;
    private final GameLogic logic;

    public GameController(GameWindow view, GameLogic logic) {
        this.view = view;
        this.logic = logic;
        System.out.print("here2");

        this.view.commandInput.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = view.commandInput.getText();
        String response = logic.execute(command);

        view.appendText("> " + command + "\n" + response);
        view.commandInput.setText("");
    }
}
