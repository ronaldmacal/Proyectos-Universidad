//Codigo Archivos o ficheros en Java 
import java.io.*;

//Parte 1
//Metodo Main
	Leer_fichero accediendo=new Leer_fichero;
	accediendo.leer();

public class Leer_fichero{
	public void lee(){
		try{
			//Se crea el fichero llamado ejemplo.
			FileReader entrada=new FileReader("Ejemplo.txt");
			int c=entrada.read();
			//Mientras no haya llegado al final del archivo
			while(c!=-1){
				c=entrada.read();
				char letra=(char)c;
				System.out.print(letra);
			}
			System.out.println(letra);
			entrada.close();
		}catch(IOException e){
			System.out.println("No se ha encontrado el archivo");
		} 	
	}	
}

class Escribiendo{
	public void escribir(){
		String frase="Esto es una prueba de escritura");
		try{
			FileWriter escritura=new FileWriter("texto_nuevo.txt");
			for(int i=0;i<frase.length();i++){
				escritura.write(frase.charAt(i));
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}