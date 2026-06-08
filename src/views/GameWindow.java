package views;

import javax.swing.JFrame;

import controllers.GameController;
import gamelogic.GameLogic;
import models.User;

public class GameWindow extends JFrame {

	private GameView gameView;
	private GameLogic logic;
	private User user = null;

	public GameWindow() {
		logic = new GameLogic();

		setTitle("Cinerea - Adventure");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		gameView = new GameView(logic.getPlayerSheet());
		add(gameView);
		
		new GameController(gameView, logic, this);
		setVisible(true);
		requestFocus();
	}

	public GameWindow(User user) {
		this.user = user;
		setTitle("Cinerea - Adventure");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		logic = new GameLogic(user);

		gameView = new GameView(logic.getPlayerSheet());

		add(gameView);
		new GameController(gameView, logic, this);
		setVisible(true);
		requestFocus();
	}

	public GameView getGameView() {
		return gameView;
	}

}