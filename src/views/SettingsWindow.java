package views;

import java.awt.Toolkit;
import javax.swing.JDialog;
import controllers.SettingsController;

public class SettingsWindow extends JDialog {

	private SettingsView settingsView;

	public SettingsWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/assets/img/pixeles.png"));
		setTitle("Cinerea – Settings");
		setSize(600, 520);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);

		settingsView = new SettingsView(this);
		add(settingsView);

		new SettingsController(settingsView);

		setVisible(true);
	}

	public SettingsView getSettingsView() {
		return settingsView;
	}
}
