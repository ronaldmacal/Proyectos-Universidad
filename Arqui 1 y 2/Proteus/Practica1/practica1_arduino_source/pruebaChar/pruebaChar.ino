#include <MatrizLed.h>

/*
 * Varios ejemplos
 */

MatrizLed pantalla;

void setup() {
  Serial1.begin(9600);
  pantalla.begin(10, 8, 9, 1); // dataPin, clkPin, csPin, numero de matrices de 8x8
  pantalla.rotar(true);
}

void loop() { 
  // Controlar LEDs independientes
  pantalla.borrar();
  pantalla.escribirCaracter('O' , 0); // Caracter, posicion en la pantalla
  delay(2000);

}
