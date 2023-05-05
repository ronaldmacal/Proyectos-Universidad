package proyecto2ps19;

/**
 *
 * @author Ronald Macal
 */
public class NodoDoble {
    public String dato;
    NodoDoble siguiente, anterior;
    //Constructor para cuando no hay nodos
    public NodoDoble(String el){
  	this(el,null,null);
    }
    //Constructor para cuando ya hay nodos
    public NodoDoble(String el,NodoDoble s, NodoDoble a){
	dato=el;
	siguiente=s;
	anterior=a;
    }
}
