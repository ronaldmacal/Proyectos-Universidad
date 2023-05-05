package proyecto2ps19;
import java.io.*;
import java.util.Stack;

/**
 *
 * @author Ronald Macal
 */
public class Proyecto2PS19 {
    public static int noempleados=0;
    public static int idempleados=1001;
    public static int idrepuestos=3001;
    public static int idcliente=7001;
    public static int cantidadcliente=0;
    public static int idservicios=9001;
    public static int cantidadservicios=0;
    public static Stack pilarepuestos=new Stack();
    public static Stack pilafacturas=new Stack();
    public static int cantidadrepuestos=0;
    public static int cantidadfacturas=0;
    public static int idordentrabajo=100001;
    
    
    
    
    //Listas del proyecto
    public static Lista_Empleados empleados=new Lista_Empleados();
    public static Lista_Servicios servicios=new Lista_Servicios();
    public static Lista_Clientes clientes=new Lista_Clientes();
    public static Lista_Automoviles automoviles=new Lista_Automoviles();
    public static Lista_OrdenTrabajo ordentrabajo=new Lista_OrdenTrabajo();
    public static Cola_Espera colaespera=new Cola_Espera();
    public static Cola_Trabajo colatrabajo=new Cola_Trabajo();
    
    
    
    public static void main(String[] args) {
        cargarempleados();
        cargarrepuestos();
        cargarclientes();
        cargarservicios();
        frmInicio inicio=new frmInicio();
        inicio.setVisible(true);
    }
    
    
    public static void cargarrepuestos(){
        //Repuestos
        try {
            FileReader lector=new FileReader("Repuestos.tmr");
            BufferedReader br=new BufferedReader(lector);
            String linea;
            while((linea=br.readLine())!=null){
                linea=Integer.toString(idrepuestos)+"-"+linea;
                idrepuestos++;cantidadrepuestos++;
                pilarepuestos.push(linea);
            }
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public static void cargarempleados(){
        //Empleados
        try {
            //En esta parte se usaron vectores solo para guardar los datos a enviar a las listas
            //Carga de datos al proyecto de los Empleados
            FileReader lector=new FileReader("Empleados.tme");
            BufferedReader br=new BufferedReader(lector);
            String linea;
            while((linea=br.readLine())!=null){
                linea=Integer.toString(idempleados)+"-"+linea;
                //Enviar datos a la listadoblemente enlazada
                empleados.agregarAlInicio(linea);
            }
            lector.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void cargarclientes(){
        //Clientes 
        try {
            FileReader lector=new FileReader("ClienteYAuto.tmca");
            BufferedReader br=new BufferedReader(lector);
            String linea;
            while((linea=br.readLine())!=null){
                linea=Integer.toString(idcliente)+"-"+linea;
                
                //Insertar a la lista de clientes
                clientes.insertar(linea);
                String[] auxiliar=linea.split("-");
                automoviles.insertar(Integer.toString(idcliente-1),auxiliar[5]);
            }
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void cargarservicios(){
        //Servicios
        try {
            FileReader lector=new FileReader("Servicios.tms");
            BufferedReader br=new BufferedReader(lector);
            String linea;
            while((linea=br.readLine())!=null){
                linea=idservicios+"-"+linea;
                //Enviar servicios a la lista
                servicios.agregaralInicio(linea);
            }
            lector.close();
        } catch (Exception e) {
        }
    }
    
}
