package proyecto2ps19;

/**
 *
 * @author Ronald Macal
 */
public class NodoCircular {
    String dato;
    NodoCircular siguiente;
    
    public NodoCircular(String dat){
        dato=dat;
        siguiente=this;
    }
}
