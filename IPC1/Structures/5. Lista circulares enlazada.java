//Listas circulares
public class NodoLC{
	String dato;
	NodoLC siguiente;

	public NodoLC(String d){
		dato=d;
		siguiente=this;
	}

}
//-----------------------------------------------------------------
public class ListaLC{
	NodoLC ultimo;
	public ListaLC(){
		ultimo=null;
	}

	//Método para saber cuando la lista está vacía
	public boolean estVacia(){
		return ultimo==null;
	}

	//Método para insertar Nodos
	public ListaLC insertar(String elemento){
		NodoLC nuevo=new NodoLC(elemento);
		if(ultimo!=null){
			nuevo.siguiente=ultimo.siguiente;
			ultimo.siguiente=nuevo;
		}
		ultimo=nuevo;
		return this;
	}

	//Método para mostrar la lista
	public void mostrarLista(){
		NodoLC auxiliar=ultimo.siguiente;
		String cadena="";
		do{
			cadena=cadena+"["+auxiliar.dato+"]->";
			auxiliar=auxiliar.siguiente;
		}while(auxiliar!=ultimo.siguiente);
		JOptionPane.showMessageDialog(null,cadena,"Mostrando la lista circular");

	}

	//Método para eliminar un Nodo de la lista circular
	public boolean eliminar(String elemento){
		NodoLC actual;
		boolean encontrado=false;
		actual=ultimo;
		while(actual.siguiente!=ultimo && !encontrado){
			encontrado=(actual.siguiente.dato==elemento)
			if(!encontrado){
				actual=actual.siguiente;
			}
		}

		encontrado=(actual.siguiente.dato==elemento);
		if(encontrado){
			NodoLC auxiliar=actual.siguiente;
			if(ultimo==ultimo.siguiente){
				ultimo=null;
			}else{
				if(auxiliar==ultimo){
					ultimo=actual;
				}
				actual.siguiente=auxiliar.siguiente;
			}
			auxuliar=null;
		}
		return encontrado==true;
	}
}