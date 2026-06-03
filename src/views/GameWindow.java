package views;

import javax.swing.JFrame;

import controllers.GameController;
import gamelogic.GameLogic;

public class GameWindow extends JFrame {

	private GameView gameView;

	public GameWindow() {

		setTitle("Cinerea - Adventure");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameView = new GameView();
		add(gameView);
		new GameController(gameView, new GameLogic());
		setVisible(true);
		requestFocus();
	}

	public GameView getGameView() {
		return gameView;
	}
}