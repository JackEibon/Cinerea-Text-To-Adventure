package main;

import javax.swing.UIManager;

import utils.AppFont;
import views.FormularioRegistro;
import views.FormularioEjemploMaestra;
import views.MyPanel;

public class Main {

	public static void main(String[] args) {
		UIManager.put("Label.font", AppFont.normal());
		
		//Comentado para ver el ejemplo de la maestra
		//FormularioRegistro window = new FormularioRegistro(); //Instanciamos la clase window que funcionara como la ventana donde trabajaremos
		
		FormularioEjemploMaestra window = new FormularioEjemploMaestra(); //Ejemplo
		MyPanel panel = new MyPanel(); //Instanciamos la clase panel que funcionara como un panel dentro de las ventanas
	}

}
