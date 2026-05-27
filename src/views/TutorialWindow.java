package views;

import java.awt.Toolkit;
import javax.swing.JFrame;
import controllers.SettingsController;

public class TutorialWindow extends JFrame {

	private TutorialView TutorialView;

	public TutorialWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/assets/img/pixeles.png"));
		setTitle("Cinerea – Tutorial");
		setSize(600, 520);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		TutorialView = new TutorialView(this);
		add(TutorialView);

		new TutorialController(TutorialView);

		setVisible(true);
	}

	public TutorialView getTutorialView() {
		return tutorialView;
	}
}
