package views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridLayoutPanel extends JPanel{

	public GridLayoutPanel(){ //Constructor del GridLayout
		setLayout(new GridLayout(3,3,10,10)); //De 3x3
		
		for(int i = 0; i < 9; i++) { //Un for de botones para llenarlo
			JButton b = new JButton(i + "");
			add(b);
		}
	}
}
