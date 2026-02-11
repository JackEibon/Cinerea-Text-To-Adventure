package main;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame; //Importamos la libreria JFrame

import views.MyPanel;

public class MyWindow extends JFrame{ //Extendemos a la libreria JFrame
	
	public MyWindow() { //Hacemos un constructor
		Toolkit tk = Toolkit.getDefaultToolkit(); 
		Image myIcon = tk.getImage("src/img/happy.png"); //Obtenemos la ruta de la imagen
		setIconImage(myIcon);
		setTitle("Happy Program");
		
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		
		MyPanel panel = new MyPanel(); //Instanciamos la clase panel que funcionara como un panel dentro de las ventanas
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
