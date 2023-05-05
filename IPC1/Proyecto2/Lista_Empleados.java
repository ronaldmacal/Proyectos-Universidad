package proyecto2ps19;
import javax.swing.JOptionPane;

/**
 *
 * @author Ronald Macal
 */
public class Lista_Empleados {
    private NodoDoble inicio,fin;
    
    public Lista_Empleados(){
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
            Proyecto2PS19.noempleados++;
            Proyecto2PS19.idempleados++;
        }else{
            inicio=fin=new NodoDoble(el);
            Proyecto2PS19.noempleados++;
            Proyecto2PS19.idempleados++;
        }
    }
    
    //Método para mostrar los mecanicos disponibles para trabajar: 
    public int mecanicos(){
        int mecanicos=0;
        if(!estVacia()){
            String datos="";
            NodoDoble auxiliar=inicio;
            while(auxiliar!=null){
                datos=auxiliar.dato;
                String[] datosmecanico=datos.split("-");
                if(datosmecanico[2].equals("mecanico")){
                    mecanicos++;
                }
                auxiliar=auxiliar.siguiente;
            }
        }
        return mecanicos;
    }
    public String nombremecanico(){
        String cadena="";
        if(!estVacia()){
            String datos="";
            NodoDoble auxiliar=inicio;
            short primeravez=0;
            while(auxiliar!=null){
                datos=auxiliar.dato;
                String[] datosmecanico=datos.split("-");
                if(datosmecanico[2].equals("mecanico")){
                    if(primeravez==0){
                        cadena=datosmecanico[1];
                        primeravez=1;
                    }else{
                        cadena=cadena+":"+datosmecanico[1];
                    }
                }
                auxiliar=auxiliar.siguiente;
            }
        }
        
        return cadena;
        
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
            //Mostrar en la tabla los datos
            return datos;
        }
        return "Esta vacía";
    }
        
    //Método de eliminar nodo el la lista    
    public void eliminaValor(String id){  
        if (!estVacia()){
            NodoDoble auxiliar =inicio;
            NodoDoble anterior = null;
            while (auxiliar != null){
                String[] prueba=auxiliar.dato.split("-");
                if (id.equals(prueba[0])){
                    if (anterior == null){
                        inicio = inicio.siguiente;
                        auxiliar.siguiente=null;
                        auxiliar= inicio;
                    }else {
                        anterior.siguiente=auxiliar.siguiente;
                        auxiliar.siguiente=null;
                        auxiliar=anterior.siguiente;
                    }  
                    JOptionPane.showMessageDialog(null, "Dato eliminado con éxito");
                    Proyecto2PS19.noempleados--;
                }else{
                    anterior = auxiliar;
                }
                auxiliar = auxiliar.siguiente;
            }
        }
    }
        
        
    //Modificar datos
    public boolean modificar(String id){
        boolean modificado=false;
        if(!estVacia()){
            String datos="",nombre="",rol="",usuario="",contra="";
            NodoDoble auxiliar=inicio;
            while(auxiliar!=null){
                datos=auxiliar.dato;
                String[] empleados=datos.split("-");
                if(id.equals(empleados[0])){
                    nombre=JOptionPane.showInputDialog("Ingrese el nuevo nombre, el nombre actual es: "+empleados[1]);
                    rol=JOptionPane.showInputDialog("Ingrese el rol Administrador, Mecanico o Receptor, rol actual: "+empleados[2]);
                    usuario=JOptionPane.showInputDialog("Ingrese el usuario, el usuario actual es: "+empleados[3]);
                    contra=JOptionPane.showInputDialog("Ingrese la contraseña nueva, contraseña actual es: "+empleados[4]);
                    datos=id+"-"+nombre+"-"+rol+"-"+usuario+"-"+contra;
                    auxiliar.dato=datos;
                    modificado=true;
                }
                auxiliar=auxiliar.siguiente;
            }
        }
        return modificado;
    }
        
    //Método para ingreso tipo administrador
    public boolean ingresoAdmin(String usuario,String contra){
        boolean ingreso=false;
        if(!estVacia()){
            
            NodoDoble auxiliar=inicio;
            while(auxiliar!=null){
                String[] datos=auxiliar.dato.split("-");
                if(usuario.equals(datos[3]) && contra.equals(datos[4])){
                    ingreso=true;
                }
                auxiliar=auxiliar.siguiente;
            }
        }
        return ingreso;
    }
    //Método para verificar si esta el dato:
    public boolean siEsta(String id){
        boolean existe=false;
        if(!estVacia()){
            String temporal;
            NodoDoble auxiliar=inicio;
            while(auxiliar!=null){
                temporal=auxiliar.dato;
                String[] datos=temporal.split("-");
                if(id.equals(datos[0])){
                     existe=true;
                }
                auxiliar=auxiliar.siguiente;
            }
        }
        return existe;
    }
        
}
