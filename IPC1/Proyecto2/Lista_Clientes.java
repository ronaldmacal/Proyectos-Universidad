package proyecto2ps19;
import javax.swing.JOptionPane;

/**
 *
 * @author Ronald Macal
 */
public class Lista_Clientes {
    NodoCircular ultimo;
    public Lista_Clientes(){
        ultimo=null;
    }
    
    public boolean estVacia(){
        return ultimo==null;
    }
    
    public Lista_Clientes insertar(String id){
        NodoCircular nuevo=new NodoCircular(id);
        if(ultimo!=null){
            nuevo.siguiente=ultimo.siguiente;
            ultimo.siguiente=nuevo;
        }
        Proyecto2PS19.idcliente++;Proyecto2PS19.cantidadcliente++;
        Proyecto2PS19.automoviles.insertar(id,"N/A");
        ultimo=nuevo;
        return this;
    }
    
    public String mostrarLista(){
        NodoCircular auxiliar=ultimo.siguiente;
        String cadena="";
        short unavez=0;
        do{
            if(unavez==0){
                cadena=auxiliar.dato;
                unavez=1;
            }else{
                cadena=cadena+"::"+auxiliar.dato;
            }
            auxiliar=auxiliar.siguiente;
        }while(auxiliar!=ultimo.siguiente);
        return cadena;
    }
    
    public boolean modificar(String id){
        boolean modificado=false;
        NodoCircular auxiliar=ultimo.siguiente;
        String cadena="";
        do{
            cadena=auxiliar.dato;
            String[] datos=cadena.split("-");
            if(id.equals(datos[0])){
                String nombre=JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del cliente, el actual es: "+datos[1]);
                String usuario=JOptionPane.showInputDialog(null, "Ingrese el nuevo usuario del cliente, el actual es: "+datos[2]);
                String contra=JOptionPane.showInputDialog(null, "Ingrese la nueva contraseña del cliente, el actual es: "+datos[3]);
                String tipo=JOptionPane.showInputDialog(null, "Ingrese el nuevo tipo del cliente, el actual es: "+datos[4]);
                cadena=id+"-"+nombre+"-"+usuario+"-"+contra+"-"+tipo+"-"+datos[5];
                auxiliar.dato=cadena;
                modificado=true;
            }
            auxiliar=auxiliar.siguiente;
        }while(auxiliar!=ultimo.siguiente);
        return modificado;
    }
    
    public boolean accesoInicio(String usuario, String contra){
        boolean acceso=false;
        NodoCircular auxiliar=ultimo.siguiente;
        String cadena="";
        do{
            cadena=auxiliar.dato;
            String[] datos=cadena.split("-");
            if(usuario.equals(datos[2]) && contra.equals(datos[3])){
                acceso=true;
                frmCliente.idclienteacceso=datos[0];
            }
            auxiliar=auxiliar.siguiente;
        }while(auxiliar!=ultimo.siguiente);
        return acceso;
    }
    public boolean eliminar(String id){
        NodoCircular actual;
        boolean encontrado=false;
        actual=ultimo;String aux;
        while(actual.siguiente!=ultimo && !encontrado){
            aux=actual.siguiente.dato;
            String[] prueba=aux.split("-");
            encontrado=(id.equals(prueba[0]));
            if(!encontrado){
                actual=actual.siguiente;
            }
        }
        aux=actual.siguiente.dato;
        String[] prueba1=aux.split("-");
        encontrado=(id.equals(prueba1[0]));
        
        if(encontrado){
            NodoCircular auxiliar=actual.siguiente;
            if(ultimo==ultimo.siguiente){
                ultimo=null;
            }else{
                if(auxiliar==ultimo){
                    ultimo=actual;
                }
                actual.siguiente=auxiliar.siguiente;
            }
            auxiliar=null;
        }
        return encontrado==true;
    }
}
