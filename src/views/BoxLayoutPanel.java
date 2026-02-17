package views;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoxLayoutPanel extends JPanel{
	
	public BoxLayoutPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Cuando lo creamos le damos una direccion de creacion, en este caso sera esta misma clase
		add(Box.createVerticalGlue());
		add(Box.createHorizontalGlue());
		
		for(int i = 0; i < 3; i++) { //For de botones
			JButton b = new JButton(i + "");
			
			add(b);
			add(Box.createVerticalGlue());
		}
	}
}
