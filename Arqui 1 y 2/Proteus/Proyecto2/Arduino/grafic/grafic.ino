#include <MatrizLed.h>
int matriz_data_in = 10;
int matriz_load = 9;
int matriz_clock = 8;

int xbase0 = 0;
int xbase1 = 0;
int xbase2 = 0;
int xbase3 = 0;
int xbase4 = 0;

String funcion = "5x^4-16x^3+3x^2+4x+99";
int actualNum = 0;
int actualPot = 0;

int maxY = 8;
int maxX = 8;

int minY = 0;
int minX = 0;

int lx = 0;
int ly = 0;
MatrizLed pantalla;

void setup() {
  Serial.begin(9600);
  pantalla.begin(matriz_data_in, matriz_clock, matriz_load, 4);
  pantalla.rotar(true);
}

void loop() {
  pantalla.borrar();
  funcion = "";
  while(true){
      if(Serial.available()){
        char c=Serial.read();
        if(c=='$') break;
        funcion+=c;
      }
  }
  pantalla.borrar();
  graphicFunc();
  delay(2500);
}

void graphicFunc(){
  converToVars();
  int rMin = -50;
  int rMax = 50;

  if(xbase4 > 0){
    rMin = -8;
    rMax = 8;
  }else if(xbase3 > 0){
    rMin = -10;
    rMax = 10;
  }else if(xbase2 > 0){
    rMin = -15;
    rMax = 15;
  }

  if(isConstant()){
    maxY = xbase0+3;
    minY = xbase0-3;
    
    maxX = rMax;
    minX = rMin;
  }else{
    if(xbase2 == 0 && xbase3 == 0 && xbase4 == 0){
      maxY = 
      xbase0+xbase1*rMax
      +pow(xbase2*rMax,2)
      +pow(xbase3*rMax,3)
      +pow(xbase4*rMax,4);

      minY = 
      xbase0+xbase1*rMin
      +pow(xbase2*rMin,2)
      +pow(xbase3*rMin,3)
      +pow(xbase4*rMin,4);

      maxX = 
      xbase0+xbase1*rMax
      +pow(xbase2*rMax,2)
      +pow(xbase3*rMax,3)
      +pow(xbase4*rMax,4);

      minX = 
      xbase0+xbase1*rMin
      +pow(xbase2*rMin,2)
      +pow(xbase3*rMin,3)
      +pow(xbase4*rMin,4);
       
    }else{
      maxY = 
      xbase0+xbase1*rMax
      +pow(xbase2*rMax,2)
      +pow(xbase3*rMax,3)
      +pow(xbase4*rMax,4);

      minY = 
      xbase0+xbase1*rMin
      +pow(xbase2*rMin,2)
      +pow(xbase3*rMin,3)
      +pow(xbase4*rMin,4);

      maxX = rMax;
      minX = rMin;
    }
  }
  
  for(int i=rMin;i<=rMax;i++){
    int x = i;
    int y = 
      xbase0+xbase1*i
      +pow(xbase2*i,2)
      +pow(xbase3*i,3)
      +pow(xbase4*i,4);
    turnLed(x, y);
  }
}

void turnLed(int x,int y){
   if(x > 0){
    if( y > 0 ){
      // Led 1
      int rx = abs(x*7/maxX)+1;
      if(rx > 7){
        rx = 0;
      }
      int ry = 7-abs(y*7/maxY);
            
      pantalla.setLed(0,ry,rx, true);
    }else{
      //led 4
      int rx = abs(x*7/maxX)+1;
      if(rx > 7){
        rx = 0;
      }
      int ry = abs(y*7/minY);
      pantalla.setLed(3,ry,rx, true);
    }
  }else{
    if( y > 0 ){
      // Led 2
      int rx = 7-abs(x*7/minX)+1;
      if(rx > 7){
        rx = 0;
      }
      int ry = 7-abs(y*7/maxY);
      pantalla.setLed(1,ry,rx, true);
    }else{
      // Led 3
      int rx = 7-abs(x*7/minX)+1;
      if(rx > 7){
        rx = 0;
      }
      int ry = abs(y*7/minY);

      if(ry == 0 && rx == 0){
        return;
      }
      pantalla.setLed(2,ry,rx, true);
    }
  }
  
}

bool isConstant(){
  if(xbase1 == 0 && xbase2 == 0 && xbase3 == 0 && xbase4 == 0){
    return true;
  }
  return false;
}

void converToVars(){
  xbase0 = 0;
  xbase1 = 0;
  xbase2 = 0;
  xbase3 = 0;
  xbase4 = 0;
  
  while(funcion.length()>0){
    switch(funcion.charAt(0)){
      case '+':
        funcion = funcion.substring(1,funcion.length());
        getValNumber();
        getPotencia();
        sumPotencia();
        break;
      case '-':
        funcion = funcion.substring(1,funcion.length());
        getValNumber();
        getPotencia();
        resPotencia();
        break;
      case 'X':
      case 'x':
        actualNum = 1;
        getPotencia();
        sumPotencia();
        break;
      case '0':
      case '1':
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        getValNumber();
        getPotencia();
        sumPotencia();
        break;
      default:
        break;
    }
  }
}

void resPotencia(){
  switch(actualPot){
    case 0:
      xbase0 -= actualNum;
      break;
    case 1:
      xbase1 -= actualNum;
      break;
    case 2:
      xbase2 -= actualNum;
      break;
    case 3:
      xbase3 -= actualNum;
      break;
    case 4:
      xbase4 -= actualNum;
      break;
  }
}

void sumPotencia(){
  switch(actualPot){
    case 0:
      xbase0 += actualNum;
      break;
    case 1:
      xbase1 += actualNum;
      break;
    case 2:
      xbase2 += actualNum;
      break;
    case 3:
      xbase3 += actualNum;
      break;
    case 4:
      xbase4 += actualNum;
      break;
  }
}

void getPotencia(){
  char var = funcion.charAt(0);

  if(var == 'x' || var == 'X'){
    char up = funcion.charAt(1);   
    if(up == '^'){
      char pot = funcion.charAt(2);
      actualPot = pot-48;  
      funcion = funcion.substring(3,funcion.length());
      return;
    }else{
      actualPot = 1;
      funcion = funcion.substring(2,funcion.length());
      return;
    }
  }else{
    actualPot = 0;
    return;
  }
}

void getValNumber(){
  char pD = funcion.charAt(0);
  char sD = funcion.charAt(1);


  //Single Var
  if(pD == 'x' || pD == 'X'){
    actualNum = 1;
    return;
  }

  //Single Digit
  if(sD < '0' || sD > '9'){
    switch(pD){
      case '0':
        actualNum = 0;
        break;
      case '1':
        actualNum = 1;
        break;
      case '2':
        actualNum = 2;
        break;
      case '3':
        actualNum = 3;
        break;
      case '4':
        actualNum = 4;
        break;
      case '5':
        actualNum = 5;
        break;
      case '6':
        actualNum = 6;
        break;
      case '7':
        actualNum = 7;
        break;
      case '8':
        actualNum = 8;
        break;
      case '9':
        actualNum = 9;
        break;
      default:
        actualNum = 0;
        break;
    }
    funcion = funcion.substring(1,funcion.length());
    return;
  }else{
    switch(pD){
      case '0':
        actualNum = 10;
        break;
      case '1':
        actualNum = 10;
        break;
      case '2':
        actualNum = 20;
        break;
      case '3':
        actualNum = 30;
        break;
      case '4':
        actualNum = 40;
        break;
      case '5':
        actualNum = 50;
        break;
      case '6':
        actualNum = 60;
        break;
      case '7':
        actualNum = 70;
        break;
      case '8':
        actualNum = 80;
        break;
      case '9':
        actualNum = 90;
        break;
      default:
        actualNum = 0;
        break;
    }

    switch(sD){
      case '0':
        actualNum += 0;
        break;
      case '1':
        actualNum += 1;
        break;
      case '2':
        actualNum += 2;
        break;
      case '3':
        actualNum += 3;
        break;
      case '4':
        actualNum += 4;
        break;
      case '5':
        actualNum += 5;
        break;
      case '6':
        actualNum += 6;
        break;
      case '7':
        actualNum += 7;
        break;
      case '8':
        actualNum += 8;
        break;
      case '9':
        actualNum += 9;
        break;
      default:
        actualNum += 0;
        break;
    }
    funcion = funcion.substring(2,funcion.length());
    return;    
  }
}
