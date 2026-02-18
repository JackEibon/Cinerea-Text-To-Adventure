package main;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame; //Importamos la libreria JFrame

import views.BorderLayoutPanel;
import views.BoxLayoutPanel;
import views.FlowLayoutPanel;
import views.GridLayoutPanel;
import views.MyPanel;

public class MyWindow extends JFrame{ //Extendemos a la libreria JFrame
	
	public MyWindow() { //Hacemos un constructor
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Image myIcon = tk.getImage("src/img/happy.png"); //Obtenemos la ruta de la imagen
		setIconImage(myIcon);
		
		/*ImageIcon imageCursor = new ImageIcon("src/img/happy.png");
		Cursor myCursor = tk.createCustomCursor(imageCursor.getImage(), new Point(0,0), "my cursor");
		setCursor(myCursor);*/
		
		setTitle("Happy Program");
		
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//MyPanel panel = new MyPanel(); //Instanciamos la clase panel que funcionara como un panel dentro de las ventanas
		//FlowLayoutPanel panel = new FlowLayoutPanel(); //Este viene desde el FlowLayoutPanel que esta en views
		BorderLayoutPanel panel = new BorderLayoutPanel(); //Este viene desde el BorderLayoutPanel que esta en views
		//GridLayoutPanel panel = new GridLayoutPanel(); //Este viene desde el GridLayoutPanel que esta en views
		//BoxLayoutPanel panel = new BoxLayoutPanel(); //Este viene desde el BoxLayoutLPanel que esta en views
		//GridBagLayoutPanel panel = new GridBagLayoutPanel(); //Este viene desde el GridBagLayoutPanel que esta en views
		add(panel);
		
		setVisible(true); //Hacemos visible la ventana con setVisible, se puede agregar en el main con frame. o el constructor (es preferible que se ponga al final del constructor)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cierre automatico al cerrar la ventana, así no quedan abiertas en segundo plano cuando se cierran.
		
		/* 
		 * setSize(500,550) le damos un tamaño a la ventana con setSize (ancho y alto en pixeles)
		 * setLocation(x,y) para modificar la localizacion inicial de nuestra ventana
		 * setBounds(x,y,width, height) para modificar el tamaño y localizacion de la ventana (reemplaza setSize y setLocation)
		 * setIconImage(Image image) para agregar una imagen de icono en nuestra ventana
		 * setTitle(String title) para ponerle un titulo a la ventana
		 * setResizable(Boolean resizable) para permitir o no la modificacion del tamaño de la ventana
		 * setLocationRelativeTo(Component c) le damos un componente para que se coloque ahi, si le damos null como parametro se pone en el centro.
		 * setVisible(true) hacemos visible la ventana con setVisible, se puede agregar en el main con frame. o el constructor (es preferible que se ponga al final del constructor)
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) cierre automatico al cerrar la ventana, así no quedan abiertas en segundo plano cuando se cierran.
		 * setPreferredSize(Dimension d) panel.setPreferredSize(new Dimension(200, 200)) para ajustar el tamaño de botones o componentes en paneles que lo permitan.
		 * 
		 * Para el icono se usan estas lineas de codigo:
		 * Toolkit tk = Toolkit.getDefaultToolkit();
		 * Image myIcon = tk.getImage("src/image.icon");
		 * SetIconImage(myIcon);
		 * 
		 * add(Component c) se añade el componente a la ventana
		 */
	}
	
}
