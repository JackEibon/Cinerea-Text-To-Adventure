package views;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import controllers.SettingsController;
import controllers.TutorialController;

public class TutorialWindow extends JDialog {

	private TutorialView TutorialView;

	public TutorialWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/assets/img/pixeles.png"));
		setTitle("Cinerea – Tutorial");
		setSize(600, 700);
		setMinimumSize(new Dimension(600, 700));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);

		TutorialView = new TutorialView(this);
		add(TutorialView);

		new TutorialController(TutorialView);

		setVisible(true);
	}

	public TutorialView getTutorialView() {
		return TutorialView;
	}
}
