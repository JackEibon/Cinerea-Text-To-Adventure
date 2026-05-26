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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = view.hiddenInput.getText();
        String response = logic.execute(command);
        view.appendText("> " + command + "\n" + response);
        view.hiddenInput.setText("");
        view.reader.repaint();
    }
}