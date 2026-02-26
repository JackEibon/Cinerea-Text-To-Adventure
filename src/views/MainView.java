package views;

import java.awt.MenuBar;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView extends JFrame{
	public MainView() {
		setSize(500, 500);
		setTitle("Mi Aplicacion");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMenu();
		
		//MyPanel panel = new MyPanel(); //Instanciamos la clase panel que funcionara como un panel dentro de las ventanas
		//FlowLayoutPanel panel = new FlowLayoutPanel(); //Este viene desde el FlowLayoutPanel que esta en views
		//BorderLayoutPanel panel = new BorderLayoutPanel(); //Este viene desde el BorderLayoutPanel que esta en views
		//GridLayoutPanel panel = new GridLayoutPanel(); //Este viene desde el GridLayoutPanel que esta en views
		//BoxLayoutPanel panel = new BoxLayoutPanel(); //Este viene desde el BoxLayoutLPanel que esta en views
		//GridBagLayoutPanel panel = new GridBagLayoutPanel(); //Este viene desde el GridBagLayoutPanel que esta en views
		setVisible(true); 	
	}	
	public void setMenu(){
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		JMenu archivo = new JMenu("Archivo");
		mb.add(archivo);
		archivo.addSeparator();
		JMenuItem abrir = new JMenuItem("Abrir");
		archivo.add(abrir);
		JMenuItem abrir2 = new JMenuItem("Abrir2");
		archivo.add(abrir2);
		JMenu otraOpcion = new JMenu("Otra Opcion");
		mb.add(otraOpcion);
		JMenuItem e = new JMenuItem("Cerrar");
		e.setMnemonic(KeyEvent.VK_S);
		otraOpcion.add(e);	
	}
}