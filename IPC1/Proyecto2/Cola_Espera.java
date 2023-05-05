package proyecto2ps19;

/**
 *
 * @author Ronald Macal
 */
public class Cola_Espera {
    NodoCola inicio,fin;
    int tama;
    public Cola_Espera(){
        inicio=fin=null;
        tama=0;
    }
    
    public boolean estVacia(){
        return inicio==null;
    }
    
    public void insertar(String dato){
        NodoCola nuevo=new NodoCola(dato);
        if(estVacia()){
            inicio=nuevo;
        }else{
            fin.siguiente=nuevo;
        }
        fin=nuevo;
        tama++;
    }
    
    public String eliminar(){
        String aux=inicio.dato;
        inicio=inicio.siguiente;
        tama--;
        return aux;
    }
    
    public String mostrar(){
        NodoCola auxiliar=inicio;
        String cadena="";short unavez=0;
        while(auxiliar!=null){
            if(unavez==0){
                cadena=auxiliar.dato;
                unavez=1;
            }else{
                cadena=cadena+":"+auxiliar.dato;
            }
            auxiliar=auxiliar.siguiente;
        }
        return cadena;
    }
    
    public String inicioCola(){
        return inicio.dato;
    }
    
    public int tamaCola(){
        return tama;
    }
}
