/*
 *    Basado en https://github.com/wayoda/LedControl
 *    A library for controling Leds with a MAX7219/MAX7221
 *    Copyright (c) 2007 Eberhard Fahle
 * 
 *    Permission is hereby granted, free of charge, to any person
 *    obtaining a copy of this software and associated documentation
 *    files (the "Software"), to deal in the Software without
 *    restriction, including without limitation the rights to use,
 *    copy, modify, merge, publish, distribute, sublicense, and/or sell
 *    copies of the Software, and to permit persons to whom the
 *    Software is furnished to do so, subject to the following
 *    conditions:
 * 
 *    This permission notice shall be included in all copies or 
 *    substantial portions of the Software.
 * 
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *    EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *    OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *    NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *    WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *    FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *    OTHER DEALINGS IN THE SOFTWARE.
 */


#include "MatrizLed.h"

//the opcodes for the MAX7221 and MAX7219
#define OP_NOOP   0
#define OP_DIGIT0 1
#define OP_DIGIT1 2
#define OP_DIGIT2 3
#define OP_DIGIT3 4
#define OP_DIGIT4 5
#define OP_DIGIT5 6
#define OP_DIGIT6 7
#define OP_DIGIT7 8
#define OP_DECODEMODE  9
#define OP_INTENSITY   10
#define OP_SCANLIMIT   11
#define OP_SHUTDOWN    12
#define OP_DISPLAYTEST 15

MatrizLed::MatrizLed() {
}

void MatrizLed::begin(int dataPin, int clkPin, int csPin, int numDevices){
    SPI_MOSI=dataPin;
    SPI_CLK=clkPin;
    SPI_CS=csPin;
    rotate = false;
    if(numDevices<=0 || numDevices>8 )
        numDevices=8;
    maxDevices=numDevices;
    pinMode(SPI_MOSI,OUTPUT);
    pinMode(SPI_CLK,OUTPUT);
    pinMode(SPI_CS,OUTPUT);
    digitalWrite(SPI_CS,HIGH);
    SPI_MOSI=dataPin;
    for(int i=0;i<64;i++) 
        status[i]=0x00;
    for(int i=0;i<maxDevices;i++) {
        spiTransfer(i,OP_DISPLAYTEST,0);
        //scanlimit is set to max on startup
        setScanLimit(i,7);
        //decode is done in source
        spiTransfer(i,OP_DECODEMODE,0);
        clearDisplay(i);
        //we go into shutdown-mode on startup
        shutdown(i,true);
    }
    //we have to init all devices in a loop
    for(int address=0; address<getDeviceCount(); address++) {
        /*The MAX72XX is in power-saving mode on startup*/
        shutdown(address,false);
        /* Set the brightness to a medium values */
        setIntensity(address,8);
        /* and clear the display */
        clearDisplay(address);
    }
}

void MatrizLed::rotar(bool r){
    rotate = r;
}

void MatrizLed::rotar_caracter(uint8_t original[8]){
  uint8_t temporal[8] = {0,0,0,0,0,0,0,0};
  for (int i=0; i<8; i++){
    bitWrite(temporal[7], i, bitRead(original[i], 0));
    bitWrite(temporal[6], i, bitRead(original[i], 1));
    bitWrite(temporal[5], i, bitRead(original[i], 2));
    bitWrite(temporal[4], i, bitRead(original[i], 3));
    bitWrite(temporal[3], i, bitRead(original[i], 4));
    bitWrite(temporal[2], i, bitRead(original[i], 5));
    bitWrite(temporal[1], i, bitRead(original[i], 6));
    bitWrite(temporal[0], i, bitRead(original[i], 7));
  }
  memcpy(original, temporal, sizeof(temporal[0])*8);
}

int MatrizLed::getDeviceCount() {
    return maxDevices;
}

void MatrizLed::shutdown(int addr, bool b) {
    if(addr<0 || addr>=maxDevices)
        return;
    if(b)
        spiTransfer(addr, OP_SHUTDOWN,0);
    else
        spiTransfer(addr, OP_SHUTDOWN,1);
}

void MatrizLed::setScanLimit(int addr, int limit) {
    if(addr<0 || addr>=maxDevices)
        return;
    if(limit>=0 && limit<8)
        spiTransfer(addr, OP_SCANLIMIT,limit);
}

void MatrizLed::setIntensity(int addr, int intensity) {
    if(addr<0 || addr>=maxDevices)
        return;
    if(intensity>=0 && intensity<16)	
        spiTransfer(addr, OP_INTENSITY,intensity);
}

void MatrizLed::clearDisplay(int addr) {
    int offset;

    if(addr<0 || addr>=maxDevices)
        return;
    offset=addr*8;
    for(int i=0;i<8;i++) {
        status[offset+i]=0;
        spiTransfer(addr, i+1,status[offset+i]);
    }
}

void MatrizLed::setLed(int addr, int row, int column, boolean state) {
    int offset;
    byte val=0x00;

    if(addr<0 || addr>=maxDevices)
        return;
    if(row<0 || row>7 || column<0 || column>7)
        return;
    offset=addr*8;
    val=B10000000 >> column;
    if(state)
        status[offset+row]=status[offset+row]|val;
    else {
        val=~val;
        status[offset+row]=status[offset+row]&val;
    }
    spiTransfer(addr, row+1,status[offset+row]);
}

void MatrizLed::setRow(int addr, int row, byte value) {
    int offset;
    if(addr<0 || addr>=maxDevices)
        return;
    if(row<0 || row>7)
        return;
    offset=addr*8;
    status[offset+row]=value;
    spiTransfer(addr, row+1,status[offset+row]);
}

void MatrizLed::setColumn(int addr, int col, byte value) {
    byte val;

    if(addr<0 || addr>=maxDevices)
        return;
    if(col<0 || col>7) 
        return;
    for(int row=0;row<8;row++) {
        val=value >> (7-row);
        val=val & 0x01;
        setLed(addr,row,col,val);
    }
}

void MatrizLed::spiTransfer(int addr, volatile byte opcode, volatile byte data) {
    //Create an array with the data to shift out
    int offset=addr*2;
    int maxbytes=maxDevices*2;

    for(int i=0;i<maxbytes;i++)
        spidata[i]=(byte)0;
    //put our device data into the array
    spidata[offset+1]=opcode;
    spidata[offset]=data;
    //enable the line 
    digitalWrite(SPI_CS,LOW);
    //Now shift out the data 
    for(int i=maxbytes;i>0;i--)
        shiftOut(SPI_MOSI,SPI_CLK,MSBFIRST,spidata[i-1]);
    //latch the data onto the display
    digitalWrite(SPI_CS,HIGH);
}    


void MatrizLed::borrar(){
    for(int address=0;address<getDeviceCount();address++) {
        clearDisplay(address);
    }
}

void MatrizLed::escribirCaracter(char caracter, int posicion)
{
    int posicion_caracter;
    // por defecto un asterisco
    uint8_t codigocaracter[]= {B00000000, B00001000, B00101010, B00011100, B01110111, B00011100, B00101010, B00001000};

    if (caracter >= 'A' && caracter <= 'Z'){
        posicion_caracter = ((int)caracter - 'A') * 8;
        for(int i=0; i<8; i++){
            codigocaracter[i]=pgm_read_byte_near(tablaCaracteresMayuscula+i+posicion_caracter);
        }
    }
    else if (caracter >= 'a' && caracter <= 'z'){
        posicion_caracter = ((int)caracter - 'a') * 8;
        for(int i=0; i<8; i++){
            codigocaracter[i]=pgm_read_byte_near(tablaCaracteresMinuscula+i+posicion_caracter); 
        }
    }
    else if (caracter >= '0' && caracter <= '9'){
        posicion_caracter = ((int)caracter - '0') * 8;
        for(int i=0; i<8; i++){
            codigocaracter[i]=pgm_read_byte_near(tablaNumeros+i+posicion_caracter); 
        }
    }
    else if (caracter == ' '){
        for(int i=0; i<8; i++){
            codigocaracter[i]= B00000000; 
        }
    }else if (caracter == '*'){
        codigocaracter[0] = B00000000;
        codigocaracter[1] = B00001000;
        codigocaracter[2] = B00101010;
        codigocaracter[3] = B00011100; 
        codigocaracter[4] = B01110111;
        codigocaracter[5] = B00011100;
        codigocaracter[6] = B00101010;
        codigocaracter[7] = B00001000;
    }else if (caracter == '#'){
        codigocaracter[0] = B00000000;
        codigocaracter[1] = B00100100;
        codigocaracter[2] = B01111110;
        codigocaracter[3] = B01111110;
        codigocaracter[4] = B00100100;
        codigocaracter[5] = B01111110;
        codigocaracter[6] = B01111110;
        codigocaracter[7] = B00100100;
    }else if (caracter == '-'){
        codigocaracter[0]= B00000000;
        codigocaracter[1]= B00111000;
        codigocaracter[2]= B00111000;
        codigocaracter[3]= B00111000;
        codigocaracter[4]= B00111000;
        codigocaracter[5]= B00111000;
        codigocaracter[6]= B00111000;
        codigocaracter[7]= B00000000;
    }else if (caracter == '_'){
        codigocaracter[0]= B00000000;
        codigocaracter[1]= B11000000;
        codigocaracter[2]= B11000000;
        codigocaracter[3]= B11000000;
        codigocaracter[4]= B11000000;
        codigocaracter[5]= B11000000;
        codigocaracter[6]= B11000000;
        codigocaracter[7]= B00000000;
    }
    // if (rotate)
    //     rotar_caracter(codigocaracter);
    if (!rotate){
        for(int i=0; i<8; i++){
            int address = 0;
            int posendisplay = posicion + i;
            while (posendisplay > 7){
                address++;
                posendisplay -= 8;
            }
            if (address > getDeviceCount() -1 )
                return;
            setRow(address, posendisplay, codigocaracter[i]);
            
        } 
    }
    else{
            posicion = ((getDeviceCount()-1)*8)-posicion-1;
            for(int i=7; i>=0; i--){
                int address = getDeviceCount()-1;
                if(getDeviceCount() == 1) address = getDeviceCount();
                int posendisplay = posicion - i;
                while (posendisplay < 0){
                    address--;
                    posendisplay += 8;
                }
                if (address < -1)
                    return;
                setColumn(address, posendisplay, codigocaracter[i]);
            }
    }
}

void MatrizLed::escribirFrase(const char* frase){
    escribirFrase(frase, 0);
}

void MatrizLed::escribirFrase(const char* frase, int posicion){
    for (size_t i=0; i < strlen(frase); i++){
        if(interruptWork){
                break;
        }
        escribirCaracter(frase[i], (i*8)+posicion);
    }
}

void MatrizLed::escribirFraseInvertida(const char* frase){
    escribirFraseInvertida(frase, 0);
}

void MatrizLed::escribirFraseInvertida(const char* frase, int posicion){
    for (size_t i=strlen(frase); i > 0; i--){
        if(interruptWork){
                break;
        }
        escribirCaracter(frase[strlen(frase)-i], -(i*8)+posicion);
    }
}

void MatrizLed::escribirCifra(int cifra, int posicion){
    working = true;
    char formateado[getDeviceCount()+1]; // Array donde se almacenará el valor formateado (un caracter por pantalla caracteres mas fin de linea)
    // sprintf con longitud dinamica no funciona en arduino :(
    // sprintf(formateado, "%.*d", getDeviceCount(), cifra); // guardamos el valor del contador, formateado con dos espacios, como cadena de texto
    dtostrf(cifra, getDeviceCount(), 0, formateado);
    escribirFrase(formateado, posicion);
    working = false;
}

void MatrizLed::escribirCifra(int cifra){
    escribirCifra(cifra, 0);
}

void MatrizLed::interrumpir(){
    if(working){
        interruptWork = true;
    }else{
        borrar();
    }
}

void MatrizLed::escribirCharPorChar(const char* frase, unsigned long pausa, int ax_input){
    working = true;
    int inicio = 1 * 8;
    int npasos = strlen(frase) * 8;
    int original_pausa = pausa;
    for(int i = 0; i<strlen(frase); i++){
            if(interruptWork){
                borrar();
                interruptWork = false;
                working = false;
                break;
            }
            escribirCaracter(frase[i],0);
            // Codigo para controlar velocidad lo mas suave posible
            if(((analogRead(ax_input)-100)*2) > original_pausa){
                if(((analogRead(ax_input)-100)*2-50) > original_pausa){
                    if(((analogRead(ax_input)-100)*2-100) > original_pausa){
                        pausa = 10;
                    }else{
                        pausa = original_pausa - ((analogRead(ax_input)-100)*2-100);
                    }
                }else{
                    pausa = original_pausa - ((analogRead(ax_input)-100)*2-50);
                }
            }else{
                pausa = original_pausa - ((analogRead(ax_input)-100)*2);
            }

            // Revisar si se indico el uso de un speed display
            if(sd_a != -1){
                setDisplayValue(((analogRead(ax_input)-100)/200)+1);
            }
            delay(pausa);
        }
        working = false;  
}

void MatrizLed::escribirFraseScroll(const char* frase, unsigned long pausa, int ax_input){
    working = true;
    int inicio = getDeviceCount() * 8;
    int npasos = strlen(frase) * 8;
    int original_pausa = pausa;
    for(int i = inicio; i > -npasos; i--){
        escribirFrase(frase, i);
        if(interruptWork){
                borrar();
                working = false;
                interruptWork = false;
                break;
        }
        // Codigo para controlar velocidad lo mas suave posible
        if(((analogRead(ax_input)-100)*2) > original_pausa){
            if(((analogRead(ax_input)-100)*2-50) > original_pausa){
                if(((analogRead(ax_input)-100)*2-100) > original_pausa){
                    pausa = 10;
                }else{
                    pausa = original_pausa - ((analogRead(ax_input)-100)*2-100);
                }
            }else{
                pausa = original_pausa - ((analogRead(ax_input)-100)*2-50);
            }
        }else{
            pausa = original_pausa - ((analogRead(ax_input)-100)*2);
        }

        // Revisar si se indico el uso de un speed display
        if(sd_a != -1){
            setDisplayValue(((analogRead(ax_input)-100)/200)+1);
        }

        delay(pausa);
    }    
    working = false;
}

void MatrizLed::escribirFraseScrollInvertido(const char* frase, unsigned long pausa, int ax_input){
    working = true;
    int inicio = getDeviceCount() * 8;
    int npasos = strlen(frase) * 8;
    int original_pausa = pausa;
    for(int i = 0; i < npasos; i++){
        escribirFraseInvertida(frase, i);
        if(interruptWork){
                borrar();
                working = false;
                interruptWork = false;
                break;
        }
        // Codigo para controlar velocidad lo mas suave posible
        if(((analogRead(ax_input)-100)*2) > original_pausa){
            if(((analogRead(ax_input)-100)*2-50) > original_pausa){
                if(((analogRead(ax_input)-100)*2-100) > original_pausa){
                    pausa = 10;
                }else{
                    pausa = original_pausa - ((analogRead(ax_input)-100)*2-100);
                }
            }else{
                pausa = original_pausa - ((analogRead(ax_input)-100)*2-50);
            }
        }else{
            pausa = original_pausa - ((analogRead(ax_input)-100)*2);
        }

        // Revisar si se indico el uso de un speed display
        if(sd_a != -1){
            setDisplayValue(((analogRead(ax_input)-100)/200)+1);
        }

        delay(pausa);
    }
    working = false;
}

void MatrizLed::setSpeedDisplayPins(int sd_a, int sd_b, int sd_c, int sd_d, int sd_e, int sd_f, int sd_g){
    this->sd_a = sd_a;
    this->sd_b = sd_b;
    this->sd_c = sd_c;
    this->sd_d = sd_d;
    this->sd_e = sd_e;
    this->sd_f = sd_f;
    this->sd_g = sd_g;
}

void MatrizLed::setDisplayValue(int value){
  switch(value){
    case 1:
      digitalWrite(sd_a, LOW);
      digitalWrite(sd_b, HIGH);
      digitalWrite(sd_c, HIGH);
      digitalWrite(sd_d, LOW);
      digitalWrite(sd_e, LOW);
      digitalWrite(sd_f, LOW);
      digitalWrite(sd_g, LOW);
      break;
    case 2:
      digitalWrite(sd_a, HIGH);
      digitalWrite(sd_b, HIGH);
      digitalWrite(sd_c, LOW);
      digitalWrite(sd_d, HIGH);
      digitalWrite(sd_e, HIGH);
      digitalWrite(sd_f, LOW);
      digitalWrite(sd_g, HIGH);
      break;
    case 3:
      digitalWrite(sd_a, HIGH);
      digitalWrite(sd_b, HIGH);
      digitalWrite(sd_c, HIGH);
      digitalWrite(sd_d, HIGH);
      digitalWrite(sd_e, LOW);
      digitalWrite(sd_f, LOW);
      digitalWrite(sd_g, HIGH);
      break;
    case 4:
      digitalWrite(sd_a, LOW);
      digitalWrite(sd_b, HIGH);
      digitalWrite(sd_c, HIGH);
      digitalWrite(sd_d, LOW);
      digitalWrite(sd_e, LOW);
      digitalWrite(sd_f, HIGH);
      digitalWrite(sd_g, HIGH);
      break;
    case 5:
      digitalWrite(sd_a, HIGH);
      digitalWrite(sd_b, LOW);
      digitalWrite(sd_c, HIGH);
      digitalWrite(sd_d, HIGH);
      digitalWrite(sd_e, LOW);
      digitalWrite(sd_f, HIGH);
      digitalWrite(sd_g, HIGH);
      break;
  }
}

void MatrizLed::setIntensidad(int intensidad){
    for(int address=0; address<getDeviceCount(); address++) {
        setIntensity(address, intensidad);
    }
}

void MatrizLed::apagar(){
    for(int address=0; address<getDeviceCount(); address++) {
        shutdown(address, true);
    }
}

void MatrizLed::encender(){
    for(int address=0; address<getDeviceCount(); address++) {
        shutdown(address, false);
    }
}
