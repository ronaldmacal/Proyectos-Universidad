//Pilas
*/
	push -> agregar un dato a la pila
	pop -> quitar el ultimo dato de la pila
	peek -> cual es el ultimo dato de la pila
	empty -> para saber si tiene datos adentro
*/
public static void main(String  args){
	Stack pila=new Stack();
	pila.push(50); //Indice 0
	pila.push("String"); 
	//Solo se puede obtener el ultimo valor
	System.out.println("El ultimo es:"+pila.peek());
	
	while(pila.empty()==false){
		Syste.out.println(pila.pop());
	}
	
	
	
}
//----------------------------------------------------------------
//Colas
public class NodoCola{
	String dato;
	NodoCola siguiente;
	public NodoCola(String d){
		dato=d;
		siguiente=null;
	}

}

public class Cola{
	NodoCola inicio,fin;
	int tama;
	public Cola(){
		inicio=fin=null;
		tama=0;
	}

	public boolean estVacia(){
		retrun inicio==null;
	}

	public void insertar(String ele){
		NodoCola nuevo=new NodoCola(ele);
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

	public String inicioCola(){
		return inicio.dato;
	}

	public int tamaCola(){
		return tama;
	}
}