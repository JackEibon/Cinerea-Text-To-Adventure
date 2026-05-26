package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gamelogic.GameLogic;
import views.GameView;
import views.GameWindow;

public class GameController implements ActionListener {

    private final GameView view;
    private final GameLogic logic;

    public GameController(GameView view, GameLogic logic) {
        this.view = view;
        this.logic = logic;

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