package views;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import controllers.LoginController;

public class LoginWindow extends JFrame {

	private LoginView loginView;

	public LoginWindow() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image myIcon = tk.getImage("src/assets/img/pixeles.png");

		setIconImage(myIcon);
		setTitle("Login");
		setSize(630, 680);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginView = new LoginView(this);

		add(loginView);

		if (loginView.getClientProperty("controller") == null) {
			loginView.putClientProperty("controller", true);
			new LoginController(loginView);
		}
		// System.out.println("LoginWindow creado: " + this);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				Image poorIcon = Toolkit.getDefaultToolkit().getImage("src/assets/img/pixelesgray.png");
				setIconImage(poorIcon);
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				Image icon = Toolkit.getDefaultToolkit().getImage("src/assets/img/pixeles.png");
				setIconImage(icon);
			}
		});

		setVisible(true);
	}

	public LoginView getLoginView() {
		return loginView;
	}
}