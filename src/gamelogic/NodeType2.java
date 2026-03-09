package gamelogic;
import java.util.List;


/*Nodo tipo 1
 * Autor Eibon
 * Creado el 5/3/2026 a las 1220.
 * Es un tipo de Nodo para un Grafo Dirigido Valorado que los construye teniendo en cuenta referencias con puntero.
 * Un nodo tendra todas sus conexiones apuntando a null hasta que sean apuntado estos valores con algun nodo en 
 * especifico*/
public class NodeType2 {
private int valor;

private List<NodeType2> nodosExistentes;
private List<Integer> costedeArco;
private int[] nodosArcos25= 
{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};	


public Node(int valor){
    this.valor=valor;
}

    public int getValor() {
        return valor;
    }

    public Node getPadre() {
        return padre;
    }

    public Node getHojaD() {
        return hojaD;
    }

    public Node getHojaI() {
        return hojaI;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setPadre(Node padre) {
        this.padre = padre;
    }

    public void setHojaD(Node hojaD) {
        this.hojaD = hojaD;
    }

    public void setHojaI(Node hojaI) {
        this.hojaI = hojaI;
    }
}
