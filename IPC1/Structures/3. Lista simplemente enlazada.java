//Listas simples, nodos y punteros
//----------------------------------------------------------------------------------------------
//Clase nueva nodo
public class Nodo{
	public int dato;
	public Nodo siguiente;
	//Constructor para insertar al final
	public Nodo(int d){
		this.dato=d;
	}
	//Constructor para insertar al inicio
	public Nodo(int d,Nodo n){
		dato=d;
		siguiente=n;
	}

}


//----------------------------------------------------------------------------------------------
//Clase nueva Listas
public class Lista{
	protected Nodo inicio,fin;//Punteros para saber donde esta el inicio y fin
	public Lista(){
		inicio=null;
		fin=null;
	}
	//Método para agregar un nodo al inicio de la lista
	public void agregaralInicio(int elemento){
		//Creando al Nodo
		inicio=new Nodo(elemento,inicio);
		if(fin==null){
			fin=inicio;
		}
	}
	//Método para mostrar los datos
	public void mostrarLista(){
		Nodo recorrer=inicio;
		while(recorrer!=null){
			System.oust.println("["+recorrer.dato+"]");
			recorrer=recorrer.siguiente;
		}
	}
	//Método para eliminar un nodo del inicio
	public int borrarDelInicio(){
		int elemento=inicio.dato;
		if(inicio=fin){
			inicio=null;
			fin=null;
		}else{
			inicio=inicio.siguiente;
		}
		return elemento;
	}
	//Método para eliminar un nodo en especifico
	public void eliminar(int elemento){
		if(!estaVacia()){
			if(inicio==fin && elemeno==inicio.dato){
				inicio=fin=null;
			}else if(elemnto==inicio.dato){
				inicio=inicio.siguiente;
			}else{
				Nodo anterior,temporal;
				anterior=inicio;
				temporal=inicio.siguiente;
				while(temporal!=null && temporal.dato!=elemento){
					anterior=anterior.siguiente;
					temporal=temporal.siguiente;
				}
				if(temporal!=null){
					anterior.siguiente=temporal.siguiente;
					if(temporal==fin){
						fin=anterior;
					}
				}
			}
		}
	}
	//Método para buscar un elemento 
	public boolean estaEnLaLista(int elmento){
		Nodo temporal=inicio;
		while(temporal!=null && temporal.dato!=elemento){
			temporal=temporal.siguiente;
		}
		return temporal!=null;
	}
}



//----------------------------------------------------------------------------------------------
//Método main para menu
public static void main(String[] args) {
	int opcion=0,el=0;
	Lista listita=new Lista();
	do{
		try{
			opcion=Integer.pasrseInt(JOptionPane.showInputDIalog(null,"1. Agregar elemento al inicio de la lista, 2. Mostrar los datos de la lista, 3. Salir"));
			switch(opcion){
				case 1:
					el=Integer.pasrseInt(JOptionPane.showInputDIalog(null,"Ingrese el elemento"));
					listita.agregaralInicio(el);
				case 2:
					listita.mostrarLista;
				case 3:
					el=listita.borrarDelInicio();
					
			}
		}catch(Exception e){

		}
	}
}
