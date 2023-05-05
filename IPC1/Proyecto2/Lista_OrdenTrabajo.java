package proyecto2ps19;
import javax.swing.JOptionPane;

/**
 *
 * @author Ronald Macal
 */
public class Lista_OrdenTrabajo {
    private NodoDoble inicio,fin;
    
    public Lista_OrdenTrabajo(){
	inicio=fin=null;
    }
    //Método para saber cuando la lista está vacía
    public boolean estVacia(){
	return inicio==null;
    }
	
    //Método agregar al inicio
    public void agregarAlInicio(String el){
	if(!estVacia()){
            inicio=new NodoDoble(el,inicio,null);
            inicio.siguiente.anterior=inicio;
        }else{
            inicio=fin=new NodoDoble(el);
        }
    }
    public boolean siEsta(String orden){
        boolean siesta=false;
        if(!estVacia()){
            NodoDoble auxiliar=inicio;
            String datos="";
            while(auxiliar!=null){
                datos=auxiliar.dato;
                String[] buscar=datos.split("-");
                if(orden.equals(buscar[0])){
                    siesta=true;
                }
                auxiliar=auxiliar.siguiente;
            }
        }
        return siesta;
    }    
    //Método para mostrar la lista de inicio a fin
    public String mostrarListaInicioFin(){
        if(!estVacia()){
            String datos="";
            short unavez=0;
            NodoDoble auxiliar=inicio;
            while(auxiliar!=null){
                if(unavez==0){
                    datos=auxiliar.dato;
                    unavez=1;
                }else{
                    datos=datos+"::"+auxiliar.dato;
                }
                auxiliar=auxiliar.siguiente;
            }
            return datos;
        }
        return "Esta vacía";
    }

    //Método para verificar si esta el dato:
    public String datosOrden(String orden){
        String cadena="";
        if(!estVacia()){
            String temporal;
            NodoDoble auxiliar=inicio;
            while(auxiliar!=null){
                temporal=auxiliar.dato;
                String[] datos=temporal.split("-");
                if(orden.equals(datos[0])){
                    cadena=orden+":"+datos[1]+":"+datos[2];
                }
                auxiliar=auxiliar.siguiente;
            }
        }
        return cadena;
    }
    public String facturardatos(String orden){
        String cadena="";
        if(!estVacia()){
            String temporal;
            NodoDoble auxiliar=inicio;
            while(auxiliar!=null){
                temporal=auxiliar.dato;
                String[] datos=temporal.split("-");
                if(orden.equals(datos[0])){
                    cadena=orden+"-"+datos[1]+"-"+datos[2]+"-"+datos[3];
                }
                auxiliar=auxiliar.siguiente;
            }
        }
        return cadena;
    }
    
}
