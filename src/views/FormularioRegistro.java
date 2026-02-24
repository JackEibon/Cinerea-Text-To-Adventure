package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import utils.AppFont;

public class FormularioRegistro extends JFrame{
	  
	public FormularioRegistro() {
		
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Image myIcon = tk.getImage("src/img/happy.png"); //Obtenemos la ruta de la imagen
		setIconImage(myIcon);
		
		/*ImageIcon imageCursor = new ImageIcon("src/img/happy.png");
		Cursor myCursor = tk.createCustomCursor(imageCursor.getImage(), new Point(0,0), "my cursor");
		setCursor(myCursor);*/
		
		setTitle("Log in");
		
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		initializeCompounds();
		
		setVisible(true);
		
	}
	
	public void initializeCompounds() {
		setLayout(new BorderLayout());
		setBackground(new Color(255, 239, 182)); //3, 0, 158
		
		JLabel lblTitle = new JLabel("Log in");
		lblTitle.setFont(AppFont.normal());
		add(lblTitle, BorderLayout.NORTH);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel centerPanel = new JPanel(); //Crear el espacio para el norte del borderlayout
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(35, 62, 35, 62));
		
		JLabel email = new JLabel("EMAIL"); //Label que estara encima del campo de email
		email.setBounds(120, 20, 0, 0);
		centerPanel.add(email);
		JTextField emailText = new JTextField();
		centerPanel.add(emailText, BoxLayout.Y_AXIS);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		JLabel nickname = new JLabel("NICKNAME"); //Label que estara encima del campo de email
		nickname.setBounds(120, 20, 0, 0);
		centerPanel.add(nickname);
		JTextField nicknameText = new JTextField();
		centerPanel.add(nicknameText);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		JLabel password = new JLabel("PASSWORD"); //Label que estara encima del campo de email
		password.setBounds(120, 20, 0, 0);
		centerPanel.add(password);
		JTextField passwordText = new JTextField();
		centerPanel.add(passwordText);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		JLabel confirmPassword = new JLabel("CONFIRM PASSWORD"); //Label que estara encima del campo de email
		confirmPassword.setBounds(120, 20, 0, 0);
		centerPanel.add(confirmPassword);
		JTextField confirmPasswordText = new JTextField();
		centerPanel.add(confirmPasswordText);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,20))); //Espacio
		
		JCheckBox chkAceptoCondiciones = new JCheckBox("Acepto los terminos y condiciones");
		centerPanel.add(chkAceptoCondiciones);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0,50))); //Espacio
		
		JButton button = new JButton("Log In", new ImageIcon("src/img/happy.png")); //Creacion del boton mediante un constructor (Pueden recibir multiples tipos de parametros, por eso es necesario verificar cual es más conveniente
		/*
		 * button.setBackground(Color.ORANGE); //Cambia el color del fondo
		 * button.setForeground(Color.BLACK); //Cambia el color de la letra
		 * button.setFont(new Font("Arial", Font.ITALIC, 10)); //Cambia el estilo de la letra y si siempre va a ser el mismo tipo de letra se puede poner como objeto instanciado desde el inicio
		 */
		initializeButtonImage(button, "../img/happy.png"); //Se utiliza del metodo de abajo
		button.setToolTipText("Te redigira a otra pestaña"); //Añade un texto al pasar el mouse por encima
		button.setBounds(440, 425, 120, 50);
		centerPanel.add(button);
		
		JScrollPane scroll = new JScrollPane(centerPanel);
		scroll.setHorizontalScrollBar(null);
		
		add(centerPanel);
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
}
