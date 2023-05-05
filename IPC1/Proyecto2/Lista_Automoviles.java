package proyecto2ps19;

/**
 *
 * @author Ronald Macal
 */
public class Lista_Automoviles {
    NodoCircular ultimo;
    public Lista_Automoviles(){
        ultimo=null;
    }
    
    public boolean estVacia(){
        return ultimo==null;
    }
    public boolean agregarauto(String id,String cadena){
        //Agregar un auto a un cliente existente
        boolean autoagregado=false;
        NodoCircular auxiliar=ultimo.siguiente;
        String prueba;
        do{
            prueba=auxiliar.dato;
            String[] aux=prueba.split("-");
            if(id.equals(aux[0])){
                if(aux[1].equals("N/A")){
                    eliminar(id);
                    insertar(id, cadena);
                }else{
                    cadena=aux[1]+";"+cadena;
                    eliminar(id);
                    insertar(id, cadena);
                }
                autoagregado=true;
            }
            auxiliar=auxiliar.siguiente;
        }while(auxiliar!=ultimo.siguiente);
        
        return autoagregado;
    }
    
    public Lista_Automoviles insertar(String id,String auto){
        NodoCircular nuevo=new NodoCircular(id+"-"+auto);
        if(ultimo!=null){
            nuevo.siguiente=ultimo.siguiente;
            ultimo.siguiente=nuevo;
        }
        ultimo=nuevo;
        return this;
    }
    //Método para comprobar si existe el id que busca
    public boolean siEsta(String id){
        boolean existe=false;
        NodoCircular auxiliar=ultimo.siguiente;
        String prueba;
        do{
           prueba=auxiliar.dato;
           String[] aux=prueba.split("-");
           if(id.equals(aux[0])){
               existe=true;
           }
           auxiliar=auxiliar.siguiente;
        }while(auxiliar!=ultimo.siguiente);
        
        return existe;
    }
    
    public String mostrarAutos(String id){
        NodoCircular auxiliar=ultimo.siguiente;
        String cadena="",prueba;
        do{
            prueba=auxiliar.dato;
            String[] aux=prueba.split("-");
            if(id.equals(aux[0])){
                cadena=auxiliar.dato;
            }
            auxiliar=auxiliar.siguiente;
        }while(auxiliar!=ultimo.siguiente);
        return cadena;
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
