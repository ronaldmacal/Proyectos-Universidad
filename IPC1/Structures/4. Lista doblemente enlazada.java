//Lista doblemente enlazada
//Clase de creacion de nodos
public class NodoDoble{
	public int dato;
	NodoDoble siguiente, anterior;
	//Constructor para cuando no hay nodos
	public NodoDoble(int el){
		this(el,null,null);
	}
	//Constructor para cuando ya hay nodos
	public NodoDoble(int el,NodoDoble s, NodoDoble a){
		dato=el;
		siguiente=s;
		anterior=a;
	}
}

//Clase de la lista doblemente enlazada
public class ListaDoble{
	private NodoDoble inicio,fin;
	public ListaDoble(){
		inicio=fin=null;
	}
	//Método para saber cuando la lista está vacía
	public boolean estVacia(){
		return inicio==null;
	}
	//Método para agregar nodos al final
	public void agregarAlFinal(int el){
		if(!estVacia()){
			fin=new NodoDoble(el,null,fin);
			fin.anterior.siguiente=fin;
		}else{
			inicio=fin=new NodoDoble(el);
		}
	}
	//Método agregar al inicio
	public void agregarAlInicio(int el){
		if(!estVacia()){
			inicio=new NodoDoble(el,inicio,null);
			inicio.siguiente.anterior=inicio;
		}else{
			inicio=fin=new NodoDoble(el);
		}
	}
	//Método para mostrar la lista de inicio a fin
	public void mostrarListaInicioFin(){
		if(!estVacia()){
			String datos="<->";
			NodoDoble auxiliar=inicio;
			while(auxiliar!=null){
				datos=datos+"["+auxiliar.dato+"]";
				auxiliar=auxiliar.siguiente;
			}
			JOptionPane.showMessageDialog(null,datos,"Mostrando lista de inicio a fin",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//Método para eliminar del inicio
	public int eliminardeInicio(){
		int elemento=inicio.dato;
		if (inicio==fin) {
			inicio=fin=null;
		}else{
			inicio=inicio.siguiente;
			inicio.aterior=null;
		}
		return elemento;
	}
	//Método para eliminar del final
	public int eliminardeFinal(){
		int elemento=fin.dato;
		if (inicio==fin) {
			inicio=fin=null;
		}else{
			fin=fin.anterior;
			fin.siguiente=null;
		}
		return elemento;
	}

public void eliminaValor(Integer valor){
    if (inicio != null){
        NodoDoble aux = inicio;
        NodoDoble ant = null;
        while (aux != null){
            if (aux.getDato() == valor ){
                if (ant == null){
                    inicio = inicio.getSiguiente();
                    aux.setSiguiente(null);
                    aux= inicio;
                }else {
                    ant.setSiguiente(aux.getSiguiente());
                    aux.setSiguiente(null);
                    aux = ant.getSiguiente();
                }                                             
            }else{
                ant = aux;
                aux = aux.getSiguiente();
            }
        }
    }
}

}


//Lo copiado
boolean eliminado=false;
            if(!estVacia()){
                NodoDoble auxiliar=inicio;
                if(inicio==fin && id.equals(auxiliar.dato)){
                    inicio=fin=null;
                }else if(id==inicio.dato){
                    inicio=inicio.siguiente;
                }else{
                    NodoDoble anterior,temporal;
                    anterior=inicio;
                    temporal=inicio.siguiente;
                    while(temporal!=null && temporal.dato!=id){
                        anterior=anterior.siguiente;
                        temporal=temporal.siguiente;
                    }
                    if(temporal!=null){
                        anterior.siguiente=temporal.siguiente;
                        if(temporal==fin){
                            fin=anterior;
                            eliminado=true;
                        }
                    }
                }
            }
            return eliminado;