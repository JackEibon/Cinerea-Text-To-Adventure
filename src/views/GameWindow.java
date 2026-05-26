package views;

import javax.swing.JFrame;

import controllers.GameController;
import gamelogic.GameLogic;

public class GameWindow extends JFrame {

    private GameView gameView;

    public GameWindow() {

        setTitle("Cinerea - Adventure");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameView gameView = new GameView();

        add(gameView);

        GameController controller = new GameController(gameView, new GameLogic());

        setVisible(true);
    }

    public GameView getGameView() {
        return gameView;
    }
}