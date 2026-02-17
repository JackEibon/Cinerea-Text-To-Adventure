package views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FlowLayoutPanel extends JPanel {
	 
	 JButton buttons[] = new JButton[10]; //Arreglo de bottones para tener acceso a ellos sin que se borre su direccion de memoria o se sobreponga
	 
	 public FlowLayoutPanel() { //Constructor del FlowLayout
		 setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20)); 
		 for(int i = 0; i < 10; i++) {
			 buttons[i] = new JButton (i + "");
			 add(buttons[i]);
		 }
	 }
}
