package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoxLayoutPanel extends JPanel{
	Font font = new Font("Arial", Font.BOLD, 22);
	Font fontError = new Font("Arial", Font.ITALIC, 14);
	
	public BoxLayoutPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Cuando lo creamos le damos una direccion de creacion, en este caso sera esta misma clase
		add(Box.createVerticalGlue());
		
		
		
		/*for(int i = 0; i < 3; i++) { //For de botones
			JButton b = new JButton(i + "");
			
			add(b);
			add(Box.createVerticalGlue());
		}*/
	}
}
