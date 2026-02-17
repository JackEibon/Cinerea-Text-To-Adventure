package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BorderLayoutPanel extends JPanel{
	
	public BorderLayoutPanel() {
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(); //Crear el espacio para el norte del borderlayout
		
		JButton b = new JButton("Button 1");
		JButton b2 = new JButton("Button 2");
		topPanel.add(b);
		topPanel.add(b2); //Agregar los botones al espacio del norte
		
		add(topPanel, BorderLayout.NORTH); //Añadir el topPanel al norte del borderLayout
		
		JButton b3 = new JButton("Button 3"); 
		//add(b3, BorderLayout.CENTER); //Añadir un boton al centro
		
		createCenterPanel();
		
		JButton southButton = new JButton("South");
		add(southButton, BorderLayout.SOUTH);
	}
	
	public void createCenterPanel() { //Crear el panel central y agregarle otro borderLayout en el cual añadiremos un flowlayout con dos botones
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		JPanel CenterSouthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //FlowLayout con dos botones a la derecha del sur del borderLayout
		centerPanel.add(CenterSouthPanel, BorderLayout.SOUTH);
		 
		JButton home = new JButton("Sign in"); //Creacion del primer boton
		CenterSouthPanel.add(home);
		 
		JButton cancel = new JButton("Cancel"); //Creacion del segundo boton
		CenterSouthPanel.add(cancel);
		
		add(centerPanel, BorderLayout.CENTER); //Añadir el panel central (Otro borderLayout) al centro del borderLayout
	}
}
