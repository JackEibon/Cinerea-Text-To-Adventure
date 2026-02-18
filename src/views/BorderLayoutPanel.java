package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import components.TextPrompt;

public class BorderLayoutPanel extends JPanel{
	Font font = new Font("Arial", Font.BOLD, 22);
	Font fontError = new Font("Arial", Font.ITALIC, 14);
	
	public BorderLayoutPanel() {
		setLayout(new BorderLayout());
		Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBackground(new Color(255, 239, 182)); //3, 0, 158
		setBorder(emptyBorder);
		
		JPanel topPanel = new JPanel(); //Crear el espacio para el norte del borderlayout
		topPanel.setBorder(emptyBorder);
		
		JLabel grettings = new JLabel("Welcome!, explore the wonderfull and bizarre world of Cinerea");
		initializeCustomLabels(grettings, font, 175, 30, 800, 40);
		grettings.setForeground(Color.black);
		topPanel.add(grettings);
		
		JPanel centerPanel = new JPanel(); //Crear el espacio para el norte del borderlayout
		centerPanel.setBorder(emptyBorder);
		
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(Box.createVerticalGlue());
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		JLabel login = new JLabel("Login"); //Label de LOGIN
		login.setFont(font);
		centerPanel.add(login);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		JLabel email = new JLabel("EMAIL"); //Label que estara encima del campo de email
		email.setFont(font);
		centerPanel.add(email);
		
		JTextField text = new JTextField(""); //Un campo de texto
		text.setForeground(Color.black);
		text.setBounds(300, 220, 400, 40);
		text.setFont(font);
		centerPanel.add(text);
		
		JLabel emailErrorText = new JLabel("Email is required"); //Label que estara debajo del campo de contraseña
		emailErrorText.setFont(fontError);
		emailErrorText.setForeground(Color.red);
		centerPanel.add(emailErrorText);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		JLabel passwordLabel = new JLabel("PASSWORD"); //Label que estara encima del campo de contraseña
		passwordLabel.setFont(font);
		centerPanel.add(passwordLabel);
		
		JPasswordField password = new JPasswordField(""); //Un campo de contraseña
		password.setForeground(Color.black);
		password.setBounds(300, 320, 400, 40);
		password.setFont(font);
		centerPanel.add(password);
		
		JLabel passwordLabelErrorText = new JLabel("Password is required"); //Label que estara debajo del campo de contraseña
		passwordLabelErrorText.setFont(fontError);
		passwordLabelErrorText.setForeground(Color.red);
		centerPanel.add(passwordLabelErrorText);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		JButton button = new JButton("Sign In", new ImageIcon("src/img/happy.png")); //Creacion del boton mediante un constructor (Pueden recibir multiples tipos de parametros, por eso es necesario verificar cual es más conveniente
		/*
		 * button.setBackground(Color.ORANGE); //Cambia el color del fondo
		 * button.setForeground(Color.BLACK); //Cambia el color de la letra
		 * button.setFont(new Font("Arial", Font.ITALIC, 10)); //Cambia el estilo de la letra y si siempre va a ser el mismo tipo de letra se puede poner como objeto instanciado desde el inicio
		 */
		initializeButtonImage(button, "../img/happy.png"); //Se utiliza del metodo de abajo
		button.setToolTipText("Te redigira a otra pestaña"); //Añade un texto al pasar el mouse por encima
		button.setBounds(440, 425, 120, 50);
		centerPanel.add(button);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		add(topPanel, BorderLayout.NORTH); //Añadir el topPanel al norte del borderLayout
		add(centerPanel, BorderLayout.CENTER);
		
		//TextPrompt promptGrettings = new TextPrompt("") 
		
		/*JButton b = new JButton("Button 1");
		JButton b2 = new JButton("Button 2");
		topPanel.add(b);
		topPanel.add(b2); //Agregar los botones al espacio del norte
		
		add(topPanel, BorderLayout.NORTH); //Añadir el topPanel al norte del borderLayout
		
		JButton b3 = new JButton("Button 3"); 
		//add(b3, BorderLayout.CENTER); //Añadir un boton al centro
		
		createCenterPanel();
		
		JButton southButton = new JButton("South");
		add(southButton, BorderLayout.SOUTH);*/
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
	
	public void createCenterPanel() { //Crear el panel central y agregarle otro borderLayout en el cual añadiremos un flowlayout con dos botones
		
		
		
		/*JPanel centerPanel = new JPanel(new BorderLayout());
		
		JPanel CenterSouthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //FlowLayout con dos botones a la derecha del sur del borderLayout
		centerPanel.add(CenterSouthPanel, BorderLayout.SOUTH);
		 
		JButton home = new JButton("Sign in"); //Creacion del primer boton
		CenterSouthPanel.add(home);
		 
		JButton cancel = new JButton("Cancel"); //Creacion del segundo boton
		CenterSouthPanel.add(cancel);
		
		add(centerPanel, BorderLayout.CENTER); //Añadir el panel central (Otro borderLayout) al centro del borderLayout
		*/
	}
}
