package proyecto2ps19;

/**
 *
 * @author Ronald Macal
 */
public class Nodo {
    public String dato;
    public Nodo siguiente;
    public Nodo(String d){
        this.dato=d;
    }
    
    public Nodo(String d,Nodo n){
        dato=d;
        siguiente=n;
    }
}
