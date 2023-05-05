package proyecto2ps19;

import javax.swing.JOptionPane;

/**
 *
 * @author Ronald Macal
 */
public class Lista_Servicios {
    protected Nodo inicio,fin;
    public Lista_Servicios(){
        inicio=null;
        fin=null;
    }
    
    public void agregaralInicio(String elemento){
        inicio=new Nodo(elemento,inicio);
        if(fin==null){
            fin=inicio;
        }
        Proyecto2PS19.idservicios++;
        Proyecto2PS19.cantidadservicios++;
    }
    
    public String mostrarLista(){
        Nodo recorrer=inicio;
        String datos="";
        short unavez=0;
        while(recorrer!=null){
            if(unavez==0){
                datos=recorrer.dato;
                unavez=1;
            }else{
                datos=datos+"::"+recorrer.dato;
            }
            recorrer=recorrer.siguiente;
        }
        return datos;
        
    }
    
    public boolean estVacia(){
        return inicio==null;
    }
    
    
    
    public void modificar(String id){
        Nodo recorrer=inicio;
        String datos="";
        while(recorrer!=null){
            String[] dato=recorrer.dato.split("-");
            if(id.equals(dato[0])){
                String nombre,marca,modelo,listarepuesto,preciomano,preciototal;
                nombre=JOptionPane.showInputDialog("Ingrese el nombre del servicio, el servicio actual es: "+dato[1]);
                marca=JOptionPane.showInputDialog("Ingrese la marca del servicio, el  actual es: "+dato[2]);
                modelo=JOptionPane.showInputDialog("Ingrese el modelo del servicio, el  actual es: "+dato[3]);
                listarepuesto=JOptionPane.showInputDialog("Ingrese la lista de repuestos del servicio, el  actual es: "+dato[4]);
                preciomano=JOptionPane.showInputDialog("Ingrese el precio mano de obra del servicio, el  actual es: "+dato[5]);
                preciototal=JOptionPane.showInputDialog("Ingrese el precio total del servicio, el  actual es: "+dato[6]);
                datos=id+"-"+nombre+"-"+marca+"-"+modelo+"-"+listarepuesto+"-"+preciomano+"-"+preciototal;
                recorrer.dato=datos;
                
            }
            recorrer=recorrer.siguiente;
        }
    }
    
    public void eliminar(String id){
        if(!estVacia()){
            String[] aux=inicio.dato.split("-");
            System.out.println("1. ->"+aux[0]);
            String prueba=aux[0];
            if(inicio==fin && id.equals(prueba)){
                inicio=fin=null;
            }else if(id.equals(prueba)){
                inicio=inicio.siguiente;
            }else{
                Nodo anterior,temporal;
                anterior=inicio;
                temporal=inicio.siguiente;
                String[] aux1=temporal.dato.split("-");
                prueba=aux1[0];
                while(temporal!=null && prueba!=id){
                    anterior=anterior.siguiente;
                    temporal=temporal.siguiente;
                    String[] aux2=temporal.dato.split("-");
                    prueba=aux2[0];
                }
                if(temporal!=null){
                    anterior.siguiente=temporal.siguiente;
                    if(temporal==fin){
                        fin=anterior;
                    }
                    JOptionPane.showMessageDialog(null,"Servicio "+id+" eliminado con éxito");
                    Proyecto2PS19.cantidadservicios--;
                }
            }
            
        }
    }
}
