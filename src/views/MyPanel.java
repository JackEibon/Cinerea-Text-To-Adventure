package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyPanel extends JPanel{
	
	public MyPanel() { //Creamos el constructor para el/los paneles que usaremos para personalizarlos
		Font font = new Font("Arial", Font.BOLD, 22);
		setBackground(new Color(255, 239, 182));
		setLayout(null); //Hacemos que no salgan las cosas por defecto
		JButton button = new JButton("Sign In", new ImageIcon("src/img/happy.png")); //Creacion del boton mediante un constructor (Pueden recibir multiples tipos de parametros, por eso es necesario verificar cual es más conveniente
		
		/*
		 * button.setBackground(Color.ORANGE); //Cambia el color del fondo
		 * button.setForeground(Color.BLACK); //Cambia el color de la letra
		 * button.setFont(new Font("Arial", Font.ITALIC, 10)); //Cambia el estilo de la letra y si siempre va a ser el mismo tipo de letra se puede poner como objeto instanciado desde el inicio
		 */
		
		initializeButtonImage(button, "../img/happy.png"); //Se utiliza del metodo de abajo
		
		button.setToolTipText("Te redigira a otra pestaña"); //Añade un texto al pasar el mouse por encima
		button.setBounds(440, 375, 120, 50);
		add(button);
		
		JLabel grettings = new JLabel("Welcome!, explore the wonderfull and bizarre world of Cinerea");
		initializeCustomLabels(grettings, font, 175, 30, 800, 40);
		grettings.setForeground(Color.black);
		add(grettings);
		
		JLabel login = new JLabel("Login");
		initializeCustomLabels(login, font, 470, 30, 800, 150);
		grettings.setForeground(Color.black);
		add(login);
		
		JLabel email = new JLabel("EMAIL");
		initializeCustomLabels(email, font, 300, 30, 500, 300);
		grettings.setForeground(Color.black);
		add(email);
		
		JLabel passwordLabel = new JLabel("PASSWORD");
		initializeCustomLabels(passwordLabel, font, 300, 30, 500, 520);
		grettings.setForeground(Color.black);
		add(passwordLabel);
		
		JTextField text = new JTextField("");
		text.setForeground(Color.black);
		text.setBounds(300, 220, 400, 40);
		text.setFont(font);
		add(text);
		
		JPasswordField password = new JPasswordField("");
		password.setForeground(Color.black);
		password.setBounds(300, 320, 400, 40);
		password.setFont(font);
		add(password);
		
		/*
		 * add(Component c) se añade el componente a la ventana
		 * setBackgrond(new Color(210, 165, 35))
		 * setLayout(null) Cuando se le introduce null se centra en el panel
		 * setBound(y, x, width, height)
		 */
	}
	
	private void initializeButtonImage(JButton button, String route) {
		try { //Esto es para que el icono no quede feo adentro del boton que vayamos a utilizar
			Image icon = ImageIO.read(getClass().getResource(route)); //Obtener la imagen
			icon = icon.getScaledInstance(40, 40, Image.SCALE_SMOOTH); //Redimensionar la imagen dentro del boton
			button.setIcon(new ImageIcon(icon));
		}catch(Exception ex){
			System.out.println("No esta la imagen del icono");
		}
	}
	
	private void initializeCustomLabels(JLabel label, Font font, int width, int height, int horizontal, int vertical) {
		label.setBounds(width, height, horizontal, vertical);
		label.setFont(font);
	}
	
	private void initializeDefaultLabels(JLabel label, Font font, int horizontal, int vertical) {
		label.setBounds(175, 30, horizontal, vertical);
		label.setFont(font);
	}
	
}
